package utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class io {
public static String readsString(InputStream aInputStream) throws Exception {
	BufferedReader reader=new BufferedReader(new InputStreamReader(aInputStream));
	StringBuilder a=new StringBuilder();
	String aString;
	while((aString=reader.readLine())!=null){
		
	a.append(aString);
	}
	
	reader.close();
	return a.toString();
	 
}
}
