package controller;


import exception.InsertException;
import management.DTO.MovieDTO;
import management.DTO.UsersDTO;
import service.ReviewService;
import service.Impl.ReviewServiceImpl;
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

	public static void insertReview(String movieName, String review, int movieScore,UsersDTO user) {
		
		try {
			service.insertReview(movieName,review,movieScore,user);
			SuccessView.successMessage("등록에 성공했습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	


}
