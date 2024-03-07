package management.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.SearchException;
import management.DAO.interfaces.DipsDAO;
import management.DTO.DipsDTO;
import management.DTO.UsersDTO;
import util.DbManager;

public class DipsDAOImpl implements DipsDAO {

	/***
	 * 찜목록 조회
	 * view에서 user에 해당하는 것만 뽑는다.
	 * select * from view_dips where user_seq = ?
	 * @throws SearchException 
	 */
	
	@Override
	public List<DipsDTO> selectDipsListAll(UsersDTO users) throws SQLException, SearchException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DipsDTO dips = null;
		List<DipsDTO> list = new ArrayList<DipsDTO>();
		String sql = "select * from view_dips where user_seq = ?";
		
		try {
			con = DbManager.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, users.getUserSeq());
			rs = ps.executeQuery();
			
		
			while(rs.next()) {
				
				dips = new DipsDTO(rs.getString(1),rs.getString(2),
						rs.getString(3),users.getUserSeq());
				list.add(dips);
			}
			
			if (dips == null) {
				con.rollback();
				throw new SQLException("찜목록을 찾을수 없습니다.");
			}
			
		}finally {
			con.commit();
			DbManager.close(con, ps, rs);
		}
		
		return list;
	}

	@Override

	public int insertDips(UsersDTO user, int movieSeq) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "insert into dips (dips_seq,user_seq,movie_seq) values (dips_seq_no.nextval,?,?)";
		int result = 0;
		try {
			con =DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUserSeq());
			ps.setInt(2, movieSeq);
			
			result = ps.executeUpdate();
			
			
		}finally {
			DbManager.close(con, ps, null);
		}
		
		
		return result;

	}

	@Override
	public int deleteDips(UsersDTO users) {
		// TODO Auto-generated method stub
		return 0;
	}

}
