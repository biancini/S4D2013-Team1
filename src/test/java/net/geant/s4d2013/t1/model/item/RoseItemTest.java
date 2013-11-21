package net.geant.s4d2013.t1.model.item;

import net.geant.s4d2013.t1.util.ItemUtils;

import org.junit.Assert;
import org.junit.Test;

public class RoseItemTest {

	private static final boolean UPDATE_ITEMS_IN_FRIDGE = true;
	private static final boolean DONT_UPDATE_ITEMS_IN_FRIDGE = false;
	private static final int SAMPLE_VALUE = 10;

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionOnIllegalArgumentDecrese() {
		// given
		RoseItem item = ItemUtils.createItem("Regular item", SAMPLE_VALUE,
				SAMPLE_VALUE);

		// when
		item.decreaseQuality(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionOnIllegalArgumentIncrese() {
		// given
		RoseItem item = ItemUtils.createItem("Regular item", SAMPLE_VALUE,
				SAMPLE_VALUE);

		// when
		item.increaseQuality(-1);
	}

	@Test
	public void shouldAgedBrieSellInDecrease() {
		// given
		int initialSellIn = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.AGED_BRIE.getName(),
				initialSellIn, 10);

		// when
		item.updateItemSellIn();
		// then
		Assert.assertEquals(initialSellIn - 1, item.getSellIn());
	}

	@Test
	public void shouldSulfurasNotChangeQuality() {
		// given
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.SULFURAS.getName(),
				10, initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality, item.getQuality());
	}

