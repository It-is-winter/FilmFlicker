package management.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DAO.interfaces.UsersDAO;
import management.DTO.UsersDTO;
import util.DbManager;

public class UsersDAOImpl implements UsersDAO {

	/***
	 * select * from users where user_ID = ? and USER_password ?
	 * @throws SQLException 
	 */
	@Override
	public UsersDTO login(String userID, String userPassword) throws SearchException, SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from users where user_ID = ? and USER_password = ?";
		
		UsersDTO user = null;
		
		try {
			con = DbManager.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(sql);
			ps.setString(1, userID);
			ps.setString(2, userPassword);
			rs = ps.executeQuery();
			if(rs.next()) {
				user = new UsersDTO(rs.getInt("user_seq"),rs.getString("user_id"),rs.getString("user_password"),
						rs.getString("user_name"),rs.getString("user_birth"),rs.getString("reg_date"));
			}else {
				con.rollback();
			    throw new SQLException("DAO 실패...");
			}
			
			con.commit();
		}finally {
			con.commit();
			DbManager.close(con, ps, rs);
		}
		
		return user;
	}

	/***
	 * insert into users values(user_seq_NO.NEXTVAL,?,?,?,?,sysdate)
	 * @throws SQLException 
	 * @throws  
	 */
	@Override
	public int register(String userID, String userPassword, String userName, String userBirth) throws InsertException, SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into users values(user_seq_NO.NEXTVAL,?,?,?,?,sysdate)";
		
		try {
			con = DbManager.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(sql);
			ps.setString(1, userID);
			ps.setString(2, userPassword);
			ps.setString(3, userName);
			ps.setString(4, userBirth);
			
			result = ps.executeUpdate();
			
			if(result == 0) {
				con.rollback();
			   throw new SQLException("DAO 실패...");
			}
			
			con.commit();
		} 
		finally {
			con.commit();
			DbManager.close(con, ps, null);
		}
		
		return result;
	}

	/***
	 * update users set USER_password = ? where user_ID = ?
	 * @throws SQLException 
	 */
	@Override
	public int userUpdate(String userID,String userPassword) throws UpdateException,SQLException {
		
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update users set USER_password = ? where user_ID = ?";
		
		try {
			con = DbManager.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, userPassword);
			ps.setString(2, userID);
			
			result = ps.executeUpdate();
			
			if(result == 0) {
				con.rollback();
				throw new SQLException("DAO 실패...");
			}
			con.commit();
			
		} finally {
			con.commit();
			DbManager.close(con, ps, null);
		}

		return result;
	}

	/***
	 * select * from users where user_ID = ?
	 * @throws SQLException 
	 */
	@Override
	public UsersDTO searchByUserID(String userID)throws SearchException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from users where user_ID = ?";
		
		UsersDTO user = null;
		
		try {
			con = DbManager.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(sql);
			ps.setString(1, userID);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new UsersDTO(rs.getInt("user_seq"),rs.getString("user_id"),rs.getString("user_password"),
						rs.getString("user_name"),rs.getString("user_birth"),rs.getString("reg_date"));
			}else {
				con.rollback();
				throw new SQLException("DAO 실패...");
			}
			
			con.commit();
		}finally {
			con.commit();
			DbManager.close(con, ps, rs);
		}
		
		return user;
	}

}
