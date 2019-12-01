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

import com.ucm.asp.veena.make_your_own_cake.dao.LikeDao;
import com.ucm.asp.veena.make_your_own_cake.dao.OrderDao;
import com.ucm.asp.veena.make_your_own_cake.model.Order;
import com.ucm.asp.veena.make_your_own_cake.service.LikeService;

@Repository
public class LikeDaoImpl extends JdbcDaoSupport  implements LikeDao  {
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
	public void likeCake(String cakeId, String userId, int likes) {
		
		try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			String insertInventory = "INSERT INTO Reviews values (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insertInventory);
			//String getOrderCountSql = "select max(oid) from Orders";
			int number_of_reviews= createReviewId() ;
			statement.setInt(1, number_of_reviews);
			statement.setString(2, cakeId);
			statement.setString(3,userId);
			statement.setInt(4,likes);
			statement.setString(5,null);
			statement.executeUpdate();
		}catch (SQLException ex) {
            ex.printStackTrace();
        }  
		
		
		
	}
	
	@Override
	public int getLikes(String cakeId) {
		String getLikesQuery = "Select COUNT(likes) from Reviews where CID = "+ cakeId;
		
		int currentLikes = 0;
		try {
		currentLikes = getJdbcTemplate().queryForObject(getLikesQuery, new Object[] { }, int.class);
		}
		catch(Exception e) {
			
		}
		
		return currentLikes;
	}
	
	
	@Override
	public void saveReview(String cakeId, String userId, String review) {
		
		try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
			String insertInventory = "INSERT INTO Reviews values (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insertInventory);
			//String getOrderCountSql = "select max(oid) from Orders";
			int number_of_reviews= createReviewId() ;
			statement.setInt(1, number_of_reviews);
			statement.setString(2, cakeId);
			statement.setString(3,userId);
			statement.setInt(4,0);
			statement.setString(5,review);
			statement.executeUpdate();
		}catch (SQLException ex) {
            ex.printStackTrace();
        }  
		
		
		
	}
	
	
	public int createReviewId() {
		int id= (int) Math.round((Math.random()) *100000);
		while(!validProductId(id)) {
			id= (int) Math.round((Math.random()) *100000);
		}
		return id;
	}
	
	public boolean validProductId(int rid) {
		String productQuery = "Select reviewId from reviews where reviewId="+rid;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(productQuery);
		if(rows.size() > 0) {
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public List<Map<String, Object>> getAllReviews(String cakeId) {
		String getReviews = "select reviews from Reviews where CID ="+cakeId +" and reviews IS NOT null;";
		
		List<Map<String, Object>> reviews = getJdbcTemplate().queryForList(getReviews);
		
		
		
		
		return reviews;
	}

}
