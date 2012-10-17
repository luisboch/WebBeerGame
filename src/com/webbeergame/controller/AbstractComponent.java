/**
 * 
 */
package com.webbeergame.controller;

import java.io.Serializable;

/**
 * @author luis
 *
 */
public abstract class AbstractComponent implements Component, Serializable{
	

	
	private static final long serialVersionUID = 8890976788930142003L;
	private Integer delay1;
	private Integer delay2;
	private Integer inventory;
	private Integer totalCost;
	private Integer backOrder;
	private Integer recentOrder;
	private Player player;
	
	private Integer facingOrder;
	private Integer supplied;
	
	public AbstractComponent() {
		this.delay1 = 0;
		this.delay2 = 0;
		this.inventory = 0;
		this.totalCost = 0;
		this.backOrder = 0;
		this.recentOrder = 0;
	}
	
	@Override
	public Integer getDelay1() {
		return delay1;
	}

	@Override
	public void setDelay1(Integer delay1) {
		this.delay1 = delay1;
	}
	@Override
	public Integer getDelay2() {
		return delay2;
	}

	@Override
	public void setDelay2(Integer delay2) {
		this.delay2 = delay2;
	}
	
	@Override
	public Integer getInventory() {
		return inventory;
	}	

	@Override
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	@Override
	public Integer getFacingOrder() {
		return facingOrder;
	}


	@Override
	public void setFacingOrder(Integer facingOrder) {
		this.facingOrder = facingOrder;
	}


	@Override
	public Integer getSupplied() {
		return supplied;
	}


	@Override
	public void setSupplied(Integer supplied) {
		this.supplied = supplied;
	}

	@Override
	public Integer getTotalCost() {
		return totalCost;
	}

	@Override
	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}
	@Override
	public Integer getBackOrder() {
		return backOrder;
	}
	
	@Override
	public void setBackOrder(Integer backOrder) {
		this.backOrder = backOrder;
	}
	@Override
	public Integer getRecentOrder() {
		return recentOrder;
	}

	@Override
	public void setRecentOrder(Integer recentOrder) {
		this.recentOrder = recentOrder;
	}
	@Override
	public Player getPlayer() {
		return player;
	}
	@Override
	public void setPlayer(Player player) {
		this.player = player;
		if(player.getComponent() == null || !player.getComponent().equals(this)){
			player.setComponent(this);
		}
	}
	
	@Override
	public String toString() {
		return "AbstractComponent {delay1=" + delay1 + ", delay2=" + delay2
				+ ", inventory=" + inventory + ", totalCost=" + totalCost
				+ ", backOrder=" + backOrder + ", recentOrder=" + recentOrder
				+ ", player=" + player + "}";
	}
	
	/**
	 * Usado para ordenar os componentes do jogo.
	 * @return this order.
	 */
	
	@Override
	public int compareTo(Component o) {
		return Integer.valueOf(o.getOrder()).compareTo(this.getOrder());
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Component){
		
			Component c= (Component) obj;
			
			if(c.getOrder() == this.getOrder()){
				return super.equals(obj);
			}
		} 
		
		return false;
	}
	
	@Override
	public void setNewOrder(Integer order) {
		this.facingOrder = order;
	}
}

