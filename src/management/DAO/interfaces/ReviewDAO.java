package management.DAO.interfaces;

import management.DTO.MovieDTO;
import management.DTO.ReviewDTO;
import management.DTO.UsersDTO;

public interface ReviewDAO {
	/**
	 * 리뷰 등록
	 */
	public int insertReview(MovieDTO movie);
	
	/**
	 * 리뷰 수정
	 */
	public int updateReview(MovieDTO movie);
	
	/**
	 * 리뷰 삭제
	 */
	public int deleteReview(MovieDTO movie);
	
	/**
	 * 영화 정보로 리뷰 검색
	 */
	public ReviewDTO selectReview(MovieDTO movie); //선택된 리뷰를 movieDAO에 전달

	int insertReview(MovieDTO movie, UsersDTO user);

	int insertReview(ReviewDTO review, MovieDTO movie, UsersDTO user);

}
