package util;

// DB 설정 정보들을 상수로 관리

public interface DBProperties {
	public static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String USER_ID = "scott";
	String USER_PASS = "TIGER";
	
}
