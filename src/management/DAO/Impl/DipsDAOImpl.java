package management.DAO.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
	 * select * from view_dips_info where user_seq = ?
	 * @throws SearchException 
	 */
	
	@Override
	public List<DipsDTO> selectDipsListAll(UsersDTO users) throws SQLException, SearchException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DipsDTO dips = null;
		List<DipsDTO> list = new ArrayList<DipsDTO>();
		String sql = "select * from view_dips_info where user_seq = ?";
		
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
	public int insertDirectorDips(UsersDTO user) throws IOException, SQLException {
	
		
		List<String> movieSeqDips = new ArrayList<String>();

		System.out.println("찜목록에 추가 할 영화 번호 입력 공백 구분");
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String movieSeqInput = bf.readLine(); // IOException
		movieSeqDips.addAll(Arrays.asList(movieSeqInput.split(" ")));

		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "insert into dips (dips_seq,user_seq,movie_seq) values (dips_seq_no.nextval,?,?)";

		// 받은 movieseq반복 저장?
		for (String movieSeq : movieSeqDips) {
			int movieSeqAsInt = Integer.parseInt(movieSeq);
			System.out.println("movieSeq"+movieSeq);//삭제
			try {
				System.out.println("try");
				con = DbManager.getConnection();
				ps = con.prepareStatement(sql);
				ps.setInt(1, user.getUserSeq());
				ps.setInt(2, movieSeqAsInt);
				result += ps.executeUpdate();
				System.out.println("result =" + result); //삭제
			
			}catch (SQLException e) {
				e.printStackTrace();
				System.out.println("catch");
			
			}finally {
			}

				DbManager.close(con, ps, null);

		}
			System.out.println(result);
			return result;
	}
	
	
	@Override
	public int deleteDips(UsersDTO users) {
		// TODO Auto-generated method stub
		return 0;
	}

}
