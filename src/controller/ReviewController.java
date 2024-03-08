package controller;

import java.util.Iterator;
import java.util.List;
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
import service.ReviewEtcService;
import service.ReviewService;
import service.Impl.ReviewServiceImpl;
import session.UsersSession;
import session.UsersSessionSet;
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
		} catch(SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 가져온 리뷰 목록에서 리뷰 수정하기
	 */
	public static void updateMyReview(UsersDTO user, int reviewNum,String reviewComent,int reviewScore) {
		UsersSessionSet uss = UsersSessionSet.getInstance();
		UsersSession session = uss.get(user.getIdEmail());
		ReviewDTO review = null;
		
		@SuppressWarnings("unchecked")
		Map<Integer, ReviewDTO> reviewList = (Map<Integer, ReviewDTO>)session.getAttribute("리뷰목록");
		
		Set<Map.Entry<Integer, ReviewDTO>> entySet = reviewList.entrySet();
		Iterator<Map.Entry<Integer, ReviewDTO>> ite = entySet.iterator();
		
		boolean isCheck = false;
		
		
		while(ite.hasNext()) {
			if(ite.next().getKey() == reviewNum) {
				isCheck = true;
				break;
			}
		}
		
		if(isCheck) {
			review = reviewList.get(reviewNum);
			review.setReview(reviewComent);
			review.setScore(reviewScore);
			
		}
		else {
			FailView.errorMessage("수정이 완료되지 못했습니다.");
		}
		
		try {
			service.updateReview(review);
			SuccessView.printReview(review);
		} catch(UpdateException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/***
	 * @param ReviewDTO
	 * 리뷰 수정하기
	 */
	public static void deleteReview(ReviewDTO review) {

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
	 * @return 
	 */
	public static List<ReviewDTO> selectReviewByMovie(MovieDTO movie) {
		List<ReviewDTO> list = null;
		try {
			list = service.selectReviewByMovie(movie);
			SuccessView.successReviewList(list);
		} catch (SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
		return list;
	}
	
	/***
	 * @param ReviewDTO
	 * 사용자로 리뷰 검색
	 * 해당 유저가 단 리뷰의 리스트 전체 출력하기
	 */
//	public static void selectReviewByUser(UsersDTO user) {
//		try {
//			List<ReviewDTO> list = service.selectReviewByUser(user);
//			SuccessView.successReviewList(list);
//		} catch (SearchException e) {
//			FailView.errorMessage(e.getMessage());
//		}
//	}
	
//=======================ReviewEtc controller===========================	
	
	/***
	 * @param ReviewDTO
	 * 해당 리뷰에 대한 ReviewEtcDTO 반환
	 */
	public static ReviewEtcDTO selectReviewEtc(ReviewDTO review) {
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
	public static void insertLike(ReviewEtcDTO reviewEtc) {

		try {
			etcService.insertLike(reviewEtc);
		} catch (InsertException e) {
			FailView.errorMessage(e.getMessage());
		} catch (UpdateException e) {
			FailView.errorMessage(e.getMessage());
		} catch(SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public static void insertHate(ReviewEtcDTO reviewEtc) {

		try {
			etcService.insertHate(reviewEtc);
		} catch (InsertException e) {
			FailView.errorMessage(e.getMessage());
		} catch (UpdateException e) {
			FailView.errorMessage(e.getMessage());
		} catch(SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/***
	 * @param ReviewDTO
	 * 해당 리뷰에 달린 좋아요 개수 반환
	 */
	public static int countLike(ReviewDTO review) {
		int result = 0;
		try {
			result = etcService.countLike(review);
		} catch(SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
		return result;
	}
	
	/***
	 * @param ReviewDTO
	 * 해당 리뷰에 달린 싫어요 개수 반환
	 */
	public static int countHate(ReviewDTO review){
		int result = 0;
		try {
			result = etcService.countHate(review);
		} catch(SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
		return result;
	}
	
}
