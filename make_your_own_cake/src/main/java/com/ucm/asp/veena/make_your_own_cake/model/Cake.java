package com.ucm.asp.veena.make_your_own_cake.model;

import java.io.InputStream;
import java.sql.Blob;

public class Cake {
	  private String cakeId;
	  private String sid;

	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getCakeId() {
		return cakeId;
	}
	private String cakeName;
	  private int stock;
	  private float amount;
	  private String type;
	  private InputStream imageStream;

	public InputStream getImageStream() {
		return imageStream;
	}
	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
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
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
