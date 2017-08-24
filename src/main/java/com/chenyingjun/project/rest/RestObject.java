package com.chenyingjun.project.rest;

import java.io.Serializable;

/**
 * 返回对象
 * 
 * @author chenyingjun
 * @since 1.0
 * @version 2017年3月31日 chenyingjun
 */
public class RestObject implements Serializable {

    /** FIXME */
    private static final long serialVersionUID = -7771374323162062273L;

    /** 状态码成功 */
    public final static int STATUS_CODE_SUCCESS = 0;

    /** 状态调用失败 */
    public final static int STATUS_CODE_FAILURE = 300;

    /** 状态码超时 */
    public final static int STATUS_CODE_TIMEOUT = 301;

    /** 返回状态 */
    private int state = STATUS_CODE_SUCCESS;
    
    /** 消息 */
    private String message = "";
    
    /** 返回数据 */
    private Object data = "";
    
    /**
     * 生成成功对象
     * 
     * @param message message
     * @return obj
     */
    public static RestObject newOk(String message) {
        return new RestObject(STATUS_CODE_SUCCESS, message);
    }
    
    /**
     * 生成成功对象
     * 
     * @param message message
     * @param data data
     * @return obj
     */
    public static RestObject newOk(String message, Object data) {
        return new RestObject(STATUS_CODE_SUCCESS, message, data);
    }
    
    /**
     * 生成错误对象
     * 
     * @param message message
     * @return obj
     */
    public static RestObject newError(String message) {
        return new RestObject(STATUS_CODE_FAILURE, message);
    }
    
    /**
     * 生成错误对象
     * 
     * @param message message
     * @param data data
     * @return obj
     */
    public static RestObject newError(String message, Object data) {
        return new RestObject(STATUS_CODE_FAILURE, message, data);
    }
    
    /**
     * 生成超时对象
     * 
     * @param message message
     * @return obj
     */
    public static RestObject newTimeout(String message) {
        return new RestObject(STATUS_CODE_TIMEOUT, message);
    }
    
    /**
     * 生成超时对象
     * 
     * @param message message
     * @param data data
     * @return obj
     */
    public static RestObject newTimeout(String message, Object data) {
        return new RestObject(STATUS_CODE_TIMEOUT, message, data);
    }
    
    /**
     * 生成禁止访问对象
     * 
     * @param message message
     * @return obj
     */
    public static RestObject newForbidden(String message) {
        return new RestObject(STATUS_CODE_TIMEOUT, message);
    }
    
    /**
     * 生成禁止访问对象
     * 
     * @param message message
     * @param data data
     * @return obj
     */
    public static RestObject newForbidden(String message, Object data) {
        return new RestObject(STATUS_CODE_TIMEOUT, message, data);
    }
    
    /**
     * 生成访问对象
     * 
     * @param state state
     * @param message message
     * @param data data
     * @return obj
     */
    public static RestObject newRestObject(int state, String message, Object data) {
        return new RestObject(state, message, data);
    }
    
    /**
     * 生成访问对象
     * 
     * @param state state
     * @param message message
     * @return obj
     */
    public static RestObject newRestObject(int state, String message) {
        return new RestObject(state, message);
    }
    
    /**
     * 构造函数
     */
    public RestObject() {
    }
    
    /**
     * 构造函数
     * 
     * @param state state
     */
    public RestObject(int state) {
        this.state = state;
    }
    
    /**
     * 构造函数
     * 
     * @param state state
     * @param message message
     */
    public RestObject(int state, String message) {
        this.state = state;
        this.message = message;
    }
    
    /**
     * 构造函数
     * 
     * @param message message
     * @param state state
     * @param data data
     */
    public RestObject(int state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = (null == data) ? "" : data;
    }
    
    /**
     * @return state
     */
    public int getState() {
        return state;
    }
    
    /**
     * @param state 要设置的 state
     */
    public void setState(int state) {
        this.state = state;
    }
    
    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * @param message 要设置的 message
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * @return data
     */
    public Object getData() {
        return data;
    }
    
    /**
     * @param data 要设置的 data
     */
    public void setData(Object data) {
        this.data = data;
    }
    
}
