package view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	
	public static void successMessage(List<MovieDTO> moviedirecter) {

		for (MovieDTO movie : moviedirecter) {
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
	 */
	public static void successReviewList(List<ReviewDTO> list){
		int cnt = 1;
		for(ReviewDTO review : list) {
			System.out.println("● 리뷰 " + cnt++ + " : " + review);
		}
	}

	public static void printPassword(UsersDTO user) {
		System.out.println("회원의 비밀번호 => "+user.getPassword());
		
	}
	
	public static void printReview(ReviewDTO review) {
		System.out.println("수정된 리뷰 "+review );
	}
	
	/**
	 * 리뷰 목록 조회
	 * @param reviewList
	 */
	public static void printReviewByUser(Map<Integer, ReviewDTO> reviewList) {
		System.out.println("===== 회원님의 리뷰 목록 =======");
		Set<Map.Entry<Integer, ReviewDTO>> entrySet = reviewList.entrySet();
		
		int i =0;
		for (Entry<Integer, ReviewDTO> element : entrySet) {
			int key = element.getKey();
			ReviewDTO dd = element.getValue();
			
			System.out.println(key +" "+ dd);
		}
	}
	
	/**
	 * 찜 목록 조회
	 * @param list
	 */
	public static void dipsList(Map<Integer, DipsDTO> list) {
		System.out.println("===== 회원님의 찜목록 =======");
		Set<Map.Entry<Integer, DipsDTO>> entrySet = list.entrySet();
		
		int i =0;
		for (Entry<Integer, DipsDTO> element : entrySet) {
			int key = element.getKey();
			DipsDTO dd = element.getValue();
			
			System.out.println(key +" "+ dd);
		}
		
	}

	

}
	