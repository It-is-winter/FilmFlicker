package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
			System.out.println("1. 비밀번호 수정		|	2. 등록 리뷰 삭제	"
					+ "|	3. 찜목록 확인하기	"
					+ "|	4. 뒤로가기	"+ "|	9. 종료하기	");
			
			try{
				bf = new BufferedReader(new InputStreamReader(System.in));
				menu = Integer.parseInt(bf.readLine());
				
			} catch (IOException e) {
				e.printStackTrace();
				FailView.errorMessage("잘못된 값을 입력하였습니다.!");
			}
			
			switch(menu) {
			case 1 :
				MyPageView.printUpdatedPassword(user);// 비밀번호 수정 화면 나오기
				break;
			case 2 :
				 // 등록된 리뷰 삭제 화면 나오기
				break;
			case 3 :
				 // 찜목록 확인하기
				break;
			case 4 :	
				return; 	// 뒤로가기
			case 9 : 
				System.exit(0);
			default :
				FailView.errorMessage("Consol 이외의 값을 입력하였습니다.!");
			}
		}// while 끝
		
		
	}// printMyPage 메소드 끝

	private static void printUpdatedPassword(UsersDTO user) {
		String password = null;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 실마리 회원 정보 수정 ===");
			System.out.print("새 비밀번호 입력	=>	");
			password = bf.readLine();
		}catch (IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.!");
		}
		
		UserController.userUpdate(user.getIdEmail(), password);
		
	}
}// 클래스 끝
