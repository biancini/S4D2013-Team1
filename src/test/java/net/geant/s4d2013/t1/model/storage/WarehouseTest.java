package net.geant.s4d2013.t1.model.storage;

import net.geant.s4d2013.t1.exception.NoMoreCapacityExcetion;
import net.geant.s4d2013.t1.exception.NoSuchItemException;
import net.geant.s4d2013.t1.model.item.RoseItem;
import net.geant.s4d2013.t1.model.item.SpecialItem;
import net.geant.s4d2013.t1.util.ItemUtils;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class WarehouseTest {
	private static final int SAMPLE_VALUE = 10;

	@Test
	public void shouldAddItemWhenFridgeIsNotFull() {
		// given
		Warehouse warehouse = new Warehouse();
		RoseItem item = ItemUtils.createItem("Regular item", SAMPLE_VALUE,
				SAMPLE_VALUE);

		// when
		warehouse.put(item);

		// then
		Assertions.assertThat(warehouse.getAllItems()).containsOnly(item);
	}

	@Test
	public void shouldAddItemWhenFridgeIsFull() {
		// given
		Warehouse warehouse = new Warehouse();
		warehouse.getFridge().setRemainingCapacity(0);
		RoseItem item = ItemUtils.createItem("Regular item", SAMPLE_VALUE,
				SAMPLE_VALUE);

		// when
		warehouse.put(item);

		// then
		Assertions.assertThat(warehouse.getAllItems()).containsOnly(item);
	}

	@Test
	public void shouldRemoveItemFromFridge() throws NoMoreCapacityExcetion,
			NoSuchItemException {
		// given
		Warehouse warehouse = new Warehouse();
		RoseItem item = ItemUtils.createItem("Regular item", SAMPLE_VALUE,
				SAMPLE_VALUE);
		warehouse.getFridge().put(item);

		// when
		warehouse.remove(item);

		// then
		Assertions.assertThat(warehouse.getAllItems()).isEmpty();
	}

	@Test
	public void shouldRemoveItemFromRegularStorage()
			throws NoMoreCapacityExcetion, NoSuchItemException {
		// given
		Warehouse warehouse = new Warehouse();
		RoseItem item = ItemUtils.createItem("Regular item", SAMPLE_VALUE,
				SAMPLE_VALUE);
		warehouse.getRegularStorage().put(item);

		// when
		warehouse.remove(item);

		// then
		Assertions.assertThat(warehouse.getAllItems()).isEmpty();
	}
	
	@Test
	public void shouldAddSulfurasToRegularStorageAndNotToFridge()
			throws NoMoreCapacityExcetion, NoSuchItemException {
		// given
		Warehouse warehouse = new Warehouse();
		RoseItem item = ItemUtils.createItem(SpecialItem.SULFURAS.getName(), SAMPLE_VALUE,
				SAMPLE_VALUE);

		// when
		warehouse.put(item);

		// then
		Assertions.assertThat(warehouse.getRegularStorage().getAllItems()).containsOnly(item);
		Assertions.assertThat(warehouse.getFridge().getAllItems()).isEmpty();
	}
	
	@Test
	public void shouldAddBackstageToRegularStorageAndNotToFridge()
			throws NoMoreCapacityExcetion, NoSuchItemException {
		// given
		Warehouse warehouse = new Warehouse();
		RoseItem item = ItemUtils.createItem(SpecialItem.BACKSTAGE.getName(), SAMPLE_VALUE,
				SAMPLE_VALUE);

		// when
		warehouse.put(item);

		// then
		Assertions.assertThat(warehouse.getRegularStorage().getAllItems()).containsOnly(item);
		Assertions.assertThat(warehouse.getFridge().getAllItems()).isEmpty();
	}
	
	@Test
	public void shouldAddVestRegularItemToRegularStorageAndNotToFridge()
			throws NoMoreCapacityExcetion, NoSuchItemException {
		// given
		Warehouse warehouse = new Warehouse();
		RoseItem item = ItemUtils.createItem("Vest", SAMPLE_VALUE,
				SAMPLE_VALUE);

		// when
		warehouse.put(item);

		// then
		Assertions.assertThat(warehouse.getRegularStorage().getAllItems()).containsOnly(item);
		Assertions.assertThat(warehouse.getFridge().getAllItems()).isEmpty();
	}
}
