package util;

import java.io.FileInputStream; 
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
			//외부 properteis파일 로딩하기
			proFile.load(new FileInputStream("resources/dbInfo.properties"));
			//proFile.load(new FileInputStream("board.properties"));
			
			Class.forName(proFile.getProperty("driverName"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static Properties getProFile() {
		return proFile;
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				proFile.getProperty("url"),
				proFile.getProperty("userName"),
				proFile.getProperty("userPass"));
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
