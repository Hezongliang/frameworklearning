package com.ztesoft.frameworklearning.system.model;

import java.io.Serializable;

public class SystemParam implements Serializable {
	private static final long serialVersionUID = -5887448003651914550L;
	private String paramId;
    private String paramValue;
    private String paramName;

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId == null ? null : paramId.trim();
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }
    
    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }
}