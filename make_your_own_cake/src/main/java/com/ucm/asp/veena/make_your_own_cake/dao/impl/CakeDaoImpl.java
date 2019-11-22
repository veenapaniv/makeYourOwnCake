package com.ucm.asp.veena.make_your_own_cake.dao.impl;

import java.io.InputStream;
import java.sql.Blob;
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
import org.springframework.stereotype.Repository;

import com.ucm.asp.veena.make_your_own_cake.dao.CakeDao;
import com.ucm.asp.veena.make_your_own_cake.model.Cake;

@Repository
public class CakeDaoImpl extends JdbcDaoSupport implements CakeDao{
	
	@Autowired
	DataSource dataSource;
	
	@Value("${spring.datasource.url}")
	String databaseURL;
	
	@Value("${spring.datasource.username}")
    String user;
	
	@Value("${spring.datasource.password}")
    String password;
    
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
    
    public void insertCake(Cake cake) {
    	int imgId = 0;

    	try(Connection imgConnection = DriverManager.getConnection(databaseURL, user, password)){
    		String insertCakeImg = "INSERT INTO IMAGES values (?,?)";
    		
    		String getImageCountSql = "select max(ImgId) from Images";
    		int number_of_images = 0;
			number_of_images= createImgId();
			
			//userId will be 1 more than the count of users in the system
			imgId = number_of_images;
			
			PreparedStatement statement = imgConnection.prepareStatement(insertCakeImg);
			statement.setInt(1, imgId);
			InputStream imageStream = cake.getImageStream();
			if (imageStream != null) {
				// fetches input stream of the upload file for the blob column
				statement.setBlob(2, imageStream);
			}
			statement.executeUpdate();
    		
    	}catch(SQLException ex) {
    		ex.printStackTrace();
    	}
		try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			String insertCake = "INSERT INTO cake values (?,?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insertCake);
			String getCakeCountSql = "select max(cid) from Cake";
			int number_of_orders= getJdbcTemplate().queryForObject(
					getCakeCountSql, new Object[]{}, int.class);
			
			//orderId will be 1 more than the count of orders in the system
			int cakeId = number_of_orders+1;
			//int userId = 
			statement.setInt(1, cakeId);
			
//			InputStream imageStream = cake.getImageStream();
//			if (imageStream != null) {
//				// fetches input stream of the upload file for the blob column
//				statement.setBlob(2, imageStream);
//			}
			statement.setString(2, cake.getCakeName());
			statement.setInt(3,cake.getStock());
			statement.setDouble(4,cake.getAmount());
			statement.setString(5, cake.getType());
			statement.setInt(6, imgId);
			statement.executeUpdate();
		}catch (SQLException ex) {
            ex.printStackTrace();
        }  
	}
    
    public int createImgId() {
		int id= (int) Math.round((Math.random()) *100000);
		while(!validCakeId(id)) {
			id= (int) Math.round((Math.random()) *100000);
		}
		return id;
	}
	
	public boolean validCakeId(int oid) {
		String productQuery = "Select oid from orders where oid="+oid;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(productQuery);
		if(rows.size() > 0) {
			return false;
		}else {
			return true;
		}
	}

	public void deleteCake(String id) {
		String deleteCake = "delete from Cake where cakeId = ?";
		getJdbcTemplate().update(deleteCake, new Object[]{
				id
		});
		
	}
    
	public Cake getCakeById(String id) {
		String getCakesQuery = "SELECT * FROM Cake WHERE cid = "+id;
		
		String getImgIdQuery = "SELECT imgId FROM Cake where cid= ?";
		
		String imgId = getJdbcTemplate().queryForObject(
				 getImgIdQuery, new Object[]{id}, String.class);
		String getImgQuery = "Select image from Images where imgId = ?";
		Blob currentCakeimage = getJdbcTemplate().queryForObject(
				getImgQuery, new Object[]{imgId}, Blob.class); 
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(getCakesQuery);
		Cake cake = new Cake();
		
		for(Map<String, Object> row:rows) {
				cake.setCakeId(id);
				cake.setCakeName((String)row.get("CAKENAME"));
				cake.setStock((int)row.get("STOCK"));
				cake.setType((String)row.get("TYPE"));
//				try {
//					cake.setImageStream(currentCakeimage.getBinaryStream());
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				cake.setAmount((float)row.get("AMOUNT"));
			}
		return cake;
		
	}
	public void updateCake(Cake cake) {
		try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			String updateInventory = "update cake " +
					"set cakename = ?, stock = ?,  Type =?, amount = ? where CID = "+cake.getCakeId();
			PreparedStatement statement = connection.prepareStatement(updateInventory);
            statement.setString(1, cake.getCakeName());
            statement.setInt(2, cake.getStock());
            statement.setString(3, cake.getType());
            statement.setFloat(4, (float) cake.getAmount());
            statement.executeUpdate();
		}catch (SQLException ex) {
            ex.printStackTrace();
        }  
	}

}
