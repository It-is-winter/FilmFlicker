package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.StringTokenizer;

import controller.MyPageController;
import management.DTO.UsersDTO;

public class MyPageView {

	private static BufferedReader bf = null;
//	private static StringTokenizer st = null;
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
				System.out.println();
				break;
			case 2 :
				MyPageView.printDips(user); //찜목록 내역 확인 화면 나오기
				System.out.println();
				MyPageView.printMyDipsMenu(user); //찜목록 메뉴 화면 나오기
				System.out.println();
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
		MyPageController.selectDips(user);
	}
	
	
	/**
	 * 찜목록 메뉴 화면
	 * @param user
	 */
	private static void printMyDipsMenu(UsersDTO user) {
		System.out.println("=================================================");
		System.out.println("1. 찜목록 삭제 | 2. 뒤로가기");
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			menu = Integer.parseInt(bf.readLine());
			
			switch(menu) {
			case 1 :
				MyPageView.printDeleteDips(user); //찜목록 삭제 화면 나오기
				System.out.println();
				break;
			case 3:
				return;
			}
			
		} catch (IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
	}
	
	/**
	 * 찜 목록 삭제 화면
	 * @param user
	 */
	private static void printDeleteDips(UsersDTO user) {
		System.out.print("삭제할 찜 목록 번호 => ");
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			int delete = Integer.parseInt(bf.readLine());
			MyPageController.deleteMyDips(user, delete);
		} catch (IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
	}
	
	
	/**
	 * 리뷰 확인 화면
	 * @param user
	 */
	private static void printMyReviewMenu(UsersDTO user) {
		MyPageController.selectReviewByUser(user);
		System.out.println("=================================================");
		System.out.println("1. 리뷰 수정 | 2. 리뷰 삭제 | 3. 뒤로가기");
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			menu = Integer.parseInt(bf.readLine());
			
			switch(menu) {
			case 1 :
				MyPageView.printUpdateReview(user);
				System.out.println();
				break;
			case 2 :
				MyPageView.printDeleteReview(user);
				System.out.println();
				break;
			case 3:
				return;
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
			
			MyPageController.updateMyReview(user, update,reviewComent,reviewScore);
			
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
			MyPageController.deleteMyDips(user, delete);
		} catch (IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
	}

}// 클래스 끝
