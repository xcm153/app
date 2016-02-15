package utils;

import java.util.List;

import android.test.AndroidTestCase;

public class test extends AndroidTestCase {

	
	@Override
	protected void setUp() throws Exception {
		// TODO 自动生成的方法存根
		List<String>aList=webutils.getaddress("a");
		for (String string : aList) {
			System.out.println(string+"1");
		}
		super.setUp();
	}
	
	
}
