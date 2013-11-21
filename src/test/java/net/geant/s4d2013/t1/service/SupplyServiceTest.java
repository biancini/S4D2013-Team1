package net.geant.s4d2013.t1.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import net.geant.s4d2013.t1.exception.NoMoreCapacityExcetion;
import net.geant.s4d2013.t1.model.item.RoseItem;
import net.geant.s4d2013.t1.model.item.SpecialItem;
import net.geant.s4d2013.t1.model.storage.Warehouse;
import net.geant.s4d2013.t1.services.BusinessRuleService;
import net.geant.s4d2013.t1.services.SupplyServiceImpl;
import net.geant.s4d2013.t1.util.ItemUtils;

import org.fest.assertions.Assertions;
import org.junit.Assert;
import org.junit.Test;

public class SupplyServiceTest {

	private static final int HIGH_VALUE = 50;
	private static final int LOW_VALUE = 1;
	protected static final int SAMPLE_VALUE = 10;

	@Test
	public void shouldBuyRegularItem() {
		// given
		Warehouse storage = new Warehouse();
		SupplyServiceImpl supplyService = new SupplyServiceImpl(storage);
		RoseItem item = ItemUtils.createItem("Regular item", SAMPLE_VALUE,
				SAMPLE_VALUE);
		BusinessRuleService businessRuleService = mock(BusinessRuleService.class);
		when(businessRuleService.calculatePurchasePrice(any(RoseItem.class)))
				.thenReturn(10D);
		supplyService.setBusinessRuleService(businessRuleService);

		// when
		supplyService.buyItem(item);

		// then
		Assertions.assertThat(storage.getAllItems()).containsOnly(item);
	}

	@Test
	public void shouldBuyItemOfNewType() {
		// given
		Warehouse storage = new Warehouse();
		SupplyServiceImpl supplyService = new SupplyServiceImpl(storage);
		RoseItem item = ItemUtils.createItem("Regular item", SAMPLE_VALUE,
				SAMPLE_VALUE);
		List<RoseItem> availaibleItemList = new ArrayList<RoseItem>();
		availaibleItemList.add(item);
		BusinessRuleService businessRuleService = mock(BusinessRuleService.class);
		when(businessRuleService.calculatePurchasePrice(any(RoseItem.class)))
				.thenReturn(10D);
		supplyService.setBusinessRuleService(businessRuleService);

		// when
		List<RoseItem> boughtItems = supplyService
				.buyMissingItems(availaibleItemList);

		// then
		Assertions.assertThat(storage.getAllItems()).contains(item);
		Assertions.assertThat(boughtItems).containsOnly(item);
	}

	@Test
	public void shouldNotBuyItemOfExistingType() {
		// given
		Warehouse storage = new Warehouse();
		RoseItem existingItem = ItemUtils.createItem("Regular item",
				SAMPLE_VALUE, SAMPLE_VALUE);
		storage.put(existingItem);
		SupplyServiceImpl supplyService = new SupplyServiceImpl(storage);
		RoseItem item = ItemUtils.createItem("Regular item", SAMPLE_VALUE,
				SAMPLE_VALUE);
		List<RoseItem> availaibleItemList = new ArrayList<RoseItem>();
		availaibleItemList.add(item);

		// when
		List<RoseItem> boughtItems = supplyService
				.buyMissingItems(availaibleItemList);

		// then
		Assertions.assertThat(storage.getAllItems()).containsOnly(existingItem);
		Assertions.assertThat(boughtItems).isEmpty();
	}

	@Test
	public void shouldBuyValuableReqularItem() {
		// given
		Warehouse storage = new Warehouse();
		SupplyServiceImpl supplyService = new SupplyServiceImpl(storage);
		RoseItem item = ItemUtils.createItem("Regular item", SAMPLE_VALUE,
				HIGH_VALUE);
		List<RoseItem> availaibleItemList = new ArrayList<RoseItem>();
		availaibleItemList.add(item);

		BusinessRuleService businessRuleService = mock(BusinessRuleService.class);
		when(businessRuleService.calculatePurchasePrice(item))
				.thenReturn(1000D);
		supplyService.setBusinessRuleService(businessRuleService);

		// when
		List<RoseItem> boughtItems = supplyService
				.buyValuableItems(availaibleItemList);

		// then
		Assertions.assertThat(storage.getAllItems()).contains(item);
		Assert.assertEquals(new Double(0D), storage.getMoneyAmount());
		Assertions.assertThat(boughtItems).containsOnly(item);
	}

