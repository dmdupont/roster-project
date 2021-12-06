package main;

import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Vector;

public class CSV_Helper {
	
//	
//	public static void saveData(List<MyClass> myData) throws Exception {
//	    File csvFile = new File(<<path to write to>>);
//	    FileOutputStream fos = new FileOutputStream(csvFile);
//	    Writer fw = new OutputStreamWriter(fos, "UTF-8");
//	    for (MyClass oneDatum : myData) {
//	        List<String> rowValues = oneDatum.getValues();
//	        CSVHelper.writeLine(fw, rowValues);
//	    }
//	    fw.flush();
//	    fw.close();
//	}
	
	public static void writeLine(Writer wr, List<String> values) throws Exception {
		boolean firstVal = true;
		for (String val : values) {
			if (!firstVal) {
				wr.write(",");
			}
			wr.write(val);
			firstVal = false;
		}
		wr.write("\n");
	}
	
	/**
	 * To write into Writer Stream
	 */
	public static void writeLine2(Writer wr, List<String> values) throws Exception {
		boolean firstVal = true;
		for (String val : values) {
			if (!firstVal) {
				wr.write(",");
			}
			wr.write("\"");
			for (int i = 0; i < val.length(); i++) {
				char character = val.charAt(i);
				if (character == '\"') {
					wr.write("\""); // extra quote
				}
				wr.write(character);
			}
			wr.write("\"");
			firstVal = false;
		}
		wr.write("\n");
	}

	/**
	 * Returns a null when the input stream is empty
	 */
	public static List<String> parseLine(Reader rdr) throws Exception {
		int character = rdr.read();
		while (character == '\r') {
			character = rdr.read();
		}
		if (character < 0) {
			return null;
		}
		Vector<String> store = new Vector<String>();
		StringBuffer curVal = new StringBuffer();
		boolean inquotes = false;
		boolean started = false;
		while (character >= 0) {
			if (inquotes) {
				started = true;
				if (character == '\"') {
					inquotes = false;
				} else {
					curVal.append((char) character);
				}
			} else {
				if (character == '\"') {
					inquotes = true;
					if (started) {
						// if this is the second quote in a value, add a quote
						// this is for the double quote in the middle of a value
						curVal.append('\"');
					}
				} else if (character == ',') {
					store.add(curVal.toString());
					curVal = new StringBuffer();
					started = false;
				} else if (character == '\r') {
					// ignore LF characters
				} else if (character == '\n') {
					// end of a line, break out
					break;
				} else {
					curVal.append((char) character);
				}
			}
			character = rdr.read();
		}
		store.add(curVal.toString());
		return store;
	}
	
}