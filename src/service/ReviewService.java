package service;

import java.util.List;

import exception.DeleteException;
import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DTO.MovieDTO;
import management.DTO.ReviewDTO;
import management.DTO.ReviewEtcDTO;
import management.DTO.UsersDTO;

public interface ReviewService {
	/*
	 * ReviewDAO에 있는 기능 모두 호출, 예외 던져주기
	 */
	
	/**
	 * 리뷰 등록
	 */
	public void insertReview(ReviewDTO review, MovieDTO movie, UsersDTO user) throws InsertException;
	
	/**
	 * 리뷰 수정
	 */
	public void updateReview(ReviewDTO review, MovieDTO movie, UsersDTO user) throws UpdateException;
	
	/**
	 * 리뷰 삭제
	 */
	public void deleteReview(ReviewDTO review) throws DeleteException;
	
	/**
	 * 리뷰 검색
	 */
	public ReviewDTO selectReview(ReviewDTO review) throws SearchException; //선택된 리뷰를 movieDAO에 전달
	/**
	 * 영화 정보로 리뷰 검색
	 */
	public List<ReviewDTO> selectReviewByMovie(MovieDTO movie) throws SearchException;
	/**
	 * 유저 정보로 리뷰 검색
	 */
	public List<ReviewDTO> selectReviewByUser(UsersDTO user) throws SearchException;
	
	/**
	 * 해당 리뷰에 좋아요 또는 싫어요 누르기 -> 1은 좋아요 -1은 싫어요 default값은 0
	 */
	public void insertLikeReview(ReviewEtcDTO reviewEtc) throws InsertException;

	/**
	 * 해당 리뷰에 좋아요 또는 싫어요 수정하기
	 */
	public void updateLikeReview(ReviewEtcDTO reviewEtc) throws UpdateException;
	
	/**
	 * 해당 리뷰에 찍힌 좋아요 개수
	 */
	public int countLike(ReviewDTO review) throws SearchException ;
	
	/**
	 * 해당 리뷰에 찍힌 싫어요 개수
	 */
	public int countHate(ReviewDTO review) throws SearchException ;

	
}
