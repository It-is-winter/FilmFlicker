package util;
/**
 * DB의 설정 정보들 상수로 관리
 * */
public interface DBProperties {
	public static final String driverName = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@orcl_high?TNS_ADMIN=C:/Edu/Util/Wallet_orcl";
	String userId = "admin";
	String userPass = "Wjdwlsdn9561@";
	
}