package com.company;
/*
 * Reg Hahne
 * This class works with a player in the game
 * 7/18/2016
 */

public class Player {
	private int location;
	private String name;
	
	public Player(String n) {
		this.name = n;
		this.location = 0;
	}
	
	public int getLocation() {
		return this.location;
	}
	
	public void setLocation(int s) {
		this.location = s;
	}
	
	public void move(int r) {
		if (r == 4 || r == 6)
			this.location -= r;
		else
			this.location += r;

		if (this.location < 0)
			setLocation(0);
	}
	
	public boolean collision(Player other) {
		return getLocation() == other.getLocation();
	}
	
	public String toString() {
		if (getLocation() == 0)
			return name + "-START";
		else if (getLocation() >= 40)
			return name + "-END";
		return name + "-" + getLocation();
	}
}
