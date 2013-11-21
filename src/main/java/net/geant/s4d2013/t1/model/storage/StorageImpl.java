package net.geant.s4d2013.t1.model.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.geant.s4d2013.t1.exception.NoSuchItemException;
import net.geant.s4d2013.t1.model.item.RoseItem;

public class StorageImpl implements Storage {
	private Map<String, List<RoseItem>> itemsMap = new HashMap<String, List<RoseItem>>();

	@Override
	public void put(RoseItem item) {
		if (!itemsMap.containsKey(item.getName())) {
			itemsMap.put(item.getName(), new ArrayList<RoseItem>());
		}
		itemsMap.get(item.getName()).add(item);
	}

	@Override
	public List<RoseItem> getByName(String itemName) throws NoSuchItemException {
		if (!itemsMap.containsKey(itemName) || itemsMap.get(itemName).isEmpty()) {
			throw new NoSuchItemException();
		}
		return itemsMap.get(itemName);
	}

	@Override
	public void remove(RoseItem selectedItem) throws NoSuchItemException {
		if (!isItemTypeOnList(selectedItem)) {
			throw new NoSuchItemException();
		}
		itemsMap.get(selectedItem.getName()).remove(selectedItem);
	}

	@Override
	public List<RoseItem> getAllItems() {
		List<RoseItem> allItems = new ArrayList<RoseItem>();
		for (List<RoseItem> currentList : itemsMap.values()) {
			allItems.addAll(currentList);
		}
		return allItems;
	}

	@Override
	public boolean isItemTypeOnList(RoseItem item) {
		try {
			getByName(item.getName());
		} catch (NoSuchItemException e) {
			return false;
		}
		return true;
	}
}
