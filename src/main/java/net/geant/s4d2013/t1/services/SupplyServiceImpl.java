package net.geant.s4d2013.t1.services;

import java.util.ArrayList;
import java.util.List;

import net.geant.s4d2013.t1.model.item.RoseItem;
import net.geant.s4d2013.t1.model.storage.Warehouse;

public class SupplyServiceImpl implements SupplyService {
	private static final double FRIDGE_MAINTENANCE_MODFIER = 0.02;

	private static final Double VALUABLE_THRESHOLD = 600D;

	private BusinessRuleService businessRuleService;

	private Warehouse storage;

	public SupplyServiceImpl(Warehouse storage) {
		this.storage = storage;
	}

	public boolean buyItem(RoseItem item) {
		Double purchasePrice = businessRuleService.calculatePurchasePrice(item);
		if (storage.getMoneyAmount() >= purchasePrice) {
			storage.put(item);
			storage.spendMoney(purchasePrice);
			return true;
		}

		return false;
	}

	@Override
	public List<RoseItem> buyMissingItems(List<RoseItem> availaibleItemList) {
		List<RoseItem> boughtItems = new ArrayList<RoseItem>();
		for (RoseItem item : availaibleItemList) {
			if (!storage.isItemTypeOnList(item)) {
				boolean itemBought = buyItem(item);
				if (itemBought) {
					boughtItems.add(item);
				}
			}
		}
		return boughtItems;
	}

	public List<RoseItem> buyValuableItems(List<RoseItem> availaibleItemList) {
		List<RoseItem> boughtItems = new ArrayList<RoseItem>();
		for (RoseItem item : availaibleItemList) {
			if (businessRuleService.calculatePurchasePrice(item) > VALUABLE_THRESHOLD) {
				boolean itemBought = buyItem(item);
				if (itemBought) {
					boughtItems.add(item);
				}
			}
		}
		return boughtItems;
	}

	public BusinessRuleService getBusinessRuleService() {
		return businessRuleService;
	}

	public void setBusinessRuleService(BusinessRuleService businessRuleService) {
		this.businessRuleService = businessRuleService;
	}

	@Override
	public void chooseItemsAndBuy(List<RoseItem> availaibleItemList) {

		List<RoseItem> notBoughtItems = new ArrayList<RoseItem>();
		notBoughtItems.addAll(availaibleItemList);

		List<RoseItem> boughtItems = buyMissingItems(notBoughtItems);
		notBoughtItems.removeAll(boughtItems);

		boughtItems = buyValuableItems(notBoughtItems);
	}

	@Override
	public void serviceFridge() {
		double cost = 0;
		for (RoseItem fridgeItem : storage.getFridge().getAllItems()) {
			cost += businessRuleService.calculateOfferPrice(fridgeItem)
					* FRIDGE_MAINTENANCE_MODFIER;
		}
		storage.spendMoney(cost);
	}
}
