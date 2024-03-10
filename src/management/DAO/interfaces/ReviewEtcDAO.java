package management.DAO.interfaces;

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
	
	int insertHate(ReviewEtcDTO reviewEtc) throws InsertException;
	
	/**
	 * 리뷰에 대한 좋아요, 싫어요가 중복 되었는지 검사
	 */
	public boolean isExist(ReviewEtcDTO reviewEtc) throws SearchException;
	
	/**
	 * 리뷰에 대한 좋아요, 싫어요 수정
	 */
	public int updateLike(UsersDTO user,ReviewEtcDTO reviewEtc) throws UpdateException;
	
	/**
	 * 좋아요,싫어요 개수
	 */
	public int countLike(ReviewDTO review) throws SearchException;
	
	public int countHate(ReviewDTO review) throws SearchException;
	
	/**
	 * 리뷰 시퀀스에 해당하는 ReviewEtcDTO 객체를 반환하기
	 */
	public ReviewEtcDTO selectReviewEtc(ReviewDTO review) throws SearchException;
	
}
