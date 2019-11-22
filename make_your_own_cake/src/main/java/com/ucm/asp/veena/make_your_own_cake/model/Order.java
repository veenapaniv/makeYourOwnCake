package com.ucm.asp.veena.make_your_own_cake.model;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

public class Order {
	
	private int orderId;
	private String cakeName;
	private int qty;
	private String username;
	private String shippingAddress;
	private float amount;
	private String userId;
	private File uploadedFile;
	private String message;
	private String cakeId;
	private String order_status;
	private InputStream customImage;
	
	public InputStream getCustomImage() {
		return customImage;
	}
	public void setCustomImage(InputStream customImage) {
		this.customImage = customImage;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getCakeName() {
		return cakeName;
	}
	public void setCakeName(String cakeName) {
		this.cakeName = cakeName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCakeId() {
		return cakeId;
	}
	public void setCakeId(String cakeId) {
		this.cakeId = cakeId;
	}
	
	public File getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(File uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

}
