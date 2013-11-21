package net.geant.s4d2013.t1.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.geant.s4d2013.t1.model.item.AgedBrieItem;
import net.geant.s4d2013.t1.model.item.BackstagePassItem;
import net.geant.s4d2013.t1.model.item.ConjuredItem;
import net.geant.s4d2013.t1.model.item.RegularItem;
import net.geant.s4d2013.t1.model.item.RoseItem;
import net.geant.s4d2013.t1.model.item.SpecialItem;
import net.geant.s4d2013.t1.model.item.SulfurasItem;

public final class ItemUtils {
	public static final int MAXIMUM_QUALITY = 50;
	public static final String[] POSSIBLE_ITEMS_NAMES = new String[] { "Vest",
			"Aged Brie", "Elixir of the Mongoose",
			"Sulfuras, Hand of Ragnaros",
			"Backstage passes to a TAFKAL80ETC concert", "Conjured Mana Cake",
			"Conjured" };
	public static final int MINIMUM_QUALITY = 0;

	private ItemUtils() {
	}

	public static List<RoseItem> produceFixedRandomItems(Random random) {
		List<RoseItem> items = new ArrayList<RoseItem>();
		items.add(ItemUtils.createItem("Vest", random.nextInt(MAXIMUM_QUALITY),
				random.nextInt(MAXIMUM_QUALITY)));
		items.add(ItemUtils.createItem("Aged Brie",
				random.nextInt(MAXIMUM_QUALITY),
				random.nextInt(MAXIMUM_QUALITY)));
		items.add(ItemUtils.createItem("Elixir of the Mongoose",
				random.nextInt(MAXIMUM_QUALITY),
				random.nextInt(MAXIMUM_QUALITY)));
		items.add(ItemUtils.createItem("Sulfuras, Hand of Ragnaros",
				random.nextInt(MAXIMUM_QUALITY),
				random.nextInt(MAXIMUM_QUALITY)));
		items.add(ItemUtils.createItem(
				"Backstage passes to a TAFKAL80ETC concert",
				random.nextInt(MAXIMUM_QUALITY),
				random.nextInt(MAXIMUM_QUALITY)));
		items.add(ItemUtils.createItem("Conjured Mana Cake",
				random.nextInt(MAXIMUM_QUALITY),
				random.nextInt(MAXIMUM_QUALITY)));
		return items;
	}

	public static List<RoseItem> produceRandomItems(Random randomGenerator,
			int itemsNumber) {
		List<RoseItem> items = new ArrayList<RoseItem>();

		for (int i = 0; i < itemsNumber; i++) {
			items.add(ItemUtils.createItem(
					ItemUtils.getRandomItemName(randomGenerator),
					randomGenerator.nextInt(MAXIMUM_QUALITY),
					randomGenerator.nextInt(MAXIMUM_QUALITY)));

		}

		return items;
	}

	public static RoseItem createItem(String itemName, int sellIn, int quality) {
		if (itemName.equals(SpecialItem.AGED_BRIE.getName())) {
			return new AgedBrieItem(sellIn, quality);
		} else if (itemName.equals(SpecialItem.BACKSTAGE.getName())) {
			return new BackstagePassItem(sellIn, quality);
		} else if (itemName.equals(SpecialItem.CONJURED.getName())) {
			return new ConjuredItem(sellIn, quality);
		} else if (itemName.equals(SpecialItem.SULFURAS.getName())) {
			return new SulfurasItem(sellIn, quality);
		} else {
			return new RegularItem(itemName, sellIn, quality);
		}
	}

	public static String getRandomItemName(Random randomGenerator) {
		int randomIndex = randomGenerator.nextInt(POSSIBLE_ITEMS_NAMES.length);
		return POSSIBLE_ITEMS_NAMES[randomIndex];
	}
}
