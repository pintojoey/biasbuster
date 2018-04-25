package biasbuster.twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Joey
 * Database connection file used to create, connect and disconnect MySQL connection
 */
public class Database {
	public static final String DATABASE="bias_buster";//Do not change has usages in queries
	public static final String USERNAME="root";//Username for mysql connection
	public static final String PASSWORD="joey";//Password for MySQL connection
	
	public static final String MYSQL_SERVER="localhost:3306";
	
	public static Connection getConnection() throws SQLException{ //method to create new connection
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://"+MYSQL_SERVER+"/"+DATABASE,USERNAME,PASSWORD); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); //Indicates driver not installed
			throw new SQLException(" com.mysql.jdbc.Driver Driver Not Found!");
		}  
		return con; 
	}

	public static void closeConnection(Connection connection, ResultSet rs, PreparedStatement proc) throws SQLException { //method to close connection,result set and prepared statement
		if(proc!=null){
			proc.close();	
		}
		if(rs!=null){
			rs.close();	
		}
		if(connection!=null){
			connection.close();	
		}

	}
}
