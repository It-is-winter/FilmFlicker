package management.DAO.interfaces;

import exception.DeleteException;
import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DTO.ReviewDTO;
import management.DTO.ReviewEtcDTO;
import management.DTO.UsersDTO;

public interface ReviewEtcDAO {
	
	int insertLike(ReviewEtcDTO reviewEtc) throws InsertException;
	/**
	 * 리뷰에 대한 좋아요, 싫어요
	 */
	public int updateLike(ReviewEtcDTO reviewEtc) throws UpdateException;
	
	/**
	 * 좋아요 개수
	 */
	public int countLike(ReviewDTO review) throws SearchException;
	
	/**
	 * 싫어요 개수
	 */
	public int countHate(ReviewDTO review) throws SearchException;

	

}
