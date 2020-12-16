package com.example.practice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jni.User;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Transactional(rollbackFor = Exception.class)
	public void execute() throws Exception{
		System.out.println("INSERT START");
		jdbcTemplate.update("INSERT INTO user_data (id, name, birthday) values ('001','John','20010901')"
				+ ",('002','Tom','19980610'),('003','Lisa','20021101')");
	}

	@Transactional(rollbackFor = Exception.class)
	public void update(UserData userData) throws Exception{
		System.out.println("update");
		if (checkUser(userData)) {
			System.out.println("更新します。");
			jdbcTemplate.update("UPDATE user_data SET name = ?"
					+ "WHERE id = ?", new Object[]{userData.getName(),userData.getId()});         			
		} 
	}	

	@Transactional(rollbackFor = Exception.class)
	public void delete(UserData userData) throws Exception{
		System.out.println("delete");
		if (checkUser(userData)) {
			System.out.println("削除します。");
			jdbcTemplate.update("DELETE FROM user_data　WHERE id = ?", new Object[]{userData.getId()});        			
		} 	       
	}
		
    public java.util.Map<String, Object> select(String id) {	
      // 実行する SQL を組み立てて実行
      String query = "SELECT * FROM user_data WHERE id = ?";
      java.util.Map<String, Object> oneUser = jdbcTemplate.queryForMap(query, id);
      return oneUser;
    }
	
	
    // データを返す
    public boolean checkUser(UserData userData) throws Exception{
    	System.out.println("findAll");
      // 実行する SQL を組み立てて実行
      String query = "SELECT * FROM user_data WHERE id = ?";
      java.util.Map<String, Object> oneUser = jdbcTemplate.queryForMap(query, userData.getId());
              
      if (!oneUser.isEmpty() && oneUser.get("id") != null) {
    	  System.out.println("IDが存在する");
    	  System.out.println("ID：　" + oneUser.get("id"));
    	 return true; 
      }
      System.out.println("IDが存在しない");
      return false; 
    }	
		
}    

