package net.geant.s4d2013.t1.model.storage;

import java.util.ArrayList;
import java.util.List;

import net.geant.s4d2013.t1.exception.NoMoreCapacityExcetion;
import net.geant.s4d2013.t1.exception.NoSuchItemException;
import net.geant.s4d2013.t1.model.item.RoseItem;

public class Warehouse {

	private static final double INITIAL_MONEY_AMOUNT = 1000D;

	private StorageImpl regularStorage = new StorageImpl();

	private Fridge fridge = new Fridge();

	public StorageImpl getRegularStorage() {
		return regularStorage;
	}

	private double moneyAmount = INITIAL_MONEY_AMOUNT;

	public Fridge getFridge() {
		return fridge;
	}

	public Double getMoneyAmount() {
		return moneyAmount;
	}

	public void earnMoney(double amount) {
		moneyAmount += amount;
	}

	public void spendMoney(double amount) {
		moneyAmount -= amount;
	}

	public void put(RoseItem item) {
		boolean insertedIntoFridge = false;
		if (item.canGoToFridge()) {
			try {
				fridge.put(item);
				insertedIntoFridge = true;
			} catch (NoMoreCapacityExcetion e) {
				// Do nothing
			}
		}

		if (!insertedIntoFridge) {
			regularStorage.put(item);
		}
	}

	public List<RoseItem> getByName(String itemName) throws NoSuchItemException {
		List<RoseItem> returnedList = new ArrayList<RoseItem>();
		try {
			returnedList.addAll(fridge.getByName(itemName));
		} catch (NoSuchItemException e) {
			// Do nothing
		}
		try {
			returnedList.addAll(regularStorage.getByName(itemName));
		} catch (NoSuchItemException e) {
			// Do nothing
		}

		if (returnedList.isEmpty()) {
			throw new NoSuchItemException();
		}
		return returnedList;
	}

	public void remove(RoseItem selectedItem) throws NoSuchItemException {
		try {
			fridge.remove(selectedItem);
		} catch (NoSuchItemException e) {
			regularStorage.remove(selectedItem);
		}
	}

	public List<RoseItem> getAllItems() {
		List<RoseItem> returnedList = new ArrayList<RoseItem>();
		returnedList.addAll(fridge.getAllItems());
		returnedList.addAll(regularStorage.getAllItems());
		return returnedList;
	}

	public boolean isItemTypeOnList(RoseItem item) {
		return fridge.isItemTypeOnList(item)
				|| regularStorage.isItemTypeOnList(item);
	}

}
