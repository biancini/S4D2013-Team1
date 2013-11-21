package net.geant.s4d2013.t1.goldmaster;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class GoldMasterFileWriter {

	public void writeOutputToFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		OutputStream fileOutputStream = new FileOutputStream(file);
		PrintStream outputStream = new PrintStream(fileOutputStream);
		System.setOut(outputStream);
		System.setErr(outputStream);
	}
}
