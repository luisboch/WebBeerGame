/**
 * 
 */
package com.webbeergame.controller;
import java.util.logging.Logger;

/**
 * @author luis
 * 
 */
public class IAPlayer extends Player{
	
	private static final Logger log = 
			Logger.getLogger(IAPlayer.class.getSimpleName());
	
	private static final long serialVersionUID = -3618590255968518579L;

	@Override
	public void setPlaying(boolean playing) {
		super.setPlaying(playing);
		
		if(playing){
			
			// TODO melhorar calculo de order
			
			this.setChoice(this.getComponent().getFacingOrder());
			
			log.info("Player "+getName()+" notifing listeners");
			
			notifyListener();
		}
	}
	
}
