package com.ucm.asp.veena.make_your_own_cake.dao.impl;

import java.io.InputStream;
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
			cakes.setAmount((double)row.get("AMOUNT"));
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
			int number_of_images= getJdbcTemplate().queryForObject(
					getImageCountSql, new Object[]{}, int.class);
			
			//userId will be 1 more than the count of users in the system
			imgId = number_of_images+1;
			
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
    
    

}
