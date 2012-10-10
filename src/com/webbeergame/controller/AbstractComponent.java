/**
 * 
 */
package com.webbeergame.controller;

/**
 * @author luis
 *
 */
public abstract class AbstractComponent implements Component{
	private Integer delay1;
	private Integer delay2;
	private Integer inventory;
	private Integer totalCost;
	private Integer backOrder;
	private Integer recentOrder;
	private Player player;
	
	@Override
	public Integer getDelay1() {
		return delay1;
	}
	public void setDelay1(Integer delay1) {
		this.delay1 = delay1;
	}
	@Override
	public Integer getDelay2() {
		return delay2;
	}
	public void setDelay2(Integer delay2) {
		this.delay2 = delay2;
	}
	@Override
	public Integer getInventory() {
		return inventory;
	}
	
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	
	@Override
	public Integer getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}
	@Override
	public Integer getBackOrder() {
		return backOrder;
	}
	
	public void setBackOrder(Integer backOrder) {
		this.backOrder = backOrder;
	}
	@Override
	public Integer getRecentOrder() {
		return recentOrder;
	}
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
}
