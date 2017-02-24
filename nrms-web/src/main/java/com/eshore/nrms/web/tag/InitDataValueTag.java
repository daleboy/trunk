package com.eshore.nrms.web.tag;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.eshore.nrms.sysmgr.pojo.Param;
import com.eshore.nrms.vo.InitData;


public class InitDataValueTag extends TagSupport {

    private String dataType;
    private String dataValue;
    
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = this.pageContext.getOut();
            if (StringUtils.isBlank(dataType) || StringUtils.isBlank(dataValue) ) {
                return SKIP_BODY;
            }

            Map<String , Param> paramInfoMap = InitData.paramMapTable.get(dataType);
            Param param = paramInfoMap.get(dataValue);
            String returnValue = null;
            if(param == null){
            	returnValue = "";
            }else{
            	returnValue = param.getParamName();
            }
            out.print(returnValue);
            return SKIP_BODY;
            
        } catch (Exception e) {
            throw new JspException(e.getMessage());
        }
    }

    @Override
    public int doEndTag() throws JspException {

        return EVAL_PAGE;

    }
    
    @Override
    public void release() {  
        super.release();  
        this.dataType = null;  
        this.dataValue = null;
    }  


    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}


    
}
