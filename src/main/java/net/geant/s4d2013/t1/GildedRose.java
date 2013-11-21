package net.geant.s4d2013.t1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.geant.s4d2013.t1.exception.NoSuchItemException;
import net.geant.s4d2013.t1.model.item.Item;
import net.geant.s4d2013.t1.model.item.RoseItem;
import net.geant.s4d2013.t1.model.storage.Warehouse;
import net.geant.s4d2013.t1.services.BusinessRuleService;
import net.geant.s4d2013.t1.services.BusinessRuleServiceImpl;
import net.geant.s4d2013.t1.services.RetailService;
import net.geant.s4d2013.t1.services.RetailServiceImpl;
import net.geant.s4d2013.t1.services.SupplyService;
import net.geant.s4d2013.t1.services.SupplyServiceImpl;
import net.geant.s4d2013.t1.util.ItemUtils;

public class GildedRose {

	private static final int MAXIMUM_SUPPLY = 60;
	private static final int MINIMUM_SUPPLY = 40;
	private static final int MAXIMUM_BUYS = 6;
	private static final int MINIMUM_BUYS = 2;
	private static final int CUSTOMER_MAXIMUM_NUMBER = 10;
	private static final int CUSTOMER_MINIMUM_NUMBER = 5;

	private Warehouse storage = new Warehouse();
	private RetailService retailService;
	private SupplyService supplyService;
	private BusinessRuleService businessRuleService;

	private Random randomGenerator = new Random();

	protected void setBusinessRuleService(
			BusinessRuleService businessRuleService) {
		this.businessRuleService = businessRuleService;
	}

	public void setRandomGenerator(Random randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	public GildedRose(List<RoseItem> items) {
		this();
		for (RoseItem roseItem : items) {
			storage.put(roseItem);
		}
	}

	public GildedRose() {
		initializeContext();
	}

	private void initializeContext() {
		this.businessRuleService = new BusinessRuleServiceImpl();

		RetailServiceImpl retailServiceImpl = new RetailServiceImpl(storage);
		retailServiceImpl.setBusinessRuleService(businessRuleService);
		this.retailService = retailServiceImpl;

		SupplyServiceImpl supplyServiceImpl = new SupplyServiceImpl(storage);
		supplyServiceImpl.setBusinessRuleService(businessRuleService);
		this.supplyService = supplyServiceImpl;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<RoseItem> items = new ArrayList<RoseItem>();
		items.add(ItemUtils.createItem("Vest", 10, 20));
		items.add(ItemUtils.createItem("Aged Brie", 2, 1));
		items.add(ItemUtils.createItem("Elixir of the Mongoose", 5, 7));
		items.add(ItemUtils.createItem("Sulfuras, Hand of Ragnaros", 0, 50));
		items.add(ItemUtils.createItem(
				"Backstage passes to a TAFKAL80ETC concert", 15, 20));
		items.add(ItemUtils.createItem("Conjured Mana Cake", 3, 6));

		GildedRose gildedRose = new GildedRose(items);
		gildedRose.print("OMGHAI!");
		gildedRose.runIterations(50);
	}

	private void print(String message) {
		System.out.println(message);
	}

	// -1 means eternous loop
	public void runIterations(int numberOfIterations) {
		int iter = 0;

		while (numberOfIterations == -1 || iter < numberOfIterations) {
			printStatus();
			boolean updateFridgedItems = iter % 2 == 0;
			boolean buyingDay = iter % 3 == 0;
			boolean fridgeNeedsMaintenance = iter % 30 == 0;

			print("Update!");
			if (buyingDay) {
				buyProducts();
			}
			makeOffers();
			updateItems(updateFridgedItems);

			printStatus();

			if (fridgeNeedsMaintenance) {
				supplyService.serviceFridge();
			}

			// main is calling with an infinite loop
			// avoiding overflow
			if (iter == Integer.MAX_VALUE) {
				iter = 0;
			} else {
				iter++;
			}
		}
	}

	private void buyProducts() {
		int numAvailalbeItems = MINIMUM_SUPPLY
				+ randomGenerator.nextInt(MAXIMUM_SUPPLY - MINIMUM_SUPPLY + 1);
		List<RoseItem> availableItems = ItemUtils.produceRandomItems(
				randomGenerator, numAvailalbeItems);

		supplyService.chooseItemsAndBuy(availableItems);
	}

	protected void updateItems(boolean updateFridgedItems) {
		for (RoseItem item : storage.getRegularStorage().getAllItems()) {
			item.updateItemSellInAndQuality(true);
		}

		for (RoseItem item : storage.getFridge().getAllItems()) {
			item.updateItemSellInAndQuality(updateFridgedItems);
		}
	}

	protected void makeOffers() {
		int numberOfCustomers = CUSTOMER_MINIMUM_NUMBER
				+ randomGenerator.nextInt(CUSTOMER_MAXIMUM_NUMBER
						- CUSTOMER_MINIMUM_NUMBER + 1);
		for (int i = 0; i < numberOfCustomers; i++) {
			int numberOfItems = MINIMUM_BUYS
					+ randomGenerator.nextInt(MAXIMUM_BUYS - MINIMUM_BUYS + 1);
			for (int j = 0; j < numberOfItems; j++) {
				String randomItemName = ItemUtils.getRandomItemName(randomGenerator);
				try {
					RoseItem soldItem = retailService.sell(randomItemName);
					print("We sold item " + soldItem.getName());
				} catch (NoSuchItemException ex) {
					print("We don't have this item " + randomItemName);
				}
			}
		}
	}

	protected void printStatus() {
		for (Item i : storage.getAllItems()) {
			printItem(i);
		}

		print("We have " + storage.getMoneyAmount() + " moneys.");
	}

	protected void printItem(Item i) {
		print(i.toString());
	}
}