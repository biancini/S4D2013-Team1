package net.geant.s4d2013.t1.model.item;

public enum SpecialItem {
	AGED_BRIE("Aged Brie"), SULFURAS("Sulfuras, Hand of Ragnaros"), BACKSTAGE(
			"Backstage passes to a TAFKAL80ETC concert"), CONJURED("Conjured");
	private String name;

	private SpecialItem(String name) {
		this.name = name;
	}

	public boolean isEqual(String name) {
		return this.name.equals(name);
	}

	public boolean isEqual(Item item) {
		return isEqual(item.getName());
	}

	public String getName() {
		return name;
	}
}
