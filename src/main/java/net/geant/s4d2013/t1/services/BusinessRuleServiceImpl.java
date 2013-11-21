package net.geant.s4d2013.t1.services;

import java.util.HashMap;
import java.util.Map;

import net.geant.s4d2013.t1.exception.NoSuchValueException;
import net.geant.s4d2013.t1.model.item.RoseItem;
import net.geant.s4d2013.t1.util.ItemUtils;

public class BusinessRuleServiceImpl implements BusinessRuleService {
	private static final double OFFER_PRICE_MODIFIER = 1.5;
	private static final double BASE_VALUE = 10D;
	private Map<String, Double> itemsValues = new HashMap<String, Double>();

	public BusinessRuleServiceImpl() {
		for (String itemName : ItemUtils.POSSIBLE_ITEMS_NAMES) {
			// TODO: fantasy initialization of values, create a property file
			itemsValues.put(itemName, BASE_VALUE + itemName.length());
		}
	}

	@Override
	public void addValue(String itemName, Double value) {
		itemsValues.put(itemName, value);
	}

	@Override
	public double calculateOfferPrice(RoseItem item) {
		return calculateBasePrice(item) * OFFER_PRICE_MODIFIER;
	}

	@Override
	public Double calculatePurchasePrice(RoseItem item) {
		return calculateBasePrice(item);
	}

	protected Double calculateBasePrice(RoseItem item) {
		Double value = itemsValues.get(item.getName());
		if (value == null) {
			throw new NoSuchValueException();
		}
		return value * item.getQuality();
	}
}
