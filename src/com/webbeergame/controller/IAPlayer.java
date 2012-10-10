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
public class IAPlayer extends Player{
	
	@Override
	public void setPlaying(boolean playing) {
		super.setPlaying(playing);
		// Gerar os valores de seleção do jogador
		notifyListener();
	}
	
}
