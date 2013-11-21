package net.geant.s4d2013.t1.service;

import net.geant.s4d2013.t1.exception.NoSuchItemException;
import net.geant.s4d2013.t1.model.item.RoseItem;
import net.geant.s4d2013.t1.model.item.SpecialItem;
import net.geant.s4d2013.t1.model.storage.Warehouse;
import net.geant.s4d2013.t1.services.BusinessRuleService;
import net.geant.s4d2013.t1.services.RetailServiceImpl;
import net.geant.s4d2013.t1.util.ItemUtils;

import org.fest.assertions.Assertions;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

public class RetailServiceTest {

	@Test
	public void shouldSellNonSpecialProduct() throws NoSuchItemException {
		// given
		Warehouse storage = new Warehouse();
		String itemName = "Vest";
		RoseItem selectedItem = ItemUtils.createItem(itemName, 10, 10);
		storage.put(selectedItem);
		BusinessRuleService businessRuleService = Mockito
				.mock(BusinessRuleService.class);
		Mockito.when(businessRuleService.calculateOfferPrice(selectedItem))
				.thenReturn(10D);
		RetailServiceImpl retailService = createRetailService(storage,
				businessRuleService);

		// when
		RoseItem item = retailService.sell(itemName);

		// then
		Assert.assertEquals(itemName, item.getName());
		Assert.assertEquals(10, item.getQuality());
		Assert.assertEquals(10, item.getSellIn());
		Assertions.assertThat(storage.getAllItems()).isEmpty();
	}

	@Ignore
	protected RetailServiceImpl createRetailService(Warehouse storage,
			BusinessRuleService businessRuleService) {
		RetailServiceImpl retailService = new RetailServiceImpl(storage);
		retailService.setBusinessRuleService(businessRuleService);
		return retailService;
	}

	@Test
	public void shouldSellHighestQualityNonSpecialProduct()
			throws NoSuchItemException {
		// given
		Warehouse storage = new Warehouse();
		String itemName = "Vest";
		RoseItem nonSelectedItem = ItemUtils.createItem(itemName, 10, 10);
		RoseItem selectedItem = ItemUtils.createItem(itemName, 10, 20);
		RoseItem nonSelectedItem2 = ItemUtils.createItem(itemName, 10, 1);

		storage.put(nonSelectedItem);
		storage.put(selectedItem);
		storage.put(nonSelectedItem2);
		BusinessRuleService businessRuleService = Mockito
				.mock(BusinessRuleService.class);
		RetailServiceImpl retailService = createRetailService(storage,
				businessRuleService);
		Mockito.when(businessRuleService.calculateOfferPrice(nonSelectedItem))
				.thenReturn(10D);
		Mockito.when(businessRuleService.calculateOfferPrice(selectedItem))
				.thenReturn(20D);
		Mockito.when(businessRuleService.calculateOfferPrice(nonSelectedItem2))
				.thenReturn(1D);
		// when
		RoseItem item = retailService.sell(itemName);

		// then
		Assert.assertEquals(itemName, item.getName());
		Assert.assertEquals(20, item.getQuality());
		Assert.assertEquals(10, item.getSellIn());
		Assertions.assertThat(storage.getAllItems()).containsOnly(
				nonSelectedItem, nonSelectedItem2);
	}

	@Test
	public void shouldSellSpecialProduct() throws NoSuchItemException {
		// given
		Warehouse storage = new Warehouse();
		String itemName = SpecialItem.AGED_BRIE.getName();
		RoseItem selectedItem = ItemUtils.createItem(
				SpecialItem.AGED_BRIE.getName(), 10, 10);
		storage.put(selectedItem);
		BusinessRuleService businessRuleService = Mockito
				.mock(BusinessRuleService.class);
		Mockito.when(businessRuleService.calculateOfferPrice(selectedItem))
				.thenReturn(10D);
		RetailServiceImpl retailService = createRetailService(storage,
				businessRuleService);

		// when
		RoseItem item = retailService.sell(itemName);

		// then
		Assert.assertEquals(itemName, item.getName());
		Assert.assertEquals(10, item.getQuality());
		Assert.assertEquals(10, item.getSellIn());
		Assertions.assertThat(storage.getAllItems()).isEmpty();
		Assert.assertEquals(new Double(1010D), storage.getMoneyAmount());
	}

	@Test
	public void shouldNotSellProductWhichIsNotAvailable()
			throws NoSuchItemException {
		// given
		Warehouse storage = new Warehouse();
		String itemName = SpecialItem.SULFURAS.getName();
		RoseItem availableItem = ItemUtils.createItem(
				SpecialItem.AGED_BRIE.getName(), 10, 10);
		storage.put(availableItem);
		BusinessRuleService businessRuleService = Mockito
				.mock(BusinessRuleService.class);
		Mockito.when(businessRuleService.calculateOfferPrice(availableItem))
				.thenReturn(10D);
		RetailServiceImpl retailService = createRetailService(storage,
				businessRuleService);

		// when
		try {
			retailService.sell(itemName);
			Assert.fail("The NoSuchItemExceptionShouldOccurs");
		} catch (Exception e) {
			// then
			// test pass
		}

		// then
		Assertions.assertThat(storage.getAllItems())
				.containsOnly(availableItem);
		Assert.assertEquals(new Double(1000D), storage.getMoneyAmount());
	}

}
