/**
 * 
 */
package com.webbeergame.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luis
 * 
 */
public class Player implements Serializable{
	
	private static final long serialVersionUID = -8224758269420528151L;
	private Integer id;
	private String name;
	private Component component;
	private boolean playing;
	private PlayerConfiguration configuration;
	private Integer choice;
	List<PlayerListener> listeners;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
		if(component.getPlayer() == null || !component.getPlayer().equals(this)){
			component.setPlayer(this);
		}
	}
	public boolean isPlaying() {
		return playing;
	}
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	protected List<PlayerListener> getListeners() {
		return listeners == null ? listeners = new ArrayList<PlayerListener>(): listeners;
	}
	
	public void addListener(PlayerListener listener){
		getListeners().add(listener);
	}
	public void removeListener(PlayerListener listener){
		getListeners().remove(listener);
	}
	
	public void notifyListener() {
		for(PlayerListener l:getListeners()){
			l.finishPlay(this);
		}
	}

	public Integer getChoice() {
		return choice;
	}
	
	public void setChoice(Integer choice) {
		this.choice = choice;
	}
	public PlayerConfiguration getConfiguration() {
		return configuration == null ? configuration = new PlayerConfiguration(): configuration;
	}
	public void setConfiguration(PlayerConfiguration configuration) {
		this.configuration = configuration;
	}
	
}
