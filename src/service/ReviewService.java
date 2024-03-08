package service;

import java.util.List;

import exception.DeleteException;
import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DTO.MovieDTO;
import management.DTO.ReviewDTO;
import management.DTO.UsersDTO;

public interface ReviewService {
	/*
	 * ReviewDAO에 있는 기능 모두 호출, 예외 던져주기
	 */
	
	/**
	 * 리뷰 등록
	 */
	public void insertReview(ReviewDTO review) throws InsertException, SearchException;
	
	/**
	 * 리뷰 수정
	 */
	public void updateReview(ReviewDTO review) throws UpdateException;
	
	/**
	 * 리뷰 삭제
	 */
	public void deleteReview(ReviewDTO review) throws DeleteException;
	
	/**
	 * 리뷰 검색
	 */
	public ReviewDTO selectReview(ReviewDTO review) throws SearchException; //선택된 리뷰를 movieDAO에 전달
	
	public ReviewDTO selectReview(MovieDTO movie, UsersDTO user) throws SearchException;
	/**
	 * 영화 정보로 리뷰 검색
	 */
	public List<ReviewDTO> selectReviewByMovie(MovieDTO movie) throws SearchException;
	/**
	 * 유저 정보로 리뷰 검색
	 */
	public List<ReviewDTO> selectReviewByUser(UsersDTO user) throws SearchException;

}
