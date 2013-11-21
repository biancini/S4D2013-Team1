package net.geant.s4d2013.t1.model.item;

public class ConjuredItem extends RoseItem {

	public ConjuredItem(int sellIn, int quality) {
		super(SpecialItem.CONJURED.getName(), sellIn, quality);
	}

	@Override
	public void updateItemSellIn() {
		setSellIn(getSellIn() - 1);
	}

	@Override
	protected void updateItemQuality() {
		decreaseQuality(2);

		if (getSellIn() < 0) {
			decreaseQuality(2);
		}
	}

}
