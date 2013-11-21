package net.geant.s4d2013.t1.services;

import net.geant.s4d2013.t1.exception.NoSuchItemException;
import net.geant.s4d2013.t1.model.item.RoseItem;

public interface RetailService {

	RoseItem sell(String itemName) throws NoSuchItemException;
}
