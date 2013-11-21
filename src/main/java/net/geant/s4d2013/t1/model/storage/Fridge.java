package net.geant.s4d2013.t1.model.storage;

import java.util.List;

import net.geant.s4d2013.t1.exception.NoMoreCapacityExcetion;
import net.geant.s4d2013.t1.exception.NoSuchItemException;
import net.geant.s4d2013.t1.model.item.RoseItem;

public class Fridge implements Storage {
	public static final int CAPACITY = 20;

	private Storage storage = new StorageImpl();
	private int remainingCapacity = CAPACITY;

	@Override
	public void put(RoseItem item) throws NoMoreCapacityExcetion {
		if (getRemainingCapacity() <= 0) {
			throw new NoMoreCapacityExcetion();
		}
		storage.put(item);
		remainingCapacity -= 1;
	}

	@Override
	public List<RoseItem> getByName(String itemName) throws NoSuchItemException {
		return storage.getByName(itemName);
	}

	@Override
	public void remove(RoseItem selectedItem) throws NoSuchItemException {
		storage.remove(selectedItem);
		remainingCapacity += 1;
	}

	@Override
	public List<RoseItem> getAllItems() {
		return storage.getAllItems();
	}

	@Override
	public boolean isItemTypeOnList(RoseItem item) {
		return storage.isItemTypeOnList(item);
	}

	protected int getRemainingCapacity() {
		return remainingCapacity;
	}

	protected void setRemainingCapacity(int remainingCapacity) {
		this.remainingCapacity = remainingCapacity;
	}

}
