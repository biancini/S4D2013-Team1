package net.geant.s4d2013.t1.services;

import java.util.List;

import net.geant.s4d2013.t1.model.item.RoseItem;

public interface SupplyService {

	void chooseItemsAndBuy(List<RoseItem> availaibleItemList);

	List<RoseItem> buyValuableItems(List<RoseItem> availaibleItemList);

	List<RoseItem> buyMissingItems(List<RoseItem> availaibleItemList);

	void serviceFridge();

}
