package com.ucm.asp.veena.make_your_own_cake.dao;

public interface LoginDao {
	boolean validateCredentials(String email, String password);
	public boolean validatePassword(String email,String password);
	public String getUserID(String email);

}
