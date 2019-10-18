package com.ucm.asp.veena.make_your_own_cake.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucm.asp.veena.make_your_own_cake.dao.CakeDao;
import com.ucm.asp.veena.make_your_own_cake.dao.OrderDao;
import com.ucm.asp.veena.make_your_own_cake.model.Cake;
import com.ucm.asp.veena.make_your_own_cake.service.CakeService;

@Service
public class CakeServiceImpl implements CakeService{

	@Autowired
	CakeDao cakeDao;
	
	@Override
	public void insertCake(Cake cake) {
		
		cakeDao.insertCake(cake);
	}

}
