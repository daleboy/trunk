package com.eshore.nrms.util;

import java.util.List;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.nrms.vo.PageVo;

public class PageUtil {

	public static PageVo getPageList(PageConfig page , List list ){
		PageVo pageVo = new PageVo();
		pageVo.setDataList(list);
		pageVo.setPageNum(page.getPageNum());
		pageVo.setDataCount(page.getRowCount());
		pageVo.setPageCount(page.getPageCount() );
		pageVo.setHasPrePage(page.getPageNum() > 1);
		if(pageVo.getHasPrePage()){
			pageVo.setPrePage(page.getPageNum() - 1);
		}
		pageVo.setHasNextPage(page.getPageNum() < pageVo.getPageCount());
		if(pageVo.getHasNextPage()){
			pageVo.setNextPage(page.getPageNum() + 1);
		}
		return pageVo;
	}
}
