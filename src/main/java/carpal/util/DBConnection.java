package carpal.util;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection  createConnection() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/carpal_db"; //MySQL URL followed by the database name
		String username = "root"; //MySQL username
		String password = "root"; //MySQL password
		
		try{
			
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
								
			}catch (ClassNotFoundException  e) {
				e.printStackTrace();
			}
			
			con = DriverManager.getConnection(url,username,password);
			System.out.println("Printing connection object "+con);
					
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return con;
	}
}
