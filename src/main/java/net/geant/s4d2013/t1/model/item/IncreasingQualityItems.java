package net.geant.s4d2013.t1.model.item;

public class IncreasingQualityItems extends RoseItem {

	public IncreasingQualityItems(SpecialItem product, int sellIn, int quality) {
		super(product.getName(), sellIn, quality);
	}

	public IncreasingQualityItems(String name, int sellIn, int quality) {
		super(name, sellIn, quality);
	}

	@Override
	public void updateItemSellIn() {
		setSellIn(getSellIn() - 1);
	}

	@Override
	protected void updateItemQuality() {
		if (getSellIn() < 0) {
			setQuality(0);
		} else if (getSellIn() < 5) {
			increaseQuality(3);
		} else if (getSellIn() < 10) {
			increaseQuality(2);
		} else {
			increaseQuality();
		}
	}

}
