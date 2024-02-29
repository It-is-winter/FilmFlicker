package service;

import management.DTO.MovieDTO;
import management.DTO.ReviewDTO;

public interface ReviewService {
	/*
	 * ReviewDAO에 있는 기능 모두 호출, 예외 던져주기
	 */
	
	/**
	 * 리뷰 등록
	 */
	public int insertReview(MovieDTO movie) /*throws InsertException*/;
	
	/**
	 * 리뷰 수정
	 */
	public int updateReview(MovieDTO movie) /*throws UpdateException*/;
	
	/**
	 * 리뷰 삭제
	 */
	public int deleteReview(MovieDTO movie) /*throws DeleteException*/;
	
	/**
	 * 영화 정보로 리뷰 검색
	 */
	public ReviewDTO selectReview(MovieDTO movie) /*throws SearchException*/; //선택된 리뷰를 movieDAO에 전달

}
