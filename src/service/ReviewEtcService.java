package service;

import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DTO.ReviewDTO;
import management.DTO.ReviewEtcDTO;
import management.DTO.UsersDTO;

public interface ReviewEtcService {
	/*
	 * ReviewEtcDAO에 있는 기능 모두 호출, 예외 던져주기
	 */
	
	/**
	 * 해당 리뷰에 좋아요 또는 싫어요 누르기 -> 1은 좋아요 -1은 싫어요 default값은 0
	 */
	public void insertLike(UsersDTO user,ReviewEtcDTO reviewEtc) throws UpdateException, InsertException, SearchException;
	public void insertHate(UsersDTO user,ReviewEtcDTO reviewEtc) throws UpdateException, InsertException, SearchException;
	
	/**
	 * 리뷰에 대한 좋아요, 싫어요
	 * 
	 */
	//public void updateLike(ReviewEtcDTO reviewEtc) throws UpdateException, SearchException;
	
	/**
	 * 좋아요 개수
	 */
	public int countLike(ReviewDTO review) throws SearchException;
	
	/**
	 * 싫어요 개수
	 */
	public int countHate(ReviewDTO review) throws SearchException;

	public ReviewEtcDTO selectReviewEtc(ReviewDTO review) throws SearchException;
	
}
