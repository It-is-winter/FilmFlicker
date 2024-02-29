package management.DAO.interfaces;

import management.DTO.ReviewDTO;

public interface ReviewEtcDAO {
	/**
	 * 리뷰에 대한 좋아요, 싫어요
	 */
	public int updateLike(ReviewDTO review);
	
	/**
	 * 좋아요 개수
	 */
	public int countLike(ReviewDTO review);
	
	/**
	 * 싫어요 개수
	 */
	public int countHate(ReviewDTO review);

}
