/**
 * 
 */
package com.webbeergame.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;

/**
 * @author luis
 */
public class GameController implements PlayerListener, Serializable{
	
	private static final Logger log = 
			Logger.getLogger(GameController.class.getSimpleName());
	
	private static final long serialVersionUID = 1L;

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

		round++;
		log.log(Level.INFO,"Starting round: {0}", round);
		clearRound();
		nextPlayer();
		
	}
	
	private void nextPlayer() {
		for(Component c:components){
			if(!getController().get(c)){
				
				log.log(Level.INFO,"Player {0} is Playing", c.getPlayer().getName());
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
			p.setName("COM - Retailer");
			
			p.addListener(this);
			
			c.setPlayer(p);
			
			log.info("Creating COM player of Retailer");
			
			components.add(c);
		}
		
		if(!wholesale){
			p = new IAPlayer();
			c = new Wholesale();
			p.setName("COM - Wholesale");
			
			p.addListener(this);
			
			c.setPlayer(p);
			log.info("Creating COM player of Wholesale");
			components.add(c);
		}
		
		if(!distributor){
			p = new IAPlayer();
			p.setName("COM - Distributor");
			c = new Distributor();

			p.addListener(this);
			
			c.setPlayer(p);
			log.info("Creating COM player of Distributor");
			components.add(c);
		}
		
		if(!factory){
			p = new IAPlayer();
			p.setName("COM - Factory");

			p.addListener(this);
			
			c = new Factory();
			c.setPlayer(p);
			log.info("Creating COM player of Factory");
			components.add(c);
		}
		
		// Reorder components.
		Collections.sort(components);
		
		this.components = components;
		
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
		log.log(Level.INFO, "Size of Map {0}", getController().size());
	}
	
	@Override
	public void finishPlay(Player player) {

		log.log(Level.INFO,"Player {0} finish Play, going next", player.getName());
		log.log(Level.INFO,"Component found {0}", player.getComponent());
		
		getController().put(player.getComponent(), true);
		player.setPlaying(false);
		nextPlayer();
	}
	
	public Map<Component, Boolean> getController() {
		return controller == null ? controller = new LinkedHashMap<Component, Boolean>():controller;
	}
	public void setPlayer(Component component){
		component.getPlayer().addListener(this);
		getComponents().add(component);
	}
	
	public void clear(){
		getComponents().clear();
		getController().clear();
	}
}
