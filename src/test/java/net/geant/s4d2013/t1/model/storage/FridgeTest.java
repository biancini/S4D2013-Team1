package net.geant.s4d2013.t1.model.storage;

import net.geant.s4d2013.t1.exception.NoMoreCapacityExcetion;
import net.geant.s4d2013.t1.exception.NoSuchItemException;
import net.geant.s4d2013.t1.model.item.RoseItem;
import net.geant.s4d2013.t1.util.ItemUtils;

import org.fest.assertions.Assertions;
import org.junit.Assert;
import org.junit.Test;

public class FridgeTest {
	private static final int SAMPLE_VALUE = 10;

	@Test
	public void shouldBeAbleToAddItemToEmptyFridge()
			throws NoMoreCapacityExcetion {
		// given
		Fridge fridge = new Fridge();
		RoseItem item = ItemUtils.createItem("Regular item", SAMPLE_VALUE,
				SAMPLE_VALUE);

		// when
		fridge.put(item);

		// then
		Assertions.assertThat(fridge.getAllItems()).containsOnly(item);
		Assert.assertEquals(Fridge.CAPACITY - 1, fridge.getRemainingCapacity());
	}

	@Test(expected = NoMoreCapacityExcetion.class)
	public void shouldNotBeAbleToAddItemToFullFridge()
			throws NoMoreCapacityExcetion {
		// given
		Fridge fridge = new Fridge();
		fridge.setRemainingCapacity(0);
		RoseItem item = ItemUtils.createItem("Regular item", SAMPLE_VALUE,
				SAMPLE_VALUE);

		// when
		fridge.put(item);

		// then
	}

	@Test
	public void shouldBeAbleToRemoveItemFromContainingFridge()
			throws NoMoreCapacityExcetion, NoSuchItemException {
		// given
		Fridge fridge = new Fridge();
		RoseItem itemToRemove = ItemUtils.createItem("Regular item",
				SAMPLE_VALUE, SAMPLE_VALUE);
		fridge.put(itemToRemove);

		// when
		fridge.remove(itemToRemove);

		// then
		Assertions.assertThat(fridge.getAllItems()).isEmpty();
		Assert.assertEquals(Fridge.CAPACITY, fridge.getRemainingCapacity());
	}

	@Test(expected = NoSuchItemException.class)
	public void shouldNotBeAbleToRemoveItemFromNotContainingFridge()
			throws NoMoreCapacityExcetion, NoSuchItemException {
		// given
		Fridge fridge = new Fridge();
		RoseItem itemToRemove = ItemUtils.createItem("Regular item",
				SAMPLE_VALUE, SAMPLE_VALUE);

		// when
		fridge.remove(itemToRemove);

		// then
	}
}
