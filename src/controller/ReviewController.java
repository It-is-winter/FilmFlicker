package controller;

import java.util.List;

import exception.DeleteException;
import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DTO.MovieDTO;
import management.DTO.ReviewDTO;
import management.DTO.ReviewEtcDTO;
import management.DTO.UsersDTO;
import service.ReviewEtcService;
import service.ReviewService;
import service.Impl.ReviewServiceImpl;
import view.FailView;
import view.SuccessView;

public class ReviewController {
	
	public static ReviewService service = new ReviewServiceImpl();
	public static ReviewEtcService etcService = new ReviewServiceImpl();
	
//=======================Review controller===========================

	/***
	 * 
	 * @param movieName
	 * @param review
	 * @param movieScore
	 * @param user
	 * 
	 * user를 입력 받아 입력받은 user에 리뷰를 추가하는 거다.
	 * @throws SearchException 
	 */

	/***
	 * @param ReviewDTO
	 * 리뷰 입력하기
	 */
	public static void insertReview(ReviewDTO review) throws SearchException {
		
		try {
			service.insertReview(review);
			SuccessView.successMessage("리뷰 등록에 성공했습니다.");
		} catch (InsertException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/***
	 * @param ReviewDTO
	 * 리뷰 수정하기
	 */
	public static void updateReview(ReviewDTO review) throws UpdateException {

		try {
			service.updateReview(review);
			SuccessView.successMessage("리뷰 수정에 성공했습니다.");
		} catch (UpdateException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/***
	 * @param ReviewDTO
	 * 리뷰 수정하기
	 */
	public static void deleteReview(ReviewDTO review) throws DeleteException {

		try {
			service.deleteReview(review);
			SuccessView.successMessage("리뷰 삭제에 성공했습니다.");
		} catch (DeleteException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	
	/***
	 * @param ReviewDTO
	 * 해당 리뷰(1개) 출력하기 (user_seq 와 movie_seq 필요)
	 */
	public static void selectReview(ReviewDTO review) {
		try {
			service.selectReview(review);
			SuccessView.successReview(review);
		} catch (SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/***
	 * @param ReviewDTO
	 * 영화로 리뷰 검색
	 * 해당 영화에 달린 리뷰의 리스트 전체 출력하기
	 */
	public static void selectReviewByMovie(MovieDTO movie) {
		try {
			List<ReviewDTO> list = service.selectReviewByMovie(movie);
			SuccessView.successReviewList(list);
		} catch (SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/***
	 * @param ReviewDTO
	 * 사용자로 리뷰 검색
	 * 해당 유저가 단 리뷰의 리스트 전체 출력하기
	 */
	public static void selectReviewByUser(UsersDTO user) {
		try {
			List<ReviewDTO> list = service.selectReviewByUser(user);
			SuccessView.successReviewList(list);
		} catch (SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
//=======================ReviewEtc controller===========================	
	
	/***
	 * @param ReviewDTO
	 * 해당 리뷰에 대한 ReviewEtcDTO 반환
	 */
	public static ReviewEtcDTO selectReviewEtc(ReviewDTO review) throws SearchException {
		ReviewEtcDTO reviewEtcDTO = new ReviewEtcDTO();
		try {
			reviewEtcDTO = etcService.selectReviewEtc(review);
		} catch (SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
		return reviewEtcDTO;
	}
	
	/***
	 * @param ReviewEtcDTO
	 * 리뷰에 좋아요 또는 싫어요 입력하기
	 */
	public static void insertLike(ReviewEtcDTO reviewEtc) throws SearchException {

		try {
			etcService.insertLike(reviewEtc);
		} catch (InsertException e) {
			FailView.errorMessage(e.getMessage());
		} catch (UpdateException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public static void insertHate(ReviewEtcDTO reviewEtc) throws SearchException {

		try {
			etcService.insertHate(reviewEtc);
			SuccessView.successMessage("싫어요 등록에 성공했습니다.");
		} catch (InsertException e) {
			FailView.errorMessage(e.getMessage());
		} catch (UpdateException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/***
	 * @param ReviewEtcDTO
	 * 리뷰에 좋아요 또는 싫어요 수정하기
	 */
//	public static void updateLikeReview(ReviewEtcDTO reviewEtc) throws UpdateException {
//
//		try {
//			etcService.updateLike(reviewEtc);
//			SuccessView.successMessage("좋아요/싫어요 수정에 성공했습니다.");
//		} catch (UpdateException e) {
//			//e.printStackTrace();
//			FailView.errorMessage(e.getMessage());
//		}
//	}
	
	/***
	 * @param ReviewDTO
	 * 해당 리뷰에 달린 좋아요 개수 반환
	 */
	public static int countLike(ReviewDTO review) throws SearchException {
		int result = etcService.countLike(review);
		return result;
	}
	
	/***
	 * @param ReviewDTO
	 * 해당 리뷰에 달린 싫어요 개수 반환
	 */
	public static int countHate(ReviewDTO review) throws SearchException {
		int result = etcService.countHate(review);
		return result;
	}
	
}
