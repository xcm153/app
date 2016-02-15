package utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class webutils {
public static ArrayList<String> getaddress(String number) throws Exception {
	InputStream aInputStream=webutils.class.getResourceAsStream("MyXml.xml");
	String aString=io.readsString(aInputStream);

	String bString=aString.replace("$string", number);
	
	return send(bString);
	
}
public static ArrayList<String> send(String bString) throws Exception {
	String path="http://fy.webxml.com.cn/webservices/EnglishChinese.asmx";
	URL url=new URL(path);
	HttpURLConnection connection=(HttpURLConnection) url.openConnection();
	connection.setConnectTimeout(5000);
	connection.setRequestMethod("POST");
	connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
	byte[]by=bString.getBytes();
	connection.setRequestProperty("Content-Length", by.length+"");
	connection.setDoOutput(true);
	connection.getOutputStream().write(by);
	ArrayList<String>aList=null;
	if(connection.getResponseCode()==200){
		InputStream aInputStream=connection.getInputStream();
		aList=paser.getinfo(aInputStream);
		
	
	}
	
	return aList;
}


}
