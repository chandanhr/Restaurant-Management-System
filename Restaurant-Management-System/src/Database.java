
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Database {


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//createTable();
		//insertTable();
		//dropTable();
		 getConnection();

	}
	public static int createTable(String Query) throws Exception{
		try {
			Connection conn=getConnection();
			
			PreparedStatement create= conn.prepareStatement(Query);
			create.executeUpdate();
			return 1;
			
		}catch(Exception e) {System.out.println(e);}
		finally {System.out.println("s");
		return 0;}
	}
	
	public static int insertTable(String Query) throws Exception{
		try {
			Connection conn=getConnection();
			
			PreparedStatement insert= conn.prepareStatement(Query);
			
			insert.executeUpdate();
			return 1;
		}catch(Exception e) {System.out.println(e);
		return 0;
		}
	}
	
	public static ResultSet Retrieve(String Query) throws Exception {
		try {
			Connection conn = getConnection();
			
			PreparedStatement select= conn.prepareStatement(Query);
			ResultSet result = select.executeQuery();
			return result;
		
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
		
	}
	
	public static Connection getConnection() throws Exception{
		try {
			String url="jdbc:mysql://127.0.0.1:3306/restaurantmanagement";
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn=DriverManager.getConnection(url,"chandan","root");
			System.out.println("connected");
			return conn;
			
			}catch(Exception e) {System.out.println(e);}
		return null;
	}
	public static void dropTable(String Query) throws Exception {
		try {
			Connection conn=getConnection();
			
			PreparedStatement drop= conn.prepareStatement(Query);
			drop.executeQuery();
		}catch(Exception e1) {
			System.out.println(e1);
		}
	}

}