	@Test
	public void shouldNotBuyCheapReqularItem() {
		// given
		Warehouse storage = new Warehouse();
		SupplyServiceImpl supplyService = new SupplyServiceImpl(storage);
		RoseItem item = ItemUtils.createItem("Regular item", SAMPLE_VALUE,
				LOW_VALUE);
		List<RoseItem> availaibleItemList = new ArrayList<RoseItem>();
		availaibleItemList.add(item);

		BusinessRuleService businessRuleService = mock(BusinessRuleService.class);
		when(businessRuleService.calculatePurchasePrice(item)).thenReturn(1D);
		supplyService.setBusinessRuleService(businessRuleService);

		// when
		List<RoseItem> boughtItems = supplyService
				.buyValuableItems(availaibleItemList);
		Assert.assertEquals(new Double(1000D), storage.getMoneyAmount());

		// then
		Assertions.assertThat(storage.getAllItems()).isEmpty();
		Assertions.assertThat(boughtItems).isEmpty();
	}

	@Test
	public void shouldChooseItemsToBuy() {
		// given
		Warehouse storage = new Warehouse();
		SupplyServiceImpl supplyService = new SupplyServiceImpl(storage);
		RoseItem alreadyPresentItem = ItemUtils.createItem("Regular item",
				SAMPLE_VALUE, LOW_VALUE);
		storage.put(alreadyPresentItem);

		List<RoseItem> availaibleItemList = new ArrayList<RoseItem>();
		RoseItem shouldNotBePickedItem = ItemUtils.createItem("Regular item",
				SAMPLE_VALUE, SAMPLE_VALUE);
		RoseItem shouldBePickedItem1 = ItemUtils.createItem("Vest",
				SAMPLE_VALUE, LOW_VALUE);
		RoseItem shouldBePickedItem2 = ItemUtils.createItem(
				SpecialItem.SULFURAS.getName(), SAMPLE_VALUE, HIGH_VALUE);

		availaibleItemList.add(shouldNotBePickedItem);
		availaibleItemList.add(shouldBePickedItem1);
		availaibleItemList.add(shouldBePickedItem2);

		BusinessRuleService businessRuleService = mock(BusinessRuleService.class);
		when(businessRuleService.calculatePurchasePrice(shouldNotBePickedItem))
				.thenReturn((double) LOW_VALUE);
		when(businessRuleService.calculatePurchasePrice(shouldBePickedItem1))
				.thenReturn((double) SAMPLE_VALUE);
		when(businessRuleService.calculatePurchasePrice(shouldBePickedItem2))
				.thenReturn((double) HIGH_VALUE);
		supplyService.setBusinessRuleService(businessRuleService);

		// when
		supplyService.chooseItemsAndBuy(availaibleItemList);

		// then
		Assertions.assertThat(storage.getAllItems()).contains(
				alreadyPresentItem, shouldBePickedItem1, shouldBePickedItem2);
	}

	@Test
	public void shouldNotBuyBecauseNotEnoughMoney() {
		// given
		Warehouse storage = new Warehouse();
		storage.spendMoney(storage.getMoneyAmount());

		SupplyServiceImpl supplyService = new SupplyServiceImpl(storage);
		RoseItem item = ItemUtils.createItem("Regular item", SAMPLE_VALUE,
				SAMPLE_VALUE);

		BusinessRuleService businessRuleService = mock(BusinessRuleService.class);
		when(businessRuleService.calculatePurchasePrice(item)).thenReturn(
				(double) SAMPLE_VALUE);
		supplyService.setBusinessRuleService(businessRuleService);

		// when
		boolean haveBought = supplyService.buyItem(item);

		// then
		Assert.assertEquals(new Double(0D), storage.getMoneyAmount());
		Assert.assertFalse(haveBought);
	}

	@Test
	public void shouldNotModifyMoneyWhenServicingEmptyFridge() {
		// given
		Warehouse storage = new Warehouse();
		SupplyServiceImpl supplyService = new SupplyServiceImpl(storage);
		Double initialMoney = storage.getMoneyAmount();

		// when
		supplyService.serviceFridge();

		// then
		Assert.assertEquals(initialMoney, storage.getMoneyAmount());
	}

	@Test
	public void shouldDecreaseMoneyWhenServicingNotEmptyFridge()
			throws NoMoreCapacityExcetion {
		// given
		Warehouse storage = new Warehouse();
		storage.getFridge()
				.put(ItemUtils.createItem("Regular item", SAMPLE_VALUE,
						SAMPLE_VALUE));

		SupplyServiceImpl supplyService = new SupplyServiceImpl(storage);
		Double initialMoney = storage.getMoneyAmount();

		BusinessRuleService businessRuleService = mock(BusinessRuleService.class);
		when(businessRuleService.calculateOfferPrice(any(RoseItem.class)))
				.thenReturn(100D);
		supplyService.setBusinessRuleService(businessRuleService);

		// when
		supplyService.serviceFridge();

		// then
		Assert.assertEquals(new Double(initialMoney - 2D),
				storage.getMoneyAmount());
	}
}
