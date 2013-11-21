package net.geant.s4d2013.t1.goldmaster;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class GoldMasterFileReader {

	public String readFile(String fileName) throws IOException {
		BufferedReader bufferedReader = null;
		try {
			File file = new File(fileName);

			Reader reader = new FileReader(file);
			bufferedReader = new BufferedReader(reader);
			StringBuffer wholeFileBuffer = new StringBuffer();
			while (bufferedReader.ready()) {
				String line = bufferedReader.readLine();
				wholeFileBuffer.append(line);
			}

			return wholeFileBuffer.toString();
		} finally {
			bufferedReader.close();
		}
	}
}
