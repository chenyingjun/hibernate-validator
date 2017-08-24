package com.chenyingjun.validator.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: FormValid.java
 * @Description: 表单验证失败错误返回对象
 * @author: chenyingjun
 * @date: 2017年8月24日
 * @version V1.0
 */
public class FormValid implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 全局错误 */
    private List<String> globalErrors = new ArrayList<String>();

    /** 字段错误 */
    private Map<String, String> fieldErrors = new HashMap<String, String>();

    public FormValid() {
        super();
    }

    public FormValid(Errors errors) {
        if (errors.hasGlobalErrors()) {
            for (ObjectError error : errors.getGlobalErrors()) {
                String message = error.getDefaultMessage();
                /*if (StringUtils.isNotBlank(message)) {
                    try {
                        message = URLEncoder.encode(message, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                    }
                }*/
                this.globalErrors.add(message);
            }
        }
        if (errors.hasFieldErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                String message = error.getDefaultMessage();
                /*if (StringUtils.isNotBlank(message)) {
                    try {
                        message = URLEncoder.encode(message, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                    }
                }*/
                this.fieldErrors.put(error.getField(), message);
            }
        }
    }

    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    public void setGlobalErrors(List<String> globalErrors) {
        this.globalErrors = globalErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

}
