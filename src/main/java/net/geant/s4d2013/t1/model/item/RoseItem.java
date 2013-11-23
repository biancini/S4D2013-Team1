package net.geant.s4d2013.t1.model.item;

import net.geant.s4d2013.t1.util.ItemUtils;

public abstract class RoseItem extends Item {
	public RoseItem(SpecialItem product, int sellIn, int quality) {
		super(product.getName(), sellIn, quality);
	}

	public RoseItem(String name, int sellIn, int quality) {
		super(name, sellIn, quality);
	}

	public void decreaseQuality() {
		setQuality(Math.max(ItemUtils.MINIMUM_QUALITY, getQuality() - 1));
	}

	public void decreaseQuality(int i) {
		if (i < 0) {
			throw new IllegalArgumentException(
					"The value to increase shuold be positive");
		}
		setQuality(Math.max(ItemUtils.MINIMUM_QUALITY, getQuality() - i));
	}

	public void increaseQuality() {
		setQuality(Math.min(ItemUtils.MAXIMUM_QUALITY, getQuality() + 1));

	}

	public void increaseQuality(int i) {
		if (i < 0) {
			throw new IllegalArgumentException(
					"The value to increase shuold be positive");
		}
		setQuality(Math.min(ItemUtils.MAXIMUM_QUALITY, getQuality() + i));
	}

	@Override
	public String toString() {
		return "Name: " + getName() + ",quality: " + getQuality() + ",sellin: "
				+ getSellIn();
	}

	public void updateItemSellInAndQuality(boolean updateQuality) {
		updateItemSellIn();
		if (updateQuality) {
			updateItemQuality();
		}
	}

	protected abstract void updateItemSellIn();

	protected abstract void updateItemQuality();
	
	public abstract boolean canGoToFridge();

	public boolean isEqual(SpecialItem specialProduct) {
		return name.equals(specialProduct.getName());
	}

}
