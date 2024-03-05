package management.DAO.interfaces;

import java.util.List;

import exception.DeleteException;
import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DTO.MovieDTO;
import management.DTO.ReviewDTO;
import management.DTO.UsersDTO;

public interface ReviewDAO {
	/**
	 * 리뷰 등록
	 */
	public int insertReview(ReviewDTO review) throws InsertException;
	/**
	 * 중복된 리뷰인지 확인
	 */
	public boolean isExist(ReviewDTO review) throws SearchException;
	
	/**
	 * 리뷰 수정
	 */
	public int updateReview(ReviewDTO review) throws UpdateException;
	
	/**
	 * 리뷰 삭제
	 */
	public int deleteReview(ReviewDTO review) throws DeleteException;
	
	/**
	 * 영화와 유저 정보로 리뷰 검색
	 */
	public ReviewDTO selectReview(MovieDTO movie, UsersDTO user) throws SearchException;
	public ReviewDTO selectReview(ReviewDTO review) throws SearchException; //선택된 리뷰를 movieDAO에 전달
	
	/**
	 * 해당 영화의 리뷰 검색
	 */
	public List<ReviewDTO> selectReviewByMovie(MovieDTO movie) throws SearchException;
	
	/**
	 * 해당 유저의 리뷰 검색
	 */
	public List<ReviewDTO> selectReviewByUser(UsersDTO user) throws SearchException;

}
