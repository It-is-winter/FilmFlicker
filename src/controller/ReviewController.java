package controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import exception.DeleteException;
import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DTO.MovieDTO;
import management.DTO.ReviewDTO;
import management.DTO.ReviewEtcDTO;
import management.DTO.UsersDTO;
import service.ReviewService;
import service.Impl.ReviewServiceImpl;
import session.UsersSession;
import session.UsersSessionSet;
import view.FailView;
import view.SuccessView;

public class ReviewController {
	
	public static ReviewService service = new ReviewServiceImpl();
	
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

//	public static void insertReview(String movieName, String review, int movieScore,UsersDTO user) {
//		
//		try {
//			service.insertReview(movieName,review,movieScore,user);
//			SuccessView.successMessage("등록에 성공했습니다.");
//		}catch (Exception e) {
//			e.printStackTrace();
//			FailView.errorMessage(e.getMessage());
//		}
//	}
	
	public static void insertReview(ReviewDTO review) {

		try {
			service.insertReview(review);
			SuccessView.successMessage("리뷰 등록에 성공했습니다.");
		} catch (InsertException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		} catch (SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/***
	 * @param ReviewEtcDTO
	 * 리뷰에 좋아요 또는 싫어요 입력하기
	 */
	public static void updateLikeReview(ReviewEtcDTO reviewEtc) throws UpdateException {

		try {
			service.updateLikeReview(reviewEtc);
			SuccessView.successMessage("좋아요/싫어요 수정에 성공했습니다.");
		} catch (UpdateException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/***
	 * @param ReviewDTO
	 * 해당 리뷰에 달린 좋아요 개수 반환
	 */
	public static int countLike(ReviewDTO review) throws SearchException {
		int result = service.countLike(review);
		return result;
	}
	
	/***
	 * @param ReviewDTO
	 * 해당 리뷰에 달린 싫어요 개수 반환
	 */
	public static int countHate(ReviewDTO review) throws SearchException {
		int result = service.countHate(review);
		return result;
	}

	public static void selectReviewByMovie(MovieDTO movie) {
		// TODO Auto-generated method stub
		
	}
	
}