	@Test
	public void shouldSulfurasNotChangeSellIn() {
		// given
		int initialSellIn = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.SULFURAS.getName(),
				initialSellIn, 10);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialSellIn, item.getSellIn());
	}

	@Test
	public void shouldItemQualityBeNotGreaterThan50() {
		// given
		int initialQuality = 50;
		RoseItem item = ItemUtils.createItem(SpecialItem.AGED_BRIE.getName(),
				10, initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality, item.getQuality());
	}

	@Test
	public void shouldRegularItemQualityDecreaseWhenUpdatingFridge() {
		// given
		int initialQuality = 50;
		RoseItem item = ItemUtils.createItem("Regular item", 10,
				initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality - 1, item.getQuality());
	}

	@Test
	public void shouldRegularItemQualityNoChangeWhenNotUpdatingFridge() {
		// given
		int initialQuality = 50;
		RoseItem item = ItemUtils.createItem("Regular item", 10,
				initialQuality);

		// when
		item.updateItemSellInAndQuality(DONT_UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality, item.getQuality());
	}

	@Test
	public void shouldConjuredItemQualityDecreaseTwiceWhenUpdatingFridge() {
		// given
		int initialQuality = 50;
		RoseItem item = ItemUtils.createItem(SpecialItem.CONJURED.getName(),
				10, initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality - 2, item.getQuality());
	}

	@Test
	public void shouldItemQualityBeNotNegative() {
		// given
		int initialQuality = 0;
		RoseItem item = ItemUtils.createItem("Regular item", 10,
				initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality, item.getQuality());
	}

	@Test
	public void shouldItemQualityBeNotNegativeAfterSellIn() {
		// given
		int initialQuality = 1;
		RoseItem item = ItemUtils.createItem("Regular item", -1,
				initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(0, item.getQuality());
	}

	@Test
	public void shouldRegularItemQualityDecreaseByTwoAfterSellInWhenUpdatingFridge() {
		// given
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem("Regular item", -1,
				initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality - 2, item.getQuality());
	}

	@Test
	public void shouldRegularItemQualityNotDecreaseAfterSellInWhenNotUpdatingFridge() {
		// given
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem("Regular item", -1,
				initialQuality);

		// when
		item.updateItemSellInAndQuality(DONT_UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality, item.getQuality());
	}

	@Test
	public void shouldConjuredItemQualityDecreaseByFourAfterSellInWhenUpdatingFridge() {
		// given
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.CONJURED.getName(),
				-1, initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality - 4, item.getQuality());
	}

	@Test
	public void shouldConjuredItemQualityNotDecreaseAfterSellInWhenNotUpdatingFridge() {
		// given
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.CONJURED.getName(),
				-1, initialQuality);

		// when
		item.updateItemSellInAndQuality(DONT_UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality, item.getQuality());
	}

	@Test
	public void shouldBackStageQualityIncreaseByOneBeforeTenSellInDays() {
		// given
		int initialSellIn = 11;
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.BACKSTAGE.getName(),
				initialSellIn, initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality + 1, item.getQuality());
	}

	@Test
	public void shouldBackStageQualityIncreaseByTwoAtTenSellInDays() {
		// given
		int initialSellIn = 10;
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.BACKSTAGE.getName(),
				initialSellIn, initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality + 2, item.getQuality());
	}

	@Test
	public void shouldBackStageQualityIncreaseByThreeAtFiveSellInDays() {
		// given
		int initialSellIn = 5;
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.BACKSTAGE.getName(),
				initialSellIn, initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality + 3, item.getQuality());
	}

	@Test
	public void shouldBackStageQualityEqualsZeroAtEndOfSellIn() {
		// given
		int initialSellIn = 0;
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.BACKSTAGE.getName(),
				initialSellIn, initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(0, item.getQuality());
	}

	@Test
	public void shouldAgedBrieQualityIncreaseByOneBeforeTenSellInDaysWhenUpdatingFridge() {
		// given
		int initialSellIn = 11;
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.AGED_BRIE.getName(),
				initialSellIn, initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality + 1, item.getQuality());
	}

	@Test
	public void shouldAgedBrieQualityNotChangeBeforeTenSellInDaysWhenNotUpdatingFridge() {
		// given
		int initialSellIn = 11;
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.AGED_BRIE.getName(),
				initialSellIn, initialQuality);

		// when
		item.updateItemSellInAndQuality(DONT_UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality, item.getQuality());
	}

	@Test
	public void shouldAgedBrieQualityIncreaseByTwoAtTenSellInDaysWhenUpdatingFridge() {
		// given
		int initialSellIn = 10;
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.AGED_BRIE.getName(),
				initialSellIn, initialQuality);
		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality + 2, item.getQuality());
	}

	@Test
	public void shouldAgedBrieQualityDontChangeAtTenSellInDaysWhenNotUpdatingFridge() {
		// given
		int initialSellIn = 10;
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.AGED_BRIE.getName(),
				initialSellIn, initialQuality);
		// when
		item.updateItemSellInAndQuality(DONT_UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality, item.getQuality());
	}

	@Test
	public void shouldAgedBrieQualityIncreaseByThreeAtFiveSellInDaysWhenUpdatingFridge() {
		// given
		int initialSellIn = 5;
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.AGED_BRIE.getName(),
				initialSellIn, initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality + 3, item.getQuality());
	}

	@Test
	public void shouldAgedBrieQualityDontChangeAtFiveSellInDaysWhenNotUpdatingFridge() {
		// given
		int initialSellIn = 5;
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.AGED_BRIE.getName(),
				initialSellIn, initialQuality);

		// when
		item.updateItemSellInAndQuality(DONT_UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality, item.getQuality());
	}

	@Test
	public void shouldAgedBrieQualityEqualsZeroAtEndOfSellInWhenUpdatingFridge() {
		// given
		int initialSellIn = 0;
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.AGED_BRIE.getName(),
				initialSellIn, initialQuality);

		// when
		item.updateItemSellInAndQuality(UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(0, item.getQuality());
	}

	@Test
	public void shouldAgedBrieQualityNotChangeAtEndOfSellInWhenDontUpdatingFridge() {
		// given
		int initialSellIn = 0;
		int initialQuality = 10;
		RoseItem item = ItemUtils.createItem(SpecialItem.AGED_BRIE.getName(),
				initialSellIn, initialQuality);

		// when
		item.updateItemSellInAndQuality(DONT_UPDATE_ITEMS_IN_FRIDGE);

		// then
		Assert.assertEquals(initialQuality, item.getQuality());
	}

}
