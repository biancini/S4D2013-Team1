package net.geant.s4d2013.t1.goldmaster;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

import net.geant.s4d2013.t1.GildedRose;
import net.geant.s4d2013.t1.model.item.RoseItem;
import net.geant.s4d2013.t1.util.ItemUtils;

import org.junit.Ignore;

@Ignore
public class GoldMasterGenerator {
	String outputFolder = "outputOriginal";

	public GoldMasterGenerator(String outputFolder) {
		this.outputFolder = outputFolder;
	}

	public void generateOutput() throws FileNotFoundException {
		GoldMasterFileWriter writer = new GoldMasterFileWriter();
		for (int i = 0; i < 20; i++) {
			writer.writeOutputToFile(outputFolder + "/" + i + ".txt");
			Random random = new Random(100 + i);
			List<RoseItem> items = ItemUtils.produceFixedRandomItems(random);
			GildedRose gildedRose = new GildedRose(items);
			gildedRose.setRandomGenerator(random);
			gildedRose.runIterations(40);
		}
	}
}
