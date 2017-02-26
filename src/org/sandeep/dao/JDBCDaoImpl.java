package org.sandeep.dao;

import org.sandeep.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;


@Component("jdbcdao")
public class JDBCDaoImpl {

	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Circle getCircle(int circleId) {
	
		Connection connection = null;
	try{	
		
		/*
		 * 
		 * This part of the code is replaced with datasource bean defined trhought which we can get
		 * connection object
		 * 
		 * 
		 * String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver).newInstance();
		
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usersdb?" +
                "user=root&password=");
		*/
		connection =  dataSource.getConnection();
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement("Select * from circle where id = ?");
		ps.setInt(1,circleId);
		Circle circle = null;
		 ResultSet rs = (ResultSet) ps.executeQuery();
		 if(rs.next()){
			 circle = new Circle(circleId, rs.getString("name"));
		 }
		 
		 rs.close();
		 ps.close();
		
		 return circle;
	}
	catch(Exception e){
		e.printStackTrace();
		throw new RuntimeException();
	}
	
		finally {

			try {
				connection.close();
			} catch (Exception e) {

			}
		}

	}	
	
	public int getCount(){
		String count = "SELECT count(*) from circle";
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.queryForInt(count);
		
	}
	
	
}
