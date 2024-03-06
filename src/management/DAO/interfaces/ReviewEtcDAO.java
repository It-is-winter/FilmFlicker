package management.DAO.interfaces;

import exception.DeleteException;
import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DTO.ReviewDTO;
import management.DTO.ReviewEtcDTO;
import management.DTO.UsersDTO;

public interface ReviewEtcDAO {
	
	/**
	 * 리뷰에 대한 좋아요, 싫어요 입력
	 */
	int insertLike(ReviewEtcDTO reviewEtc) throws InsertException;
	
	/**
	 * 리뷰에 대한 좋아요, 싫어요가 중복 되었는지 검사
	 */
	public boolean isExist(ReviewEtcDTO reviewEtc) throws SearchException;
	
	/**
	 * 리뷰에 대한 좋아요, 싫어요 수정
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
