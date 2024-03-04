package management.DAO.interfaces;

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
	public int insertReview(ReviewDTO review, MovieDTO movie, UsersDTO user) throws InsertException;
	
	/**
	 * 리뷰 수정
	 */
	public int updateReview(ReviewDTO review, MovieDTO movie, UsersDTO user) throws UpdateException;
	
	/**
	 * 리뷰 삭제
	 */
	public int deleteReview(ReviewDTO review, MovieDTO movie, UsersDTO user) throws DeleteException;
	
	/**
	 * 영화와 유저 정보로 리뷰 검색
	 */
	public ReviewDTO selectReview(MovieDTO movie, UsersDTO user) throws SearchException; //선택된 리뷰를 movieDAO에 전달
	
	/**
	 * 해당 영화의 리뷰 검색
	 */
	public ReviewDTO selectReview(MovieDTO movie) throws SearchException;
	
	/**
	 * 해당 유저의 리뷰 검색
	 */
	public ReviewDTO selectReview(UsersDTO user) throws SearchException;

}
