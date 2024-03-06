package management.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.DeleteException;
import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DAO.interfaces.ReviewDAO;
import management.DTO.MovieDTO;
import management.DTO.ReviewDTO;
import management.DTO.UsersDTO;
import util.DbManager;
import view.SuccessView;

public class ReviewDAOImpl implements ReviewDAO {

	@Override
	public int insertReview(ReviewDTO review, MovieDTO movie, UsersDTO user) throws InsertException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into review(review_seq, movie_seq, user_seq, review, score, reg_date) "
				+ "values(review_seq_no.NEXTVAL, ?, ?, ?, ?, sysdate)";
		int result = 0;
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, movie.getMovieSeq());
			ps.setInt(2, user.getUser_seq());
			ps.setString(3, review.getReview()); // 리뷰
			ps.setInt(4, review.getScore()); // 별점
			
			SuccessView.successMessage("등록되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InsertException("리뷰 입력에 실패했습니다.");
		} finally {
			DbManager.close(con, ps, null);
		}
		
		return result;
	}

	@Override
	public int updateReview(ReviewDTO review, MovieDTO movie, UsersDTO user) throws UpdateException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update review set review = ?, score =? where movie_seq =? and user_seq = ?";
		int result = 0;
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, review.getReview());
			ps.setInt(2, review.getScore());
			ps.setInt(3, movie.getMovieSeq());
			ps.setInt(4, user.getUser_seq());
			
			result = ps.executeUpdate();
			
			SuccessView.successMessage("수정되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UpdateException("리뷰 수정에 실패했습니다.");
		} finally {
			DbManager.close(con, ps, null);
		}
		
		return result;
	}

	@Override
	public int deleteReview(ReviewDTO review, MovieDTO movie, UsersDTO user) throws DeleteException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete from review where movie_seq =? and user_seq = ?";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, movie.getMovieSeq());
			ps.setInt(2, user.getUser_seq());
			
			result = ps.executeUpdate();
			
			SuccessView.successMessage("삭제되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DeleteException("리뷰 삭제에 실패했습니다.");
		} finally {
			DbManager.close(con, ps, null);
		}
		
		return result;
	}

	@Override
	public ReviewDTO selectReview(MovieDTO movie, UsersDTO user) throws SearchException{ // 영화와 사용자 시퀀스로 리뷰 검색
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ReviewDTO review = null;
		String sql = "delete from review where movie_seq =? and user_seq = ?";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, movie.getMovieSeq());
			ps.setInt(2, user.getUser_seq());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				review = new ReviewDTO(rs.getInt(1), rs.getInt(2), rs.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SearchException("리뷰 수정에 실패했습니다.");
		} finally {
			DbManager.close(con, ps, rs);
		}
		
		return review;
	}

	@Override
	public ReviewDTO selectReview(MovieDTO movie) throws SearchException { // 영화 시퀀스로 리뷰 검색
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ReviewDTO review = null;
		String sql = "delete from review where movie_seq =?";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, movie.getMovieSeq());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				review = new ReviewDTO(rs.getInt(1), rs.getInt(2), rs.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SearchException("리뷰 수정에 실패했습니다.");
		} finally {
			DbManager.close(con, ps, rs);
		}
		
		return review;
	}

	@Override
	public ReviewDTO selectReview(UsersDTO user) throws SearchException { // 사용자 시퀀스로 리뷰 검색
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ReviewDTO review = null;
		String sql = "delete from review where user_seq = ?";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(2, user.getUser_seq());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				review = new ReviewDTO(rs.getInt(1), rs.getInt(2), rs.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SearchException("리뷰 수정에 실패했습니다.");
		} finally {
			DbManager.close(con, ps, rs);
		}
		
		return review;
	}

}
