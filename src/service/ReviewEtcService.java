package service;

import management.DTO.ReviewDTO;

public interface ReviewEtcService {
	/*
	 * ReviewEtcDAO에 있는 기능 모두 호출, 예외 던져주기
	 */
	
	/**
	 * 리뷰에 대한 좋아요, 싫어요
	 */
	public int updateLike(ReviewDTO review) /*throws UpdateException*/;
	
	/**
	 * 좋아요 개수
	 */
	public int countLike(ReviewDTO review) /*throws SearchException*/;
	
	/**
	 * 싫어요 개수
	 */
	public int countHate(ReviewDTO review) /*throws SearchException*/;

}
