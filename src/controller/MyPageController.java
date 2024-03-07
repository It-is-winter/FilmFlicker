package controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exception.DeleteException;
import exception.SearchException;
import exception.UpdateException;
import management.DTO.DipsDTO;
import management.DTO.ReviewDTO;
import management.DTO.UsersDTO;
import service.DipsService;
import service.ReviewService;
import service.Impl.DipsServiceImpl;
import service.Impl.ReviewServiceImpl;
import session.UsersSession;
import session.UsersSessionSet;
import view.FailView;
import view.SuccessView;

public class MyPageController {
	
	public static ReviewService reviewService = new ReviewServiceImpl();
	public static DipsService dipsService = new DipsServiceImpl();
	
	
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
			list = reviewService.selectReviewByUser(user);
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
			reviewService.updateReview(review);
			SuccessView.printReview(review);
		} catch(UpdateException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	/***
	 * 선택한 리뷰 삭제
	 *  */
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
		} else {
			FailView.errorMessage("삭제 실패했습니다.");
		}
			
		try {
			reviewService.deleteReview(review);
			SuccessView.successMessage("삭제 성공했습니다.");
		} catch(DeleteException e) {
			FailView.errorMessage(e.getMessage());
		}
			
	}
	/**
	 * 마이페이지에서 찜 목록 조회
	 * */
	public static void selectDips(UsersDTO user) {
		try {
			List<DipsDTO> dips = dipsService.selectDipsListAll(user);
				
				
			UsersSessionSet uss = UsersSessionSet.getInstance();
			UsersSession us = uss.get(user.getIdEmail());
				
			Map<Integer, DipsDTO>dipList = (Map<Integer, DipsDTO>)us.getAttribute("찜목록");
				
			if(dipList == null) { 
				dipList = new HashMap<>(); 
				us.setAttribute("찜목록", dipList);
			}
				
			int i = 0;
				
			for (DipsDTO dipsDTO : dips) {
				i++;
				dipList.put(i, dipsDTO);
			}
			SuccessView.dipsList(dipList);
				
		} catch (SearchException e) {
				FailView.errorMessage(e.getMessage());
		} catch(SQLException e) {
				FailView.errorMessage(e.getMessage());
		}
	}
	/**
	 * 선택한 찜 목록 삭제
	 * */
	public static void deleteMyDips(UsersDTO user, int dipsNum) {
		UsersSessionSet uss = UsersSessionSet.getInstance();
		UsersSession session = uss.get(user.getIdEmail());
		DipsDTO dips = null;
		
		Map<Integer, DipsDTO> dipsList = (Map<Integer, DipsDTO>)session.getAttribute("찜목록");
		
		Set<Map.Entry<Integer, DipsDTO>> entySet = dipsList.entrySet();
		Iterator<Map.Entry<Integer, DipsDTO>> ite = entySet.iterator();
		
		boolean isCheck = false;
		
		while(ite.hasNext()) {
			if(ite.next().getKey() == dipsNum) {
				isCheck = true;
				break;
			}
		}
		
		if(isCheck) {
			dips = dipsList.get(dipsNum);
			dipsList.remove(dipsNum);
		} else {
			FailView.errorMessage("삭제 실패했습니다.");
		}
		
		try {
			dipsService.deleteDips(dips);
			SuccessView.successMessage("삭제 성공했습니다.");
		} catch(DeleteException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

}
