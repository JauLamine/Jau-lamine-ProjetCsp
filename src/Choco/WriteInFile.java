package Choco;

import java.io.FileWriter;
import java.io.IOException;

public class WriteInFile {
	private static final String IOException = null;
	public void writeInFile(String t, String n) {
		try
		{
		 FileWriter fw = new FileWriter(n+".txt",true);
		 fw.write(t+"\n");
		 fw.close();
		}
		catch(IOException ioe)
		{
		 System.err.println(IOException + ioe.getMessage());
		}
	}


}
