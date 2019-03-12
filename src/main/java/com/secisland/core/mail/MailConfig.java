/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.core.mail;

import java.io.Serializable;

public class MailConfig implements Serializable{

	private static final long serialVersionUID = -1243973507450315226L;
	private String host;
	private int port;
	private String username;
	private String password;
	private String auth;
	private String timeout;
	private String socketFactory;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	public String getSocketFactory() {
		return socketFactory;
	}
	public void setSocketFactory(String socketFactory) {
		this.socketFactory = socketFactory;
	}
}
