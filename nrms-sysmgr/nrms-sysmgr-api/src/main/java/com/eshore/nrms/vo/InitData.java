package com.eshore.nrms.vo;
import com.eshore.nrms.sysmgr.pojo.Param;

import java.util.List;
import java.util.Hashtable;
import java.util.Map;
public class InitData {
	
	public static Hashtable<String , List<Param>> paramListTable = new Hashtable<String , List<Param>>();
	public static Hashtable<String , Map<String , Param>> paramMapTable = new Hashtable<String , Map<String , Param>>();
}
