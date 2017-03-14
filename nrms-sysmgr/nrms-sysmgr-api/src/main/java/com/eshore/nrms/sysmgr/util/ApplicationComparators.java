package com.eshore.nrms.sysmgr.util;

import java.util.Comparator;

import com.eshore.khala.common.utils.type.StringUtils;
import com.eshore.nrms.sysmgr.pojo.Application;

public class ApplicationComparators {

	/**
	 * 
	 * @return 返回通过开始时间startDate作为比较依据的比较器
	 */
	public static StartDateComparator getStartDateComparator(){
		return new StartDateComparator();
	}
	
	static private class StartDateComparator implements Comparator<Application>{

		@Override
		public int compare(Application a1, Application a2) {
			if(StringUtils.isBlank(a1.getStartDate())){
				return 1;
			}
			if(StringUtils.isBlank(a2.getStartDate())){
				return -1;
			}
			return a1.getStartDate().compareTo(a2.getStartDate());
		}
		
	}
}
