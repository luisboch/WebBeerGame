package com.webbeergame.controller;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class GameBean implements Serializable{
	
	private static final Logger log =
			Logger.getLogger(GameBean.class.getSimpleName());
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{sessionBean}")
	private SessionBean session;
	
	//ui controlls
	private Boolean renderDialogComponentSelecion;
	private Boolean renderPlayerName;
	private Boolean renderGame;
	
	//UI selection
	private List<ComponentType> components = new LinkedList<ComponentType>();
	private ComponentType selectedComponent;
	
	
	private GameController controller;
	
	@PostConstruct
	public void postConstruct(){
		controller = new GameController();
		// show Player selection
		showPlayerName();
		
		// create new Player
		session.setPlayer(new Player());
		
		// add Components selections
		components.add(ComponentType.RETAILER);
		components.add(ComponentType.WHOLESALER);
		components.add(ComponentType.DISTRIBUTOR);
		components.add(ComponentType.FACTORY);
		selectedComponent = ComponentType.RETAILER;
		
		log.info("Starting GameBean instance");
		
	}
	
	public void newGame(){
		log.info("Displaying Component selection");
		showDialogComponentSelecion();
		
	}
	
	public void startGame(){
		showGame();
		Component c = null;
		switch (selectedComponent) {
		case RETAILER:
			c = new Retailer();
			break;
		case DISTRIBUTOR:
			c = new Distributor();
			break;
		case WHOLESALER:
			c = new Wholesale();
			break;
		case FACTORY:
			c = new Factory();
			break;
		default:
			break;
		}
		
		c.setPlayer(session.getPlayer());

		// Renew all controller properties.
		controller.clear();
		
		controller.setPlayer(c);
		
		try {
			controller.configure();
		} catch (ControllerException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	public void showDialogComponentSelecion(){
		renderDialogComponentSelecion = true;
		renderPlayerName = false;
		renderGame = false;
	}
	public void showGame(){
		renderDialogComponentSelecion = false;
		renderPlayerName = false;
		renderGame = true;
	}
	
	public void showPlayerName(){
		renderDialogComponentSelecion = false;
		renderPlayerName = true;
		renderGame = false;
	}
	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
	}

	public GameController getController() {
		return controller;
	}

	public void setController(GameController controller) {
		this.controller = controller;
	}
	

	public Boolean getRenderDialogComponentSelecion() {
		return renderDialogComponentSelecion;
	}

	public void setRenderDialogComponentSelecion(
			Boolean renderDialogComponentSelecion) {
		this.renderDialogComponentSelecion = renderDialogComponentSelecion;
	}

	public List<ComponentType> getComponents() {
		return components;
	}

	public void setComponents(List<ComponentType> components) {
		this.components = components;
	}

	public ComponentType getSelectedComponent() {
		return selectedComponent;
	}

	public void setSelectedComponent(ComponentType selectedComponent) {
		this.selectedComponent = selectedComponent;
	}

	public Boolean getRenderPlayerName() {
		return renderPlayerName;
	}

	public void setRenderPlayerName(Boolean renderPlayerName) {
		this.renderPlayerName = renderPlayerName;
	}

	public Boolean getRenderGame() {
		return renderGame;
	}

	public void setRenderGame(Boolean renderGame) {
		this.renderGame = renderGame;
	}
	
	public void logout(){
		 HttpServletRequest request = (HttpServletRequest)
	                FacesContext.getCurrentInstance().getExternalContext().getRequest();
		 request.getSession().invalidate();
	}
}
