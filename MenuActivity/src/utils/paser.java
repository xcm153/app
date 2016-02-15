package utils;

import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class paser {
  public static ArrayList<String>getinfo(InputStream aInputStream) throws Exception {
	  XmlPullParser aParser=Xml.newPullParser();
	  aParser.setInput(aInputStream, "utf-8");
	  int type=aParser.getEventType();
	  ArrayList<String>aList=new ArrayList<>();
	  String aString="";
	  while(type!=XmlPullParser.END_DOCUMENT){
		  switch (type) {
		case XmlPullParser.START_TAG:
			if("string".equals(aParser.getName()))
			aString=aParser.nextText();
			aList.add(aString);
			aString="";
			
			break;
       
			
		}
		  
		 type= aParser.next();
		  
	  }
	  
	  
	  
	  
	  
	return aList;
	
}
}
