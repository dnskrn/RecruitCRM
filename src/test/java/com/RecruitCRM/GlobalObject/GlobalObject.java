package com.RecruitCRM.GlobalObject;

import com.RecruitCRM.Utility.CommonMethod;
import com.RecruitCRM.Utility.JavaScripExecuterJS;


public class GlobalObject {

	public static GlobalObject GlobalObject_instance;
	public static String Browser;

	private GlobalObject() {

	}

	public static GlobalObject getinstance() {
		if (GlobalObject_instance == null) {
			GlobalObject_instance = new GlobalObject();
		}
		return GlobalObject_instance;
	}
	
	public CommonMethod CM= CommonMethod.getinstance();
	public JavaScripExecuterJS JS=JavaScripExecuterJS.getinstance();
	

}
