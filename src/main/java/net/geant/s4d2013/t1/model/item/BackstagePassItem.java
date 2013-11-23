package net.geant.s4d2013.t1.model.item;

public class BackstagePassItem extends IncreasingQualityItems {

	public BackstagePassItem(int sellIn, int quality) {
		super(SpecialItem.BACKSTAGE.getName(), sellIn, quality);
	}
	
	@Override
	public boolean canGoToFridge() {
		return false;
	}

}
