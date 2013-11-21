package net.geant.s4d2013.t1.services;

import net.geant.s4d2013.t1.model.item.RoseItem;

public interface BusinessRuleService {
	double calculateOfferPrice(RoseItem item);

	void addValue(String itemName, Double value);

	Double calculatePurchasePrice(RoseItem item);
}
