package com.webbeergame.controller;

import java.io.Serializable;

public class PlayerConfiguration implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Boolean showConfig = true;
	
	public Boolean getShowConfig() {
		return showConfig;
	}
	
	public void setShowConfig(Boolean showConfig) {
		this.showConfig = showConfig;
	}
}
