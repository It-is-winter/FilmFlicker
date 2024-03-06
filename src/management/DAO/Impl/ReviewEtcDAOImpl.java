package management.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.DeleteException;
import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DAO.interfaces.ReviewEtcDAO;
import management.DTO.ReviewDTO;
import management.DTO.ReviewEtcDTO;
import management.DTO.UsersDTO;
import util.DbManager;

public class ReviewEtcDAOImpl implements ReviewEtcDAO {
	
	public boolean isExist(ReviewEtcDTO reviewEtc) throws SearchException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from review_etc where review_seq =? and user_seq = ?";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, reviewEtc.getReviewSeq());
			ps.setInt(2, reviewEtc.getUserSeq());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
			   return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SearchException("리뷰가 존재하지 않습니다.");
		} finally {
			DbManager.close(con, ps, rs);
		}
		
		return false;
	}
	
	@Override
	public int insertLike(ReviewEtcDTO reviewEtc) throws InsertException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert review_etc(REVIEW_ETC_SEQ, USER_SEQ, REVIEW_SEQ, LIKE_DISLIKE, REG_DATE) "
				+ "values(REVIEW_ETC_SEQ_NO.NEXTVAL, ?, ?, ?, sysdate)";
		int result = 0;
		
		try {
			con = DbManager.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, reviewEtc.getUserSeq());
			ps.setInt(2, reviewEtc.getReviewSeq());
			ps.setInt(3, reviewEtc.getLike());
			
			result = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InsertException("좋아요/싫어요 입력에 실패했습니다.");
		} finally {
			DbManager.close(con, ps, null);
		}
		
		return result;
	}

	
	

	@Override
	public int updateLike(ReviewEtcDTO reviewEtc) throws UpdateException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update review_etc set LIKE_DISLIKE = ? where review_seq =?";
		int result = 0;
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, reviewEtc.getLike());
			ps.setInt(2, reviewEtc.getReviewSeq());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UpdateException("좋아요/싫어요 입력에 실패했습니다.");
		}  finally {
			DbManager.close(con, ps, null);
		}
		
		return result;
	}

	@Override
	public int countLike(ReviewDTO review) throws SearchException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int result = 0;
		String sql = "select * from review_etc where review_seq = ? and like_dislike = 1";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SearchException("좋아요 개수 파악에 실패했습니다.");
		} finally {
			DbManager.close(con, ps, rs);
		}
		
		return result;
	} // select문 같이

	@Override
	public int countHate(ReviewDTO review) throws SearchException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int result = 0;
		String sql = "select * from review_etc where like_dislike = -1";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SearchException("싫어요 개수 파악에 실패했습니다.");
		} finally {
			DbManager.close(con, ps, rs);
		}
		
		return result;
	}

	
}
