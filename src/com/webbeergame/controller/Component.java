package com.webbeergame.controller;

public interface Component extends Comparable<Component> {
	Integer getDelay1();
	Integer getDelay2();
	Integer getInventory();
	Integer getTotalCost();
	Integer getBackOrder();
	Integer getRecentOrder();
	Player getPlayer();
	void setPlayer(Player player);
	int getOrder();
}
