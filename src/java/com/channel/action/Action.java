/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.channel.action;

/**
 *
 * @author tringapps
 */

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;




/**
 * 
 * @author sukumar.j@tringapps.com
 * used to perform the defined action
 */
public class Action implements Serializable{
 
	//body for the request to perform the action
	public Map<String, Object> inputBody  = new HashMap<String, Object>();
	public Map<String, Object> outputBody = new HashMap<String, Object>();
    //url path for the action to perform
    public String path;
    //name of the action going to perform
    public String actionName;

    //map, which consits of key and value pair to send in the request header
    public Map<String, String> inputHeader = new HashMap<String, String>(); 
    
    //type of the method to be performed ---> GET, POST, PUT,etc.
	public String method;
    //map, which consits of key and value pair from of the response header
    public Map<String, String> outputHeader = new HashMap<String, String>(); 
    

    //unique identifier for each action
    public String actionId;
    
    
    public boolean isPassiveAction;

    public int repeatCount;

    public String immediateAction;
    
    public boolean shouldUseSubLocalUrl;

    /**
     * To get the body for the request
     * @return
     */
    public Map<String, Object> getInputBody() {
		return inputBody;
	}

    /**
     * To set the body of the request
     * @param inputBody
     */
	public void setInputBody(Map<String, Object> inputBody) {
		this.inputBody = inputBody;
	}

	/**
	 * To get the path for making the request
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * To set the path for making the request
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * To get the name of the action
	 * @return
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * To set the name of the action
	 * @param actionName
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * To get the input header pair
	 * @return
	 */
	public Map<String, String> getInputHeader() {
		return inputHeader;
	}

	/**
	 * To set the input header pair
	 * @param inputHeader
	 */
	public void setInputHeader(Map<String, String> inputHeader) {
		this.inputHeader = inputHeader;
	}

	/**
	 * To get the type of the method to be perform
	 * @return
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * To set the type of the method to perform
	 * @param method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * To get the Output header
	 * @return
	 */
	public Map<String, String> getOutputHeader() {
		return outputHeader;
	}
 
	/**
	 * To set the outputHeader from the response
	 * @param outputHeader
	 */
	public void setOutputHeader(Map<String, String> outputHeader) {
		this.outputHeader = outputHeader;
	}

	/**
	 * To get the identifier for the action
	 * @return
	 */
	public String getActionId() {
		return actionId;
	}

	public Map<String, Object> getOutputBody() {
		return outputBody;
	}

	public boolean isPassiveAction() {
		return isPassiveAction;
	}

	public void setPassiveAction(boolean isPassiveAction) {
		this.isPassiveAction = isPassiveAction;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}



	public boolean isShouldUseSubLocalUrl() {
		return shouldUseSubLocalUrl;
	}

	public void setShouldUseSubLocalUrl(boolean shouldUseSubLocalUrl) {
		this.shouldUseSubLocalUrl = shouldUseSubLocalUrl;
	}

	public String getImmediateAction() {
		return immediateAction;
	}

	public void setImmediateAction(String immediateAction) {
		this.immediateAction = immediateAction;
	}

	public void setOutputBody(Map<String, Object> outputBody) {
		this.outputBody = outputBody;
	}

	/**
	 * To set the identifier for the action
	 * @param actionId
	 */
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}




}
