package net.geant.s4d2013.t1.goldmaster;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Ignore;

public class GoldMasterTest {

	@Ignore
	public void goldMasterTest() throws IOException {
		GoldMasterGenerator generator = new GoldMasterGenerator(
				"outputRefactor");
		generator.generateOutput();

		for (int i = 0; i < 20; i++) {
			boolean areFileEqual = compareTwoFiles("outputOriginal/" + i
					+ ".txt", "outputRefactor/" + i + ".txt");
			Assert.assertTrue(areFileEqual);
		}
	}

	public boolean compareTwoFiles(String fileName1, String fileName2)
			throws IOException {
		GoldMasterFileReader fileReader = new GoldMasterFileReader();
		String fileContent1 = fileReader.readFile(fileName1);
		String fileContent2 = fileReader.readFile(fileName2);

		return fileContent1.equals(fileContent2);
	}
}
