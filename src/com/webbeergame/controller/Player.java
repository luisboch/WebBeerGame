/**
 * 
 */
package com.webbeergame.controller;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luis
 * 
 */
public class Player {
	
	Integer id;
	String name;
	Component component;
	boolean playing;
	
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
	
	protected void notifyListener() {
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
}
