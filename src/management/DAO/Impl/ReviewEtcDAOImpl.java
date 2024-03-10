package management.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DAO.interfaces.ReviewEtcDAO;
import management.DTO.ReviewDTO;
import management.DTO.ReviewEtcDTO;
import management.DTO.UsersDTO;
import util.DbManager;

public class ReviewEtcDAOImpl implements ReviewEtcDAO {
	
	@Override //  해당 리뷰에 대한 ReviewEtcDTO 반환
	public ReviewEtcDTO selectReviewEtc(ReviewDTO review) throws SearchException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ReviewEtcDTO reviewEtc = new ReviewEtcDTO();
		String sql = "select * from review_etc where review_seq = ?";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, review.getReviewSeq());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				reviewEtc = new ReviewEtcDTO(rs.getInt("user_seq"), 
						rs.getInt("review_seq"), rs.getInt("like_dislike"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SearchException("리뷰가 존재하지 않습니다.");
		} finally {
			DbManager.close(con, ps, rs);
		}
		
		return reviewEtc;
	}
	
	@Override// 해당 유저가 해당 리뷰에 좋아요/싫어요를 입력했는지 여부 판단 -> 이미 입력했으면 true, 아직 안한거면 false
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
		String sql = "insert into review_etc(REVIEW_ETC_SEQ, USER_SEQ, REVIEW_SEQ, LIKE_DISLIKE, REG_DATE) "
				+ "values(REVIEW_ETC_SEQ_NO.NEXTVAL, ?, ?, 1, sysdate)";
		int result = 0;
		
		try {
			con = DbManager.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, reviewEtc.getUserSeq());
			ps.setInt(2, reviewEtc.getReviewSeq());
			
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
	public int insertHate(ReviewEtcDTO reviewEtc) throws InsertException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into review_etc(REVIEW_ETC_SEQ, USER_SEQ, REVIEW_SEQ, LIKE_DISLIKE, REG_DATE) "
				+ "values(REVIEW_ETC_SEQ_NO.NEXTVAL, ?, ?, -1, sysdate)";
		int result = 0;
		
		try {
			con = DbManager.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, reviewEtc.getUserSeq());
			ps.setInt(2, reviewEtc.getReviewSeq());
			
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
	public int updateLike(UsersDTO user,ReviewEtcDTO reviewEtc) throws UpdateException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE /*+ NO_PARALLEL(review_etc) */ review_etc "
				+ "SET LIKE_DISLIKE = ? "
				+ "WHERE review_seq = ? AND user_seq = ?";
		int result = 0;
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, reviewEtc.getLike());
			ps.setInt(2, reviewEtc.getReviewSeq());
			ps.setInt(3, user.getUserSeq());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UpdateException("좋아요/싫어요 입력에 실패했습니다.");
		}  finally {
			DbManager.close(con, ps, null);
		}
		
		return result;
	}

	@Override // 해당 리뷰의 좋아요의 개수 (like_dislike = 1인 것의 개수)
	public int countLike(ReviewDTO review) throws SearchException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int result = 0;
		String sql = "select count(*) from review_etc where review_seq = ? and like_dislike = 1";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, review.getReviewSeq());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SearchException("좋아요 개수 파악에 실패했습니다.");
		} finally {
			DbManager.close(con, ps, rs);
		}
		
		return result;
	} // select문 같이

	@Override // 해당 리뷰의 싫어요의 개수 (like_dislike = -1인 것의 개수)
	public int countHate(ReviewDTO review) throws SearchException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int result = 0;
		String sql = "select count(*) from review_etc where review_seq = ? and like_dislike = -1";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, review.getReviewSeq());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SearchException("좋아요 개수 파악에 실패했습니다.");
		} finally {
			DbManager.close(con, ps, rs);
		}
		
		return result;
	}
}
