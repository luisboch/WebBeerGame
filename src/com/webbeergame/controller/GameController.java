/**
 * 
 */
package com.webbeergame.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luis
 *
 */
public class GameController implements PlayerListener{
	
	/**
	 * Usado para contar os rounds já jogados
	 */
	private Integer round;
	
	private List<Component> components;
	
	/**
	 * Este atributo controla quais componentes já
	 * tiveram suas ações executadas para o round corrente.
	 */
	private Map<Component, Boolean> controller;

	
	public List<Component> getComponents() {
		return components ==  null? components = new ArrayList<Component>():components;
	}
	
	public void nextRound() {
		clearRound();
		nextPlayer();
		round++;
	}
	
	private void nextPlayer() {
		for(Component c:components){
			if(!getController().get(c.getPlayer())){
				c.getPlayer().setPlaying(true);
				break;
			}
		}
	}

	public void configure() throws ControllerException{
		
		// start counter;
		round = 0;
		
		List<Component> components = getComponents();
		if(components.isEmpty()){
			throw new ControllerException("Need one or more players to star new Game");
		}
		boolean retailer = false;
		boolean wholesale= false;
		boolean distributor = false;
		boolean factory = false;
		
		for(Component c:components){
			if(c instanceof Retailer){
				retailer = true;
			} else if (c instanceof Wholesale){
				wholesale = true;
			} else if (c instanceof Distributor) {
				distributor = true;
			} else if (c instanceof Factory) {
				factory = true;
			}
		}
		
		Component c;
		Player p;
		
		if(!retailer){
			p = new IAPlayer();
			c = new Retailer();
			c.setPlayer(p);
			components.add(c);
		}
		
		if(!wholesale){
			p = new IAPlayer();
			c = new Wholesale();
			c.setPlayer(p);
			components.add(c);
		}
		
		if(!distributor){
			p = new IAPlayer();
			c = new Distributor();
			c.setPlayer(p);
			components.add(c);
		}
		
		if(!factory){
			p = new IAPlayer();
			c = new Factory();
			c.setPlayer(p);
			components.add(c);
		}
		
		// Reorder components.
		Collections.sort(components);
		
	}
	
	/**
	 * Listener para a pagina jsf verificar alterações.
	 */
	public void checkChanges(){
	}
	
	public void clearRound(){
		for(Component c:getComponents()){
			getController().put(c, false);
		}
	}
	@Override
	public void finishPlay(Player player) {
		getController().put(player.getComponent(), true);
		nextPlayer();
	}
	
	public Map<Component, Boolean> getController() {
		return controller == null ? controller = new LinkedHashMap<Component, Boolean>():controller;
	}
	public void setPlayer(Component component){
		getComponents().add(component);
	}
}
