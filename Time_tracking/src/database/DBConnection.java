package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private String dataBasesName;
	private Connection connection;  
	private Statement statement;  

	public DBConnection(){

	}

	public DBConnection (String dataBasesName){
		setDataBasesName(dataBasesName);
	}

	public int init() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}  
		}
		try {  
			Class.forName("org.sqlite.JDBC");  
			connection = DriverManager.getConnection("jdbc:sqlite:" + dataBasesName);  
		} catch (ClassNotFoundException e) {  
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	public void setDataBasesName (String dataBasesName) {
		this.dataBasesName = dataBasesName;
	}

	public String getDataBasesName () {
		return dataBasesName;
	}

	public ResultSet resultSetQuery (String query){
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		return null;
	}
	public void sqlQuery (String query){
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void finalize()
	{  
		if (statement != null) {
			try {  
				statement.close();
			} catch (Exception e){  
				e.printStackTrace();  
			}  
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
	}  
}  

