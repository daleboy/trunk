package com.eshore.nrms.web.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshore.nrms.sysmgr.pojo.Param;
import com.eshore.nrms.sysmgr.service.IParamService;
import com.eshore.nrms.vo.Conts;
import com.eshore.nrms.vo.InitData;

@Component
public class InitDataComponent implements InitializingBean {

	@Autowired
	private IParamService paramService;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		for(String paramType : Conts.PARAM_TYPE){
			List<Param> paramList = paramService.queryParamByParamType(paramType);
			InitData.paramListTable.put(paramType, paramList);
			InitData.paramMapTable.put(paramType, listToMap(paramType , paramList));
		}
	}
	
	
	private Map<String,Param> listToMap(String paramType , List<Param> paramList){
		HashMap<String , Param> paramMap = new HashMap<String , Param>();
		for(Param param : paramList){
			if(paramType.toLowerCase().contains("_ip")){
				paramMap.put(param.getId(), param);
			}else{
				paramMap.put(param.getParamValue(), param);
			}
		}
		return paramMap;
	}

}
