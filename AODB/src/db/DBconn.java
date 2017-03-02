package db;

import java.sql.*;
import java.util.Vector;



public class DBconn {
	@SuppressWarnings("rawtypes")
	private Vector dataset;
	int rowCount = 0;
	int colCount = 0;
	public String[] type = null;
	boolean flag = true;
	private static String tablename;
	
	public DBconn(String tbname){
		DBconn.tablename=tbname;
	}
	public static Connection getconn() {


		String url = "jdbc:mysql://localhost:3306/"+tablename;
		String user = "root";
		String psw = "";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, psw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConn(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int update(String sql) {
		Connection conn = DBconn.getconn();
		Statement stmt = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			count = stmt.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			// System.out.print(e.getErrorCode());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBconn.closeConn(conn);
		}
		return count;
	}
	@SuppressWarnings("rawtypes")
	public String[][] getData(String sql) {

		query(sql);
		String dsString[][] = new String[rowCount][colCount];
		if (flag == true) {
			dsString = null;
		} else {
			for (int i = 0; i < rowCount; i++) {
				// System.out.println();
				Vector row = new Vector();
				row = (Vector) dataset.get(i);
				for (int j = 0; j < colCount; j++) {

					dsString[i][j] = (String) row.get(j);

				}
			}
		}
		dataset.clear();
		return dsString;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int query(String sql) {
		Connection conn = DBconn.getconn();
		dataset = new Vector();
		Statement stmt = null;
		ResultSet rs = null;
		rowCount = 0;
		colCount = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			flag = true;
			while (rs.next()) {
				flag = false;
				rowCount++;
				ResultSetMetaData rsmd = rs.getMetaData();
				Vector row = new Vector();
				colCount = rsmd.getColumnCount();
				for (int i = 0; i < colCount; i++) {
					row.add(rs.getString(i + 1));
					// System.out.println(rs.getString(i+1));
				}
				dataset.add(row);
			}
		} catch (SQLException e) {
			System.out.print(e.getErrorCode());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBconn.closeConn(conn);
		}
		return rowCount;
	}
}
