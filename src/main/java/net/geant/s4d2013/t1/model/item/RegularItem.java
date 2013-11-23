package net.geant.s4d2013.t1.model.item;

public class RegularItem extends RoseItem {
	
	private static final String[] ITEMS_THAT_DONT_GO_TO_FRIDGE = new String[] { "Vest" };

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
	
	private boolean isItemThatShouldNotGoToFridge() {
		for (String curItemName : ITEMS_THAT_DONT_GO_TO_FRIDGE) {
			if (curItemName.equals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean canGoToFridge() {
		boolean itemThatShouldNotGoToFridge = isItemThatShouldNotGoToFridge();
		if (itemThatShouldNotGoToFridge) {
			return false;
		}
		return true;
	}

}
