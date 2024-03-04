package management.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exception.InsertException;
import management.DAO.interfaces.ReviewDAO;
import management.DTO.MovieDTO;
import management.DTO.ReviewDTO;
import management.DTO.UsersDTO;
import util.DbManager;

public class ReviewDAOImpl implements ReviewDAO {

	@Override
	public int insertReview(ReviewDTO review,MovieDTO movie, UsersDTO user) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into review(review_seq, movie_seq, user_seq, review, score, reg_date) "
				+ "values(review_seq_no, ?, ?, ?, ?, sysdate)";
		int result = 0;
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, movie.getMovieSeq());
			ps.setInt(2, user.getUserSeq());
			ps.setString(3, review.getReview()); // 리뷰
			ps.setInt(4, review.getScore()); // 별점
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InsertException("리뷰 입력에 실패했습니다.");
		} finally {
			DbManager.close(con, ps, null);
		}
		
		
		return result;
	}

	@Override
	public int updateReview(MovieDTO movie) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReview(MovieDTO movie) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ReviewDTO selectReview(MovieDTO movie) {
		// TODO Auto-generated method stub
		return null;
	}

}
