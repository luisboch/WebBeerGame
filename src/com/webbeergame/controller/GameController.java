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

/**
 * @author luis
 */
public class GameController implements PlayerListener, Serializable {

	private static final Logger log = Logger.getLogger(GameController.class
			.getSimpleName());

	private static final long serialVersionUID = 1L;

	/**
	 * Usado para contar os rounds já jogados
	 */
	private Integer round;

	private List<Component> components;

	/**
	 * Este atributo controla quais componentes já tiveram suas ações executadas
	 * para o round corrente.
	 */
	private Map<Component, Boolean> controller;

	public List<Component> getComponents() {
		return components == null ? components = new ArrayList<Component>()
				: components;
	}

	public void nextRound() {

		if (canGoNextRound()) {

			round++;

			log.log(Level.INFO, "Starting round: {0}", round);

			clearRound();

			Integer r = Double.valueOf((Math.random() * 10) + "").intValue();

			Component c = getComponents().get(0);

			log.log(Level.INFO, "Setting start value ({0})to Retailer Player",
					r);

			c.setNewOrder(r);

			nextPlayer();
		}

	}

	private void checkInventory() {

		for (Component c : getComponents()) {
			c.setBackOrder(c.getBackOrder() + c.getFacingOrder());
			if (c.getInventory() > c.getBackOrder()) {
				c.setSupplied(c.getBackOrder());
				c.setInventory(c.getInventory() - c.getBackOrder());
				c.setBackOrder(0);
			} else {
				c.setBackOrder(c.getBackOrder() - c.getInventory());
				c.setSupplied(c.getInventory());
				c.setInventory(0);
			}

		}
	}

	private void moveValues() {

		List<Component> components = getComponents();

		// Load components
		Component retailer = components.get(0);
		Component wholesale = components.get(1);
		Component distributor = components.get(2);
		Component factory = components.get(3);

		// Move Retailer values;
		retailer.setInventory(retailer.getInventory() + retailer.getDelay1());
		retailer.setDelay1(retailer.getDelay2());
		retailer.setDelay2(wholesale.getSupplied());

		// Move WholeSale values;
		wholesale
				.setInventory(wholesale.getInventory() + wholesale.getDelay1());
		wholesale.setDelay1(wholesale.getDelay2());
		wholesale.setDelay2(distributor.getSupplied());

		// Move Distributor values
		distributor.setInventory(distributor.getInventory()
				+ distributor.getDelay1());
		distributor.setDelay1(distributor.getDelay2());
		distributor.setDelay2(factory.getSupplied());

		// Move Factory values
		factory.setInventory(factory.getInventory() + factory.getDelay1());
		factory.setDelay1(factory.getDelay2());
		factory.setDelay2(factory.getPlayer().getChoice());

	}

	private void calculeTotals() {
		for (Component c : getComponents()) {
			Integer currentTotal = c.getTotalCost();
			if (c.getInventory() == 0) {
				currentTotal = currentTotal + (c.getBackOrder() * 1);
			} else {
				Double d = c.getInventory().doubleValue();
				currentTotal = currentTotal + ((Double) (d * 0.5)).intValue();
			}
			c.setTotalCost(currentTotal);
		}

	}

	/**
	 * Method check if all players has played
	 * 
	 * @return true when all players are played, false otherwise.
	 */
	private boolean canGoNextRound() {
		for (Component c : getController().keySet()) {
			if (!getController().get(c)) {
				return false;
			}
		}
		return true;
	}

	private void nextPlayer() {
		for (Component c : components) {
			if (!getController().get(c)) {

				log.log(Level.INFO, "Player {0} is Playing", c.getPlayer()
						.getName());

				c.getPlayer().setPlaying(true);
				break;
			}
		}
	}

	public void configure() throws ControllerException {

		// start counter;
		round = 0;

		List<Component> components = getComponents();

		if (components.isEmpty() || components.size() > 4) {
			throw new ControllerException(
					"Need one or max 4 players to start new Game");
		}

		boolean retailer = false;
		boolean wholesale = false;
		boolean distributor = false;
		boolean factory = false;

		for (Component c : components) {
			if (c instanceof Retailer) {
				retailer = true;
			} else if (c instanceof Wholesale) {
				wholesale = true;
			} else if (c instanceof Distributor) {
				distributor = true;
			} else if (c instanceof Factory) {
				factory = true;
			}
			log.info("Player " + c.getPlayer().getName() + " has found");
			// set All players to not playing
			c.getPlayer().setPlaying(false);
		}

		Component c;
		Player p;

		if (!retailer) {
			p = new IAPlayer();
			c = new Retailer();
			p.setName("COM - Retailer");

			c.setPlayer(p);

			log.info("Creating COM player of Retailer");
			components.add(c);
		}

		if (!wholesale) {
			p = new IAPlayer();
			c = new Wholesale();
			p.setName("COM - Wholesale");

			c.setPlayer(p);
			log.info("Creating COM player of Wholesale");
			components.add(c);
		}

		if (!distributor) {
			p = new IAPlayer();
			p.setName("COM - Distributor");
			c = new Distributor();

			c.setPlayer(p);
			log.info("Creating COM player of Distributor");
			components.add(c);
		}

		if (!factory) {
			p = new IAPlayer();
			p.setName("COM - Factory");

			c = new Factory();
			c.setPlayer(p);
			log.info("Creating COM player of Factory");
			components.add(c);
		}

		// Reorder components.
		Collections.sort(components);

		this.components = components;
		Integer startValueInteger = 4;
		
		for(Component c1:getComponents()){
			c1.setDelay1(startValueInteger);
			c1.setInventory(startValueInteger);
			c1.setDelay2(startValueInteger);
			c1.getPlayer().clearListeners();
			c1.getPlayer().addListener(this);
		}

	}

	/**
	 * Listener para a pagina jsf verificar alterações.
	 */
	public void checkChanges() {
	}

	public void clearRound() {
		for (Component c : getComponents()) {
			getController().put(c, false);
		}
		log.log(Level.INFO, "Size of Map {0}", getController().size());
	}

	@Override
	public void finishPlay(Player player) {

		log.log(Level.INFO, "Player {0} finish Play, going next",
				player.getName());
		log.log(Level.INFO, "Component found {0}", player.getComponent());

		getController().put(player.getComponent(), true);
		player.setPlaying(false);

		Integer index = 0;

		for (Component c : getComponents()) {
			if (c.equals(player.getComponent())) {
				break;
			}
			index++;
		}

		if (index != 3) {
			getComponents().get(index + 1).setNewOrder(player.getChoice());
		}
		
		if (round != 0 && canGoNextRound()) {
			checkInventory();
			moveValues();
			calculeTotals();
		}
		
		nextPlayer();

		
	}

	public Map<Component, Boolean> getController() {
		return controller == null ? controller = new LinkedHashMap<Component, Boolean>()
				: controller;
	}

	public void setPlayer(Component component) {
		getComponents().add(component);
	}

	public void clear() {
		getComponents().clear();
		getController().clear();
		log.info("Clearing Game");
	}

	public Integer getRound() {
		return round;
	}

	public boolean getCanGoNextRound() {
		return canGoNextRound();
	}
}
