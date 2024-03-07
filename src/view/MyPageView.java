package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import controller.DipsController;
import controller.ReviewController;
import controller.UserController;
import management.DTO.UsersDTO;

public class MyPageView {

	private static BufferedReader bf = null;
	private static StringTokenizer st = null;
	private static int menu;
	
	/*
	 * 회원의 마이페이지 뷰를 따로 뺌.
	 */
	
	public static void printMyPage(UsersDTO user) {
		
		while(true) {
			
			
			System.out.println("=== 실마리 영화 커뮤니티 ===");
			System.out.println("1. 등록한 리뷰 보기 | 2. 찜목록 확인하기 "
					+ "| 3. 뒤로가기 | 9. 종료하기");
			
			try{
				bf = new BufferedReader(new InputStreamReader(System.in));
				menu = Integer.parseInt(bf.readLine());
				
			} catch (IOException e) {
				FailView.errorMessage("잘못된 값을 입력하였습니다.");
			}
			
			switch(menu) {
			case 1 :
				MyPageView.printMyReviewMenu(user); // 등록된 리뷰 화면 나오기
				break;
			case 2 :
				MyPageView.printDips(user);
				break;
			case 3 :
				return; // 뒤로가기
			case 9 : 
				System.exit(0);
			default :
				FailView.errorMessage("Consol 이외의 값을 입력하였습니다.");
			}
		}// while 끝
		
		
	}// printMyPage 메소드 끝

	/**
	 * 찜목록 내역 확인하기 화면
	 * @param user
	 */
	private static void printDips(UsersDTO user) {
		
		DipsController.selectDips(user);
		
	}
	
	private static void printMyReviewMenu(UsersDTO user) {
		ReviewController.selectReviewByUser(user);
		System.out.println("=================================================");
		System.out.println("1. 리뷰 수정 | 2. 리뷰 삭제");
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			menu = Integer.parseInt(bf.readLine());
			
			switch(menu) {
			case 1 :
				MyPageView.printUpdateReview(user);
				break;
			case 2 :
				MyPageView.printDeleteReview(user);
				break;
			}
			
		} catch (IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
	}
	
	/**
	 * 등록된 리뷰 수정 화면
	 */
	private static void printUpdateReview(UsersDTO user) {
		int update = 0;
		String reviewComent = null;
		int reviewScore = 0;
		
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("수정할 리뷰 번호 => ");
			update = Integer.parseInt(bf.readLine());
			System.out.print("수정할 리뷰 내용 => ");
			reviewComent = bf.readLine();
			System.out.print("수정할 리뷰 점수 => ");
			reviewScore = Integer.parseInt(bf.readLine());
			
			ReviewController.updateMyReview(user, update,reviewComent,reviewScore);
			
		} catch (IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
	}
	
	/**
	 * 등록된 리뷰 삭제 화면
	 */
	private static void printDeleteReview(UsersDTO user) {
		System.out.print("삭제할 리뷰 번호 => ");
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			int delete = Integer.parseInt(bf.readLine());
			ReviewController.deletMyReview(user, delete);
		} catch (IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
	}

}// 클래스 끝
