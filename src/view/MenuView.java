package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import controller.MovieController;
import controller.ReviewController;
import controller.UserController;
import management.DTO.UsersDTO;

public class MenuView {
	
	
	/*
	 * 회원, 비회원, 회원가입, 종료
	 * 회원 선택 - 영화등록, 리뷰등록(+평점), 영화겁색(+리뷰, 뒤로가기), 마이페이지(+찜목록, 리뷰삭제, 뒤로가기), 종료
	 * 비회원 - 영화검색(+리뷰)
	 * 회원가입 - ID, Password, 생년월일, 이름,(+sysdate)
	 */
	
	private static BufferedReader bf = null;
	private static StringTokenizer st = null;
	private static int menu;
	
	public static void menu() {
		MenuView.printMenu();  // 첫 화면 나오기 1)회원 2)비회원 3)회원가입 9)종료
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			menu = Integer.parseInt(bf.readLine());
			
		} catch (IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.!");
		}
		

		switch(menu) {
		case 1 :
			MenuView.printMember(); // 회원 화면 나오기
			break;
		case 2 :
			MenuView.printNotMember(); // 비회원 화면 나오기
			break;
		case 3 :
			MenuView.printRegister(); // 회원 가입 화면 나오기
			break;
		case 4 :
			MenuView.printSelectMovie();
			break;
		case 5 :	
			MenuView.printInsertMovie();
			break;
		case 9 : 
			System.exit(0);
			break;
		default :
			FailView.errorMessage("Consol 이외의 값을 입력하였습니다.!");
		}
	
	}
	
	
	private static void printRegister() {
		
		
	}


	private static void printNotMember() {
		
		
	}


	private static void printMember() {
		
		String idEmail = null;
		String password = null;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 실마리 영화 커뮤니티 로그인 ===");
			System.out.print("ID	=>	");
			idEmail = bf.readLine();
			System.out.print("pwd	=>	");
			password = bf.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.!");
		}
		
		
		UsersDTO user = UserController.login(idEmail, password);
		
		MenuView.printUserMenu(null);
		
	}


	public static void printMenu() {
		System.out.println("=== 실마리 영화 커뮤니티 ===");
		System.out.println("1. 회원		|	2. 비회원	|	3. 회원가입	|	9. 종료");
	}




	public static void printUserMenu(UsersDTO user) {
		
		while(true) {
			
			System.out.println("=== 실마리 " + user.getName() + "회원님 " +" ===");
			System.out.println("1. 영화등록		|	2. 리뷰등록	|	3. 영화검색	|	4. 마이페이지	|	9. 종료");
			

			try{
				bf = new BufferedReader(new InputStreamReader(System.in));
				menu = Integer.parseInt(bf.readLine());
				
			} catch (IOException e) {
				e.printStackTrace();
				FailView.errorMessage("잘못된 값을 입력하였습니다.!");
			}
			
			switch(menu) {
			case 1 :
				MenuView.printInsertMovie(); // 영화 등록 화면 나오기
				break;
			case 2 :
				MenuView.printInsertReview(user); // 리뷰 등록 화면 나오기
				break;
			case 3 :
				MenuView.printSelectMovie(); // 영화 검색 화면 나오기
				break;
			case 4 :
				MenuView.printMyPage(); // 마이페이지 화면 나오기
				break;
			case 9 : 
				System.exit(0);
			default :
				FailView.errorMessage("Consol 이외의 값을 입력하였습니다.!");
			}
			
		}
		
		
	}


	private static void printInsertReview(UsersDTO user) {
		String movieName = null;
		String review = null;
		int movieScore = 0;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 실마리 리뷰 등록 ===");
			System.out.print("영화 이름	=>	");
			movieName = bf.readLine();
			System.out.print("리뷰 내용	=>	");
			review = bf.readLine();
			System.out.print("영화 평점	=>	");
			movieScore = Integer.parseInt(bf.readLine());
		}catch (IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.!");
		}
		
		ReviewController.insertReview(movieName, review, movieScore,user);
	}


	private static void printMyPage() {
		// TODO Auto-generated method stub
		
	}


	private static void printSelectMovie() {
		
		String movieName = null;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 실마리 리뷰 등록 ===");
			System.out.print("검색할 영화 이름	=>	");
			movieName = bf.readLine();
		}catch (IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.!");
		}
		
		MovieController.selectMovieByName(movieName);
		
	}



	private static void printInsertMovie() {
		
		String movieName = null;
		String movieDirecter = null;
		String releaseDate = null;
		List<String> leadActor = new ArrayList<String>();
		List<String> supportActor = new ArrayList<String>();
	
		try{
			
			 bf = new BufferedReader(new InputStreamReader(System.in));
		        System.out.println("=== 실마리 영화 등록 ===");
		        System.out.print("영화 이름    =>  ");
		        movieName = bf.readLine();
		        System.out.println(movieName);
		        System.out.print("영화 감독    =>  ");
		        movieDirecter = bf.readLine();
		        System.out.println(movieDirecter);
		        System.out.print("개봉 날짜    =>  ");
		        releaseDate = bf.readLine();
		     
		        System.out.print("주연 배우(공백 구분)   =>  ");
		        String leadActorsInput = bf.readLine();
		        leadActor.addAll(Arrays.asList(leadActorsInput.split(" ")));
		        System.out.println(leadActor);
		        System.out.print("조연 배우(공백 구분)   =>  ");
		        String supportActorsInput = bf.readLine();
		        supportActor.addAll(Arrays.asList(supportActorsInput.split(" ")));
			
		        
		        
		/*        
			bf = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer(bf.readLine());
			System.out.println("=== 실마리 영화 등록 ===");
			System.out.print("영화 이름	=>	");
			movieName = st.nextToken();
			System.out.print("영화 감독	=>	");
			movieDirecter = st.nextToken();
			System.out.print("개봉 날짜	=>	");
			releaseDate = st.nextToken();
			System.out.print("주연 배우(공백 구분)	=>	");
			while(st.nextToken() != null) {
				leadActor.add(st.nextToken());
			}
			System.out.print("조연 배우(공백 구분)	=>	");
			while(st.nextToken() != null) {
				supportActor.add(st.nextToken());
			}
		   */     
		        
		} catch (IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.!");
		}
	
			
		MovieController.insertMovie(movieName,movieDirecter,releaseDate,leadActor,supportActor);
		
		
	}


}
