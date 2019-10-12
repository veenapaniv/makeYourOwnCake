package com.ucm.asp.veena.make_your_own_cake.dao.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ucm.asp.veena.make_your_own_cake.dao.OrderDao;
import com.ucm.asp.veena.make_your_own_cake.model.Cake;
import com.ucm.asp.veena.make_your_own_cake.model.Order;

public class OrderDaoImpl extends JdbcDaoSupport implements OrderDao {
	@Autowired
	DataSource dataSource;
	
	@Value("${spring.datasource.url}")
	String databaseURL;
    String user = "root";
    String password = "Sriram@1";
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public List<Cake> getAllCakes() {
		String getCakesQuery = "SELECT * FROM Cake";
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(getCakesQuery);
		List<Cake> cakeList = new ArrayList<Cake>();
		
		for(Map<String, Object> row:rows) {
			Cake cakes = new Cake();
			cakes.setCakeId((String)row.get("CID"));
			cakes.setCakeName((String)row.get("CAKENAME"));
			cakes.setStock((int)row.get("STOCK"));
			cakes.setAmount((double)row.get("AMOUNT"));
			cakes.setType((String) row.get("TYPE"));
			
			cakeList.add(cakes);
			
		}
		
		return cakeList;
	}
	
	@Override
	public List<Order> getAllOrders() {
		String getCakesQuery = "SELECT * FROM Orders";
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(getCakesQuery);
		List<Order> orderList = new ArrayList<Order>();
		
		for(Map<String, Object> row:rows) {
			Order orders = new Order();
			orders.setCakeId((String)row.get("CID"));
			orders.setCakeName((String)row.get("CAKENAME"));
			orders.setQty((int)row.get("QTY"));
			orders.setUsername((String)row.get("USERNAME"));
			orders.setShippingAddress((String) row.get("S_ADDRESS"));
			orders.setMessage((String) row.get("MESSAGE"));
			
			orderList.add(orders);
			
		}
		
		return orderList;
	}

	@Override
	public String getCakeID(String cakeName) {
		String getCakesIdQuery = "SELECT * FROM Cake where cakename="+cakeName;
		
		return getJdbcTemplate().queryForObject(
				getCakesIdQuery, new Object[]{cakeName}, String.class);
	}
	
	public void insertOrder(Order order) {
		try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			String insertInventory = "INSERT INTO orders values (?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insertInventory);
			String getOrderCountSql = "select * from Orders";
			List<Map<String,Object>> number_of_orders= getJdbcTemplate().queryForList(getOrderCountSql);
			
			//orderId will be 1 more than the count of orders in the system
			int orderId = number_of_orders.size()+1;
			//int userId = 
			statement.setInt(1, orderId);
			statement.setString(2,order.getCakeName());
			statement.setInt(3, order.getQty());
			statement.setString(4,"veenapani");
			statement.setString(5,order.getShippingAddress());
			statement.setString(6, "");
			statement.setInt(8, 1);
			statement.setString(7, order.getMessage());
			statement.setString(9, getCakeID(order.getCakeName()));
			statement.setDouble(10, order.getAmount());
			
			
		}catch (SQLException ex) {
            ex.printStackTrace();
        }  
	}
	
	public void updateOrder(Order order) {
		try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			String updateInventory = "update inventory " +
					"set cname = ?, qty = ?, s_address = ?, image = ?, msg =?, amount = ? where ProductId = "+order.getCakeId();
			PreparedStatement statement = connection.prepareStatement(updateInventory);
            statement.setString(1, order.getCakeName());
            statement.setInt(2, order.getQty());
            statement.setString(3, order.getShippingAddress());
            statement.setString(4, "");
            statement.setString(5, order.getMessage());
            statement.setFloat(6, order.getAmount());
            statement.executeUpdate();
          
		}catch (SQLException ex) {
            ex.printStackTrace();
        }  
	}
	

}
