package net.geant.s4d2013.t1.services;

import net.geant.s4d2013.t1.exception.NoSuchItemException;
import net.geant.s4d2013.t1.model.item.RoseItem;
import net.geant.s4d2013.t1.model.storage.Warehouse;

public class RetailServiceImpl implements RetailService {

	private Warehouse storage;

	private BusinessRuleService businessRuleService;

	public RetailServiceImpl(Warehouse storage) {
		this.storage = storage;
	}

	@Override
	public RoseItem sell(String itemName) throws NoSuchItemException {
		RoseItem selectedItem = highestOfferPriceItem(itemName);
		storage.remove(selectedItem);
		storage.earnMoney(businessRuleService.calculateOfferPrice(selectedItem));
		return selectedItem;
	}

	public RoseItem highestOfferPriceItem(String itemName)
			throws NoSuchItemException {
		double highestOfferPrice = 0;
		RoseItem highestValueItem = null;
		for (RoseItem item : storage.getByName(itemName)) {
			double itemPrice = businessRuleService.calculateOfferPrice(item);
			if (itemPrice > highestOfferPrice) {
				highestOfferPrice = itemPrice;
				highestValueItem = item;
			}
		}
		if (highestValueItem == null) {
			throw new NoSuchItemException();
		}
		return highestValueItem;
	}

	public BusinessRuleService getBusinessRuleService() {
		return businessRuleService;
	}

	public void setBusinessRuleService(BusinessRuleService businessRuleService) {
		this.businessRuleService = businessRuleService;
	}
}
