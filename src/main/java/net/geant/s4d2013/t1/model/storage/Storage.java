package net.geant.s4d2013.t1.model.storage;

import java.util.List;

import net.geant.s4d2013.t1.exception.NoMoreCapacityExcetion;
import net.geant.s4d2013.t1.exception.NoSuchItemException;
import net.geant.s4d2013.t1.model.item.RoseItem;

public interface Storage {

	void put(RoseItem item) throws NoMoreCapacityExcetion;

	List<RoseItem> getByName(String itemName) throws NoSuchItemException;

	void remove(RoseItem selectedItem) throws NoSuchItemException;

	List<RoseItem> getAllItems();

	boolean isItemTypeOnList(RoseItem item);

}
