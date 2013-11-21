package net.geant.s4d2013.t1.service;

import net.geant.s4d2013.t1.exception.NoSuchValueException;
import net.geant.s4d2013.t1.model.item.RoseItem;
import net.geant.s4d2013.t1.services.BusinessRuleServiceImpl;
import net.geant.s4d2013.t1.util.ItemUtils;

import org.fest.assertions.Assertions;
import org.junit.Assert;
import org.junit.Test;

public class BusinessRuleServiceTest {

	@Test
	public void shouldReturnOfferPriceOfItem() {
		// given
		BusinessRuleServiceImpl businessRuleService = new BusinessRuleServiceImpl();
		String itemName = "Regular item";
		businessRuleService.addValue(itemName, 10D);
		RoseItem item = ItemUtils.createItem(itemName, 10, 10);

		// when
		Double price = businessRuleService.calculateOfferPrice(item);

		// then
		Assertions.assertThat(price).isEqualTo(item.getQuality() * 1.5 * 10);
	}

	@Test
	public void shouldThrowExceptionOnPurchaseIfNotValue() {
		// given
		BusinessRuleServiceImpl businessRuleService = new BusinessRuleServiceImpl();
		String itemName = "Regular item";
		RoseItem item = ItemUtils.createItem(itemName, 10, 10);

		// when
		try {
			businessRuleService.calculateOfferPrice(item);
			Assert.fail("The exception should be thrown when there is no value for item");
		} catch (NoSuchValueException e) {
			// then
			// testPass
		}
	}

	@Test
	public void shouldReturnPurchasePriceOfItem() {
		// given
		BusinessRuleServiceImpl businessRuleService = new BusinessRuleServiceImpl();
		String itemName = "Regular item";
		businessRuleService.addValue(itemName, 10D);
		RoseItem item = ItemUtils.createItem(itemName, 10, 10);

		// when
		Double price = businessRuleService.calculatePurchasePrice(item);

		// then
		Assertions.assertThat(price).isEqualTo(item.getQuality() * 10);
	}

	@Test
	public void shouldThrowExceptionOnOfferIfNotValue() {
		// given
		BusinessRuleServiceImpl businessRuleService = new BusinessRuleServiceImpl();
		String itemName = "Regular item";
		RoseItem item = ItemUtils.createItem(itemName, 10, 10);

		// when
		try {
			businessRuleService.calculatePurchasePrice(item);
			Assert.fail("The exception should be thrown when there is no value for item");
		} catch (NoSuchValueException e) {
			// then
			// testPass
		}
	}
}
