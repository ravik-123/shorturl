package carpal.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import carpal.bean.ShortUrlBean;
import carpal.util.DBConnection;

public class ShortUrlDao {

	  private static final String CHAR_LIST = 
		        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final int RANDOM_STRING_LENGTH = 5;
	
	public HashMap<String, Object> saveShortUrl(ShortUrlBean obj){
	 System.out.println(obj);
	 
	 String shortstr = generateRandomString();
	 obj.setKeyCode(shortstr);
	 obj.setShort_url("http://localhost:8080/carpal/" + shortstr);
		
		HashMap<String, Object>  hashdata = new  HashMap<String, Object>();
	Connection con = DBConnection.createConnection();
	try{
	CallableStatement cbstmt = con.prepareCall("{ call usp_save_short_url(?,?,?)}");
	cbstmt.setString(1, obj.getLong_url());
	cbstmt.setString(2, obj.getShort_url());
	cbstmt.setString(3, obj.getKeyCode());
//	cbstmt.setString(4, obj.getOauthtoken());
	
	boolean hadResults  = cbstmt.execute();
	
	 while(hadResults){
		ResultSet rs = cbstmt.getResultSet();
		while (rs.next()) {
			Boolean issuccess = rs.getBoolean("issuccess");
				if(issuccess){
				hashdata.put("issuccess", true);
			hashdata.put("short_url", rs.getString("short_url"));
				hashdata.put("message",rs.getString("serviceMessage") );
			}else
			{
				hashdata.put("issuccess", false);
				hashdata.put("message",rs.getString("serviceMessage") );
				
				 ResultSetMetaData rsmd = rs.getMetaData();
				    int columns = rsmd.getColumnCount();
				    boolean iskeycodeExist =  false;
				    for (int x = 1; x <= columns; x++) {
				        if ("iskeycodeExist".equals(rsmd.getColumnName(x))) {
				        	iskeycodeExist = true;
				        	saveShortUrl(obj);
				        	}
				    }
				
			}
			
			
		}
		hadResults = cbstmt.getMoreResults();
		
	}
	 cbstmt.close();
	
	 return hashdata;
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	return null;	
	}
	
	  public String generateRandomString(){
	         
	        StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	          	            char ch = CHAR_LIST.charAt(new Random().nextInt(CHAR_LIST.length()));
	            randStr.append(ch);
	        }
	        return randStr.toString();
	    }

	public String getLongUrl(StringBuilder shortUrl) {
		
		Connection con = new DBConnection().createConnection();
		String longurl = "";
		try {
			String str = new String(shortUrl);
		PreparedStatement stmt = con.prepareStatement("select long_url from  tbl_sys_short_url where keyCode = ?");
		stmt.setString(1, str);
		
		ResultSet rs = stmt.executeQuery();
		System.out.println("Size" + rs.getFetchSize());
		while(rs.next()){
			 longurl = rs.getString("long_url");
			 System.out.println(longurl);
		}
		} catch(SQLException e){
			e.printStackTrace();
		}
	return longurl;
		
	}
}
