package net.geant.s4d2013.t1.model.item;

public class SulfurasItem extends RoseItem {

	public SulfurasItem(int sellIn, int quality) {
		super(SpecialItem.SULFURAS.getName(), sellIn, quality);
	}

	@Override
	public void updateItemSellIn() {
		// Do Nothing
	}

	@Override
	protected void updateItemQuality() {
		// Do Nothing
	}

}
