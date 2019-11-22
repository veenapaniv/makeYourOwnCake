package com.ucm.asp.veena.make_your_own_cake.dao.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ucm.asp.veena.make_your_own_cake.dao.OrderDao;
import com.ucm.asp.veena.make_your_own_cake.model.Cake;
import com.ucm.asp.veena.make_your_own_cake.model.Order;
import com.ucm.asp.veena.make_your_own_cake.model.User;

@Repository
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
			cakes.setAmount((float)row.get("AMOUNT"));
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
			orders.setOrderId((int)row.get("OID"));
			orders.setCakeId((String)row.get("CID"));
			orders.setCakeName((String)row.get("CNAME"));
			orders.setQty((int)row.get("QTY"));
			orders.setUsername((String)row.get("USERNAME"));
			orders.setShippingAddress((String) row.get("S_ADDRESS"));
			orders.setMessage((String) row.get("MSG"));
			orders.setOrder_status((String)row.get("ORDER_STATUS"));
			orders.setAmount((float)row.get("amount"));
			orders.setUserId((String)row.get("UID"));
			orderList.add(orders);
			
		}
		
		return orderList;
	}
	
	@Override
	public List<Order> getUserOrders(String userId) {
		
		String getCakesQuery = "SELECT * FROM Orders where uid = "+userId;
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(getCakesQuery);
		List<Order> orderList = new ArrayList<Order>();
		
		for(Map<String, Object> row:rows) {
			Order orders = new Order();
			orders.setOrderId((int)row.get("OID"));
			orders.setCakeId((String)row.get("CID"));
			orders.setCakeName((String)row.get("CNAME"));
			orders.setQty((int)row.get("QTY"));
			orders.setUsername((String)row.get("USERNAME"));
			orders.setShippingAddress((String) row.get("S_ADDRESS"));
			orders.setMessage((String) row.get("MSG"));
			orders.setOrder_status((String)row.get("ORDER_STATUS"));
			orders.setAmount((float)row.get("amount"));
			orders.setUserId((String)row.get("UID"));
			orderList.add(orders);
			
		}
		
		return orderList;
	}
	
	/**
	 * Returns a user object by ID
	 */
	@Override
	public Order getOrderById(int id) {
		String getCakesQuery = "SELECT * FROM Orders WHERE oid = "+id;
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(getCakesQuery);
		Order order = new Order();
		
		for(Map<String, Object> row:rows) {
				order.setOrderId(id);
				order.setCakeName((String)row.get("CNAME"));
				order.setQty((int)row.get("QTY"));
				order.setUsername((String)row.get("username"));
				order.setShippingAddress((String)row.get("s_address"));
				order.setMessage((String)row.get("msg"));
				order.setUserId((String)row.get("uid"));
				order.setCakeId((String)row.get("cid"));
				order.setOrder_status((String)row.get("order_status"));
				order.setAmount((float)row.get("amount"));
				
			}
		return order;
		
	}
	
	@Override
	public List<Order> getNewOrders() {
		String getCakesQuery = "SELECT * FROM Orders where ORDER_STATUS = \'NEW\'";
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(getCakesQuery);
		List<Order> orderList = new ArrayList<Order>();
		
		for(Map<String, Object> row:rows) {
			Order orders = new Order();
			orders.setCakeId((String)row.get("CID"));
			orders.setCakeName((String)row.get("CNAME"));
			orders.setQty((int)row.get("QTY"));
			orders.setUsername((String)row.get("USERNAME"));
			orders.setShippingAddress((String) row.get("S_ADDRESS"));
			orders.setMessage((String) row.get("MSG"));
			orders.setOrder_status((String)row.get("ORDER_STATUS"));
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
		Boolean customCake = false;

		try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			String insertInventory = "INSERT INTO orders values (?, ?, ?, ?, ?, ?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insertInventory);
			//String getOrderCountSql = "select max(oid) from Orders";
			int number_of_orders= createOrderId() ;
//					getJdbcTemplate().queryForObject(
//					getOrderCountSql, new Object[]{}, int.class);
			
			String cakeNameQuery = "select cakename from Cake where cid = ?";
			String cakeName;
			String query;
			String imgId;
			float amount = 0;
			
			if(order.getCakeId() != null) {
				 cakeName = getJdbcTemplate().queryForObject(cakeNameQuery, new Object[] { order.getCakeId() }, String.class);
				 query = "select imgId from Cake where cid = ?";
				 imgId = getJdbcTemplate().queryForObject(query, new Object[] { order.getCakeId() }, String.class);
				 statement.setString(1,cakeName);
				 String getCakeAmount = "select amount from cake where cid = ? ";
				amount = getJdbcTemplate().queryForObject(getCakeAmount, new Object[] { order.getCakeId() }, float.class);
			}
			else {
				customCake = true;
				String getMaxImgId = "Select max(imgId) from Images";
				String maxImgId = getJdbcTemplate().queryForObject(getMaxImgId, new Object[] {}, String.class);
				maxImgId = maxImgId.substring(0, maxImgId.length()-1);
				int maxImgIdint = Integer.parseInt(maxImgId) + 1;
				
				imgId = maxImgIdint+  "C";
				statement.setString(1,"customCake");
				try(Connection imgConnection = DriverManager.getConnection(databaseURL, user, password)){
					String insertCakeImg = "INSERT INTO IMAGES values (?,?)";
					PreparedStatement imgInsertstatement = imgConnection.prepareStatement(insertCakeImg);
					imgInsertstatement.setString(1, imgId);
					InputStream imageStream = order.getCustomImage();
					if (imageStream != null) {
						// fetches input stream of the upload file for the blob column
						imgInsertstatement.setBlob(2, imageStream);
					}
					imgInsertstatement.executeUpdate();
				}catch (SQLException e) {
					
				}	
				
			}
			
			//orderId will be 1 more than the count of orders in the system
			//int orderId = number_of_orders;
			//statement.setInt(1, orderId);
			statement.setInt(2, order.getQty());
			statement.setString(3,order.getUsername());
			statement.setString(4,order.getShippingAddress());
			statement.setString(5, order.getMessage());
			statement.setString(6, order.getUserId());
			statement.setString(7, order.getCakeId());
			statement.setFloat(8, order.getAmount());
			if(customCake)
				statement.setFloat(8, 100);
			else
				statement.setFloat(8, amount);
			
			statement.setString(9, order.getOrder_status());
			statement.setString(10, imgId);
			statement.setInt(11, number_of_orders);
			statement.executeUpdate();
		}catch (SQLException ex) {
            ex.printStackTrace();
        }  
	}
	

	public int createOrderId() {
		int id= (int) Math.round((Math.random()) *100000);
		while(!validProductId(id)) {
			id= (int) Math.round((Math.random()) *100000);
		}
		return id;
	}
	
	public boolean validProductId(int oid) {
		String productQuery = "Select oid from orders where oid="+oid;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(productQuery);
		if(rows.size() > 0) {
			return false;
		}else {
			return true;
		}
	}


	@Override
	public void updateOrder(Order order) {
		try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			String updateInventory = "update orders " +
					"set cname = ?, qty = ?, imgId=?, username = ?, s_address = ?, msg =?, amount = ?, order_status = ?, cid = ? where OID = "+order.getOrderId();
			PreparedStatement statement = connection.prepareStatement(updateInventory);
			String cakeNameQuery = "select cakeName from Cake where cid = ?";
			String cname = getJdbcTemplate().queryForObject(cakeNameQuery, new Object[] { order.getCakeId() }, String.class);
            statement.setString(1, cname);
            statement.setInt(2, order.getQty());
            String query = "select imgId from Cake where cid = ?";
			String imgId = getJdbcTemplate().queryForObject(query, new Object[] { order.getCakeId() }, String.class);
			float amount;
			String getCakeAmount = "select amount from cake where cid = ? ";
			amount = getJdbcTemplate().queryForObject(getCakeAmount, new Object[] { order.getCakeId() }, float.class);
			statement.setString(3, imgId);
			statement.setString(4, order.getUsername());
            statement.setString(5, order.getShippingAddress());
            statement.setString(6, order.getMessage());
            statement.setFloat(7, amount);
            statement.setString(8,  order.getOrder_status());
            statement.setString(9, order.getCakeId());
            statement.executeUpdate();
		}catch (SQLException ex) {
            ex.printStackTrace();
        }  
	}

	@Override
	public Blob getPhotoById(int id) {
		String query = "select imgId from Cake where cid = ?";
		int imgId = getJdbcTemplate().queryForObject(query, new Object[] { id }, int.class);		
		String getImgQuery = "select Image from Images where imgId=?";

		Blob photo = getJdbcTemplate().queryForObject(getImgQuery, new Object[] { imgId }, Blob.class);

		return photo;
	}
	
	@Override
	public Blob getPhotoByOrderId(int id) {
		String query = "select imgId from Orders where oid = ?";
		String imgId = getJdbcTemplate().queryForObject(query, new Object[] { id }, String.class);		
		String getImgQuery = "select Image from Images where imgId=?";

		Blob photo = getJdbcTemplate().queryForObject(getImgQuery, new Object[] { imgId }, Blob.class);

		return photo;
	}
	
	@Override
	public void deleteOrder(String id) {
		String deleteProduct = "delete from Orders where oid = ?";
		getJdbcTemplate().update(deleteProduct, new Object[]{
				id
		});
		
	}
	
	@Override
	public void deleteProduct(String id) {
		String deleteProduct = "delete from Cake where cid = ?";
		getJdbcTemplate().update(deleteProduct, new Object[]{
				id
		});
		
	}
	@Override
	public List<Order> getPopularCakes() {
		String popularCakes = "SELECT cname, cid, count(cid) as orderedQty \n" + 
				"FROM Orders GROUP BY cid,cname order by orderedQty desc"  ;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(popularCakes);
		List<Order> orderList = new ArrayList<Order>();
		
		for(Map<String, Object> row:rows) {
			Order order = new Order();
			order.setCakeId((String)row.get("cid"));
			order.setCakeName((String)row.get("cname"));
			orderList.add(order);
		}
		
		return orderList;
	}
	
	@Override
	public List<Order> getPopularUsers() {
		String popularUsers = "select username, count(username) from orders\n" + 
				"group by username\n" + 
				"having count(username) > 1\n" + 
				"order by username;";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(popularUsers);
		List<Order> orderList = new ArrayList<Order>();
		Order order = new Order();
		for(Map<String, Object> row:rows) {
			order.setCakeId((String)row.get("cid"));
			order.setCakeName((String)row.get("cname"));
			order.setUsername((String)row.get("username"));
			orderList.add(order);
		}
		return orderList;
	}
}
