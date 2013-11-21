package net.geant.s4d2013.t1.model.item;

public class RegularItem extends RoseItem {

	public RegularItem(String name, int sellIn, int quality) {
		super(name, sellIn, quality);
	}

	@Override
	public void updateItemSellIn() {
		setSellIn(getSellIn() - 1);
	}

	@Override
	protected void updateItemQuality() {
		decreaseQuality();

		if (getSellIn() < 0) {
			decreaseQuality();
		}
	}

}
