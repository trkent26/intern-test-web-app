package controllers;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

import java.util.ArrayList;


public class SQLCredentials {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		Connection conn = null;
    	
    	try {
            // The newInstance() call is a work around for some
            // broken Java implementations
    		ArrayList<String> connectionInfo = new ArrayList<>();
    		// First entry: database address e.g. jdbc:mysql://localhost:3306/cinema
    		// Second entry: username e.g. root
    		// Third entry: password e.g. BarQ17chickengarden
    		
    	
    		
    		connectionInfo.add("jdbc:mysql://localhost:3306/cerealranking");
        	connectionInfo.add("root");
        	connectionInfo.add("pr0t3ct3d!p455w0rd!"); 
    			    		
        	Class.forName("com.mysql.cj.jdbc.Driver");
    		
    		
    		
    		conn = DriverManager.getConnection(connectionInfo.get(0), 
    										   connectionInfo.get(1),
    										   connectionInfo.get(2));
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
}
