package view;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import controller.ReviewController;
import exception.SearchException;
import management.DTO.DipsDTO;
import management.DTO.MovieDTO;
import management.DTO.ReviewDTO;
import management.DTO.UsersDTO;

public class SuccessView {
	
	/***
	 * 
	 * @param message
	 * 성공했을때 메시지 띄움.
	 */
	public static void successMessage(String message){
		System.out.println(message);
	}
	/*
	 * 감독 이름으로 검색 성공했을때 띄움
	 */
	
	public static void successMessage(List<MovieDTO> moviedirector) {

		for (MovieDTO movie : moviedirector) {
			System.out.println(movie);

		}

	}


	/***
	 * 
	 * @param movie
	 * movieDto만 띄울때 사용 -> 안에 list들은 따로 매소드 만들어서 for문 돌려야함.
	 */
	public static void successMovie(MovieDTO movie){
		System.out.println(movie);
	}
	
	/***
	 * @param review
	 * 리뷰 호출에 성공했을때 ReviewDTO의 toString() 출력
	 */
	public static void successReview(ReviewDTO review){
		System.out.println(review);
	}
	/***
	 * @param review
	 * selectReviewByMovie() 또는 selectReviewByUser() 호출에 성공했을때 
	 * 리턴한 List<ReviewDTO>안의 값들을 출력
	 * @throws SearchException 
	 */
	public static void successReviewList(List<ReviewDTO> list) throws SearchException{
		int cnt = 1;
		
	    Collections.sort(list, new Comparator<ReviewDTO>() {
	        @Override
	        public int compare(ReviewDTO review1, ReviewDTO review2) {
	            // Compare reviewSeq of review1 and review2
	            return Integer.compare(review1.getReviewSeq(), review2.getReviewSeq());
	        }
	    });
		
		for(ReviewDTO review : list) {
			System.out.println("● 리뷰 " + cnt++ + " : " + review);
			
			int like = ReviewController.countLike(review);
			int hate = ReviewController.countHate(review);
			
			System.out.print("  -> 이 리뷰가 도움이 됐나요?   ");
			System.out.println("좋아요 " + like + "   싫어요 " + hate);
			System.out.println("=====================================================");
		}
	}

	public static void printPassword(UsersDTO user) {
		System.out.println("회원의 비밀번호 => "+user.getPassword());
		
	}

	public static void dipsList(List<DipsDTO> dips) {
		System.out.println("===== 회원님의 찜목록 =======");
		int i =0;
		for (DipsDTO dipsDTO : dips) {
			i++;
			System.out.println(i+"." + dipsDTO);
		}
		
	}

}
	