package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbManager {
	private static Properties proFile = new Properties();

	/**
	 * 로드
	 */
	static {
		try {
			
			//C:\Edu\JavaWorks\FilmFlicker
			Class.forName(DBProperties.DRIVER_NAME);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static Properties getProFile() {
		return proFile;
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				DBProperties.URL,
				DBProperties.USER_ID,
				DBProperties.USER_PASS);
	}
	

	public static void close(Connection con, Statement st, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(con != null) con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
