package controller;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exception.DeleteException;
import exception.SearchException;
import exception.UpdateException;
import management.DTO.ReviewDTO;
import management.DTO.UsersDTO;
import service.ReviewService;
import service.Impl.ReviewServiceImpl;
import session.UsersSession;
import session.UsersSessionSet;
import view.FailView;
import view.SuccessView;

public class ReviewController {
	
	public static ReviewService service = new ReviewServiceImpl();
	

	/***
	 * 
	 * @param movieName
	 * @param review
	 * @param movieScore
	 * @param user
	 * 
	 * user를 입력 받아 입력받은 user에 리뷰를 추가하는 거다.
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
	/**
	 * 마이페이지에 리뷰 목록 가져오기
	 */
	public static List<ReviewDTO> selectReviewByUser(UsersDTO user) {
		UsersSessionSet uss = UsersSessionSet.getInstance();
		UsersSession session = uss.get(user.getIdEmail());
		
		Map<Integer, ReviewDTO> reviewList = (Map<Integer, ReviewDTO>)session.getAttribute("리뷰목록");
		
		if(reviewList == null) { 
			reviewList = new HashMap<>(); 
			session.setAttribute("리뷰목록", reviewList);
		}
		
		List<ReviewDTO> list = null;
		int i =0;
				
		try {
			list = service.selectReviewByUser(user);
		} catch(SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
		for (ReviewDTO reviewDTO : list) {
			i++;
			reviewList.put(i, reviewDTO);
		}
		
		SuccessView.printReviewByUser(reviewList);
		
		return list;
	}
	
	/**
	 * 가져온 리뷰 목록에서 리뷰 수정하기
	 */
	public static void updateMyReview(UsersDTO user, int reviewNum,String reviewComent,int reviewScore) {
		UsersSessionSet uss = UsersSessionSet.getInstance();
		UsersSession session = uss.get(user.getIdEmail());
		ReviewDTO review = null;
		
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
	 * 가져온 리뷰 삭제
	 */
	public static void deletMyReview(UsersDTO user, int reviewNum) {	
		UsersSessionSet uss = UsersSessionSet.getInstance();
		UsersSession session = uss.get(user.getIdEmail());
		ReviewDTO review = null;
		
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
			reviewList.remove(reviewNum);
		}else {
			FailView.errorMessage("삭제 실패했습니다.");
		}
		
		try {
			service.deleteReview(review);
			SuccessView.successMessage("삭제 성공했습니다.");
		} catch(DeleteException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}

}
