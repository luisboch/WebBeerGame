/**
 * 
 */
package com.webbeergame.controller;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author luis
 * 
 */
public class IAPlayer extends Player{
	List<Integer> facingOrders = new LinkedList<Integer>();
	Integer requestedOrders  = 0;
	Integer receivedOrders = 0;
	
	private static final Integer MIN_INVENTORY = 4;
	private static final Integer ORDER = 30;
	
	private static final Logger log = 
			Logger.getLogger(IAPlayer.class.getSimpleName());
	
	private static final long serialVersionUID = -3618590255968518579L;
	private boolean orderRealized = false;
	@Override
	public void setPlaying(boolean playing) {
		
		super.setPlaying(playing);
		
		if(playing){
			
			if(orderRealized){
				receivedOrders = receivedOrders + this.getComponent().getDelay1();
				if(receivedOrders >= requestedOrders){
					orderRealized = false;
					receivedOrders = 0;
					requestedOrders = 0;
				}
			}
			
			Integer inventory = 
					this.getComponent().getInventory()
					- this.getComponent().getFacingOrder() 
					+ this.getComponent().getDelay1() 
					- this.getComponent().getBackOrder();
			
			if(inventory < MIN_INVENTORY && !orderRealized){
				Integer requestingOrder;
				if(getComponent().getFacingOrder() + getComponent().getBackOrder() > ORDER){
					requestingOrder = getComponent().getFacingOrder() + getComponent().getBackOrder() ;
				} else {
					requestingOrder = IAPlayer.ORDER;
				}
				log.log(Level.INFO, "Player {} as requesting order: "+ORDER, this.getName());
				this.setChoice(requestingOrder);
				orderRealized = true;
				requestedOrders=requestingOrder+ getComponent().getDelay2();
				receivedOrders = 0;
			} else {
				this.setChoice(0);
			}
			notifyListener();
			
		}
	}
	
}
