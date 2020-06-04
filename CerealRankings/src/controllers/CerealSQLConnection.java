package controllers;

import java.sql.Connection;
import entity.Cereal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CerealSQLConnection {

	public static ArrayList<Cereal> getCereals()
	{
		ArrayList<Cereal> al = new ArrayList<Cereal>();
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = SQLCredentials.getConnection();
			
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM cerealranks ORDER BY `rank` asc;");
			
			while(rs.next()) {
				Cereal cTemp = new Cereal();
				cTemp.setName(rs.getString("cerealname"));
				cTemp.setRank(rs.getInt("rank"));
				al.add(cTemp);
			}
		} catch (SQLException sqle) {
			System.out.println (sqle.getMessage());
			System.out.println("sqlexception");
		} catch (ClassNotFoundException cnfe) {
			System.out.println (cnfe.getMessage());
			System.out.println("classnotfound exception");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("SQL Exception"+sqle.getMessage());
			}
		}
		
		//System.out.println("" + al.get(0).getName() + ", " + al.get(1).getName());
		
		return al;
	}
 		
	public static void rankUp(Cereal cereal)
	{		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		int currRank = cereal.getRank();
		int desiredRank = currRank - 1;
		
		if (currRank != 1)
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			
				conn = SQLCredentials.getConnection();
			
				st = conn.createStatement();
			
			
			
				st.execute("UPDATE cerealranks SET `rank` = (CASE `rank` "
						+ "WHEN '" + currRank + "' THEN '" + desiredRank + "' "
						+ "WHEN '" + desiredRank + "' THEN '" + currRank + "' "
						+ "ELSE `rank` "
						+ "END) "
						+ "WHERE `rank`='" + currRank + "' || `rank`='"+ desiredRank + "';");			
			
			} catch (SQLException sqle) {
				System.out.println (sqle.getMessage());
				System.out.println("sqlexception");
			} catch (ClassNotFoundException cnfe) {
				System.out.println (cnfe.getMessage());
				System.out.println("classnotfound exception");
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException sqle) {
					System.out.println("SQL Exception"+sqle.getMessage());
				}
			}
		}	
	}
	
	public static void rankDown(Cereal cereal)
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Statement st2 = null;
		ResultSet rs2 = null;
		
		int currRank = cereal.getRank();
		int desiredRank = currRank + 1;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = SQLCredentials.getConnection();
				
			st = conn.createStatement();
			st2 = conn.createStatement();
					
			rs2 = st2.executeQuery("SELECT * FROM cerealranks");
			rs2.last();
			int rows = rs2.getRow();
			
			if (currRank != rows)
			{	
				st.execute("UPDATE cerealranks SET `rank` = (CASE `rank` "
						+ "WHEN '" + currRank + "' THEN '" + desiredRank + "' "
						+ "WHEN '" + desiredRank + "' THEN '" + currRank + "' "
						+ "ELSE `rank` "
						+ "END) "
						+ "WHERE `rank`='" + currRank + "' || `rank`='"+ desiredRank + "';");			
			}
			
		} catch (SQLException sqle) {
			System.out.println (sqle.getMessage());
			System.out.println("sqlexception");
		} catch (ClassNotFoundException cnfe) {
			System.out.println (cnfe.getMessage());
			System.out.println("classnotfound exception");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (rs2 != null) {
					rs2.close();
				}
				if (st2 != null) {
					st2.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("SQL Exception"+sqle.getMessage());
			}
		}
	}
	
	public static boolean addCereal(String cereal)
	{
		boolean addedcereal = false;
		
		Connection conn = null;
		Statement st = null;
		Statement st2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = SQLCredentials.getConnection();
			
			st = conn.createStatement();
			st2 = conn.createStatement();
			
			rs = st.executeQuery("SELECT * from cerealranks where cerealname=\"" + cereal + "\"");
			
			rs2 = st2.executeQuery("SELECT * FROM cerealranks");
			rs2.last();
			int rows = rs2.getRow() + 1;
			
			// Checks that the cereal is not already stored in the database. If not, creates a new cereal
			
			if(!rs.next())
			{	
				st.execute("INSERT INTO cerealranks VALUES ('" + cereal + "','" + rows + "');");
				addedcereal = true;
			} else {
				addedcereal = false;
				System.out.println("Cereal NOT added.");
			}
			
			return addedcereal;
			
		} catch (SQLException sqle) {
			System.out.println (sqle.getMessage());
			System.out.println("sqlexception");
		} catch (ClassNotFoundException cnfe) {
			System.out.println (cnfe.getMessage());
			System.out.println("classnotfound exception");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rs2 != null) {
					rs2.close();
				}
				if (st != null) {
					st.close();
				}
				if (st2 != null) {
					st2.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("SQL Exception"+sqle.getMessage());
			}
		}
		
		return addedcereal;
	}

	public static void deleteCereal(Cereal cereal)
	{	
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		Statement st2 = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = SQLCredentials.getConnection();
			
			st = conn.createStatement();
			st2 = conn.createStatement();
			
			st.execute("DELETE FROM cerealranks WHERE cerealname=\"" + cereal.getName() + "\";");
			
			st.close();
			
			rs = st2.executeQuery("SELECT * FROM cerealranks WHERE `rank`>'" + cereal.getRank() + "' ORDER BY `rank` asc");
			
			while(rs.next())
			{
				Cereal cTemp = new Cereal();
				
				cTemp.setName(rs.getString("cerealname"));
				cTemp.setRank(rs.getInt("rank"));
				
				System.out.println(cTemp.getName() + ", " + cTemp.getRank());
				
				
				rankUp(cTemp);				
			}
			
		} catch (SQLException sqle) {
			System.out.println (sqle.getMessage());
			System.out.println("sqlexception");
		} catch (ClassNotFoundException cnfe) {
			System.out.println (cnfe.getMessage());
			System.out.println("classnotfound exception");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (st2 != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("SQL Exception"+sqle.getMessage());
			}
		}
	}
}
