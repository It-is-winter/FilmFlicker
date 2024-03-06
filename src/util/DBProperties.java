package util;

// DB 설정 정보들을 상수로 관리

public interface DBProperties {
	public static final String driverName = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userId = "scott";
	String userPass = "TIGER";
	
}