package com.webbeergame.controller;

public interface Component extends Comparable<Component> {
	Integer getDelay1();
	Integer getDelay2();
	Integer getInventory();
	Integer getTotalCost();
	Integer getBackOrder();
	Integer getRecentOrder();
	void setDelay1(Integer delay1);
	void setDelay2(Integer delay2);
	void setInventory(Integer inventory);
	void setTotalCost(Integer totaCost);
	void setBackOrder(Integer backOrder);
	void setRecentOrder(Integer recentOrder);
	Player getPlayer();
	void setPlayer(Player player);
	int getOrder();
	void setNewOrder(Integer order);
	
	Integer getFacingOrder();
	void setFacingOrder(Integer order);
	
	Integer getSupplied();
	void setSupplied(Integer supplied);
}
