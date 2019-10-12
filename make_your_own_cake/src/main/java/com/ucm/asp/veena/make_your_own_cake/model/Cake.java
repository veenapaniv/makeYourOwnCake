package com.ucm.asp.veena.make_your_own_cake.model;

public class Cake {
	  private String cakeId;
	  //private String userName;
	  private String cakeName;
	  private int stock;
	  private double amount;
	  private String type;
	  private String image;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCakeId() {
		return cakeId;
	}
	public void setCakeId(String cakeId) {
		this.cakeId = cakeId;
	}
	public String getCakeName() {
		return cakeName;
	}
	public void setCakeName(String cakeName) {
		this.cakeName = cakeName;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
