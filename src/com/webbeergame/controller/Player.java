/**
 * 
 */
package com.webbeergame.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author luis
 * 
 */
public class Player implements Serializable{
	
	private static final Logger log = Logger.getLogger(Player.class.getSimpleName());
	
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

		log.info("Player "+getName()+" notifing listeners");
		
		for(PlayerListener l:getListeners()){
			l.finishPlay(this);
		}
	}
	
	public void clearListeners(){
		getListeners().clear();
	}

	public Integer getChoice() {
		return choice;
	}
	
	public void setChoice(Integer choice) {
		this.choice = choice;
		if(component != null){
			component.setRecentOrder(choice);
			
			if(component.getInventory() > choice){
				component.setSupplied(choice);
			} else {
				component.setSupplied(component.getInventory()); 
			}
			
		}
	}
	public PlayerConfiguration getConfiguration() {
		return configuration == null ? configuration = new PlayerConfiguration(): configuration;
	}
	public void setConfiguration(PlayerConfiguration configuration) {
		this.configuration = configuration;
	}
	
	@Override
	public String toString() {
		return "Player {name: "+this.name +" }";
	}
	
}
