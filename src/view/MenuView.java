package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.StringTokenizer;

import controller.DipsController;
import controller.MovieController;
import controller.ReviewController;
import controller.UserController;
import exception.SearchException;
import management.DTO.MovieDTO;
import management.DTO.ReviewDTO;
import management.DTO.ReviewEtcDTO;
import management.DTO.UsersDTO;
import session.UsersSession;
import session.UsersSessionSet;



public class MenuView {
	
	
	/*
	 * 회원, 비회원, 회원가입, 종료
	 * 회원 선택 - 영화등록, 리뷰등록(+평점), 영화겁색(+리뷰, 뒤로가기), 마이페이지(+찜목록, 리뷰삭제, 뒤로가기), 종료
	 * 비회원 - 영화검색(+리뷰)
	 * 마이페이지는 뷰를 따로 뺌.
	 * 회원가입 - ID, Password, 생년월일, 이름,(+sysdate)
	 */
	
	private static BufferedReader bf = null;
//	private static StringTokenizer st = null;
	private static int menu;
	private static boolean isLogOut = false;
	
	/**
	 * 첫 화면 띄우기
	 * 1)회원 2)비회원 3)회원가입 4)비밀번호 찾기 9)종료
	 */

	public static void menu() {
		
		while(true) {
			UsersSessionSet.getInstance();
//			System.out.println("현재 접속중인 회원 " +userSessionSet.getSet());
			
			MenuView.printMenu();
			try{
				bf = new BufferedReader(new InputStreamReader(System.in));
				menu = Integer.parseInt(bf.readLine());
				
			} catch (IOException e) {
				FailView.errorMessage("잘못된 값을 입력하였습니다.");
			}
			System.out.println();

			switch(menu) {
			case 1 :
				MenuView.printMember(); // 회원으로 접속 화면 나오기
				System.out.println();
				break;
			case 2 :
				MenuView.printNotMember(); // 비회원 접속 화면 나오기
				System.out.println();
				break;
			case 3 :
				MenuView.printRegister(); // 회원 가입 화면 나오기
				System.out.println();
				break;
			case 4 :
				MenuView.printFindPassword(); // 비밀번호 찾기 화면 나오기
				System.out.println();
				break;
			case 9 :
				System.out.println("다음에 또 이용해주세요. 실마리 찾기 종료");
				System.exit(0);
				break;
			default :
				FailView.errorMessage("Consol 이외의 값을 입력하였습니다.");
			}
		}

	}
	
	/**
	 * 비밀번호 찾기 화면
	 */
	private static void printFindPassword() {
		String id = null;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 실마리 영화 커뮤니티 비밀번호 찾기 ===");
			System.out.print("ID => ");
			id = bf.readLine();
			
		} catch (IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
		
		UserController.searchByUserID(id);
		System.out.println();
		MenuView.printPasswordMenu(id);
	}
	
	/**
	 * 비밀번호 변경 메뉴
	 */
	private static void printPasswordMenu(String userId) {
		System.out.println("============================");
		System.out.println("1. 비밀번호 변경 | 2. 뒤로가기");
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			menu = Integer.parseInt(bf.readLine());
			
			switch(menu) {
			case 1 :
				MenuView.printUpdatedPassword(userId); //비밀번호 변경 화면 나오기
				System.out.println();
				break;
			case 2 :
				return;
			}
			
		} catch (IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
	}
	
	/**
	 * 비밀번호 변경 화면
	 * @param user
	 */
	private static void printUpdatedPassword(String userId) {
		String password = null;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 실마리 회원 정보 수정 ===");
			System.out.print("새 비밀번호 입력=> ");
			password = bf.readLine();
		} catch (IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
		
		UserController.userUpdate(userId, password);
	}
	
	/**
	 * 회원가입 화면
	 */
	private static void printRegister() {
		String id = null;
		String password = null;
		String userName = null;
		String birth = null;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 실마리 영화 커뮤니티 회원 가입 ===");
			System.out.print("ID => ");
			id = bf.readLine();
			System.out.print("pwd => ");
			password = bf.readLine();
			System.out.print("이름 => ");
			userName = bf.readLine();
			System.out.print("생년월일(6자리) => ");
			birth = bf.readLine();
			System.out.println("");
			
			UserController.register(id, password, userName, birth);
			
		} catch (IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}


	}
	
	/**
	 * 비회원 접속 화면
	 */
	private static void printNotMember() {
		MenuView.printSelectMovie();
	}
	
	/**
	 * 로그인 화면
	 */
	private static void printMember() {
		
		String id = null;
		String password = null;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 실마리 영화 커뮤니티 로그인 ===");
			System.out.print("ID => ");
			id = bf.readLine();
			System.out.print("pwd => ");
			password = bf.readLine();
			System.out.println("");
		} catch (IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}

		/*UsersDTO user = */UserController.login(id, password);
		System.out.println();
	}
	
	/**
	 * 시작 화면
	 */
	public static void printMenu() {
		System.out.println("=== 실마리 영화 커뮤니티 ===");
		System.out.println("1. 회원으로 접속 | 2. 비회원으로 접속 "
				+ "| 3. 회원가입 하기 "
				+ "| 4. 비밀번호 찾기 | 9. 종료");
	}

	
	/**
	 * 회원 접속 화면
	 * @param user
	 */
	public static void printUserMenu(UsersDTO user){
		
		while(true) {
			
			if(MenuView.isLogOut) {
				return;
			}
			
			System.out.println("=== 실마리 " + user.getName() + " 회원님 " +" ===");
			System.out.println("1. 영화등록 | 2. 리뷰등록 | 3. 영화검색 | 4. 마이페이지 "
					+ "| 5. 뒤로가기 | 6. 로그아웃 | 9. 종료");
			
			try{
				bf = new BufferedReader(new InputStreamReader(System.in));
				menu = Integer.parseInt(bf.readLine());
				
			} catch (IOException e) {
				e.printStackTrace();
				FailView.errorMessage("잘못된 값을 입력하였습니다.");
			}
			
			switch(menu) {
			case 1 :
				System.out.println();
				MenuView.printInsertMovie(); // 영화 등록 화면 나오기
				System.out.println();
				break;
			case 2 :
				System.out.println();
				MenuView.printInsertReview(user); // 리뷰 등록 화면 나오기
				System.out.println();
				break;
			case 3 :
				System.out.println();
				MenuView.printSelectMovie(user); // 영화 검색 화면 나오기
				System.out.println();
				break;
			case 4 :
				System.out.println();
				MyPageView.printMyPage(user); // 마이페이지 화면 나오기
				System.out.println();
				break;
			case 5 :
				 return; // 뒤로가기
			case 6 :
				MenuView.printLogOut(user);
				System.out.println("로그아웃 되었습니다.");
				return; // 로그아웃하기
			case 9 :
				System.out.println("다음에 또 이용해주세요. 실마리 찾기 종료");
				System.exit(0);
			default :
				FailView.errorMessage("Consol 이외의 값을 입력하였습니다.");
			}
			
		}
		
		
	}


	/**
	 * 로그 아웃
	 * @param user
	 */
	private static void printLogOut(UsersDTO user) {
		UsersSession userSession  = new UsersSession(user.getIdEmail());
		
		UsersSessionSet userSessionSet = UsersSessionSet.getInstance();
		userSessionSet.remove(userSession);
		
	}
	
	
	/**
	 * 영화 검색 화면(비회원 전용)
	 */
	private static void printSelectMovie() {
		
		while(true) {
			System.out.println("==== 영화 검색 방법 ====");
			System.out.println("1. 영화 이름 | 2. 감독 이름 | 3. 영화 장르 | 4. 개봉 연도 | "
					+ "5. 뒤로가기 | 9. 종료");
			
			try {
				bf = new BufferedReader(new InputStreamReader(System.in));
				menu = Integer.parseInt(bf.readLine());
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
				FailView.errorMessage("잘못된 값을 입력하였습니다.");
			}
			
			switch (menu) {
			
			case 1 :
				System.out.println();
				MenuView.selectMovieName(); //영화 이름으로 영화 검색하는 화면 나오기
				System.out.println();
				break;
			case 2 :
				System.out.println();
				MenuView.selectMovieDirector(); //감독 이름으로 영화 검색하는 화면 나오기
				System.out.println();
				break;
				
			case 3 :
				System.out.println();
				MenuView.selectMovieGenre(); //장르로 영화 검색하는 화면 나오기
				System.out.println();
				break;
			case 4 :
				System.out.println();
				MenuView.selectMovieReleaseDate(); //개봉연도로 영화 검색하는 화면 나오기
				System.out.println();
				break;
			case 5 :
				return; //뒤로 가기
			case 9 :
				System.out.println("다음에 또 이용해주세요. 실마리 찾기 종료");
				System.exit(0);
			
			default :
				FailView.errorMessage("Consol 이외의 값을 입력하였습니다.");
			}
			
		}
		
	}
	
	
	/**
	 * 영화 검색 화면(회원 전용)
	 */
	private static void printSelectMovie(UsersDTO user) {
		
		while(true) {
			System.out.println("==== 영화 검색 방법 ====");
			System.out.println("1. 영화 이름 | 2. 감독 이름 | 3. 영화 장르 | 4. 개봉 연도 | "
					+ "5. 뒤로가기 | 6. 로그아웃 | 9. 종료");
			
			try {
				bf = new BufferedReader(new InputStreamReader(System.in));
				menu = Integer.parseInt(bf.readLine());
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
				FailView.errorMessage("잘못된 값을 입력하였습니다.");
			}
			
			switch (menu) {
			
			case 1 :
				System.out.println();
				MenuView.selectMovieName(user); //영화 이름으로 영화 검색하는 화면 나오기
				System.out.println();
				break;
			case 2 :
				System.out.println();
				MenuView.selectMovieDirector(); //감독 이름으로 영화 검색하는 화면 나오기
				System.out.println();
				break;
				
			case 3 :
				System.out.println();
				MenuView.selectMovieGenre(); //장르로 영화 검색하는 화면 나오기
				System.out.println();
				break;
			case 4 :
				System.out.println();
				MenuView.selectMovieReleaseDate(); //개봉연도로 영화 검색하는 화면 나오기
				System.out.println();
				break;
			case 5 :
				return; //뒤로 가기
			case 6 :
				MenuView.printLogOut(user);
				System.out.println("로그아웃 되었습니다.");
				MenuView.isLogOut= true;
				return;
			case 9 :
				System.out.println("다음에 또 이용해주세요. 실마리 찾기 종료");
				System.exit(0);
			
			default :
				FailView.errorMessage("Consol 이외의 값을 입력하였습니다.");
			}
			
		}
		
	}

//-------------------------영화 이름 검색시----------------------------------------
	
	/**
	 * 영화 이름으로 검색 화면 (비회원용)
	 */
	private static void selectMovieName() {
		String movieName = null;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 영화 검색 ===");
			System.out.print("검색할 영화 이름 => ");
			movieName = bf.readLine();
			MovieDTO movie = MovieController.selectMovieByName(movieName);
			List<ReviewDTO> list = ReviewController.selectReviewByMovie(movie);
			if(list==null) MenuView.printSelectMovie();
			else ReviewController.printReviewList(list);
		} catch (IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
	}
	
	
	/**
	 * 영화 이름으로 검색 화면(회원용) -> 검색 성공시 찜목록에 저장하는 메소드 호출
	 */
	private static void selectMovieName(UsersDTO user) {
		String movieName = null;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 영화 검색 ===");
			System.out.print("검색할 영화 이름 => ");
			movieName = bf.readLine();
			MovieDTO movie = MovieController.selectMovieByName(movieName);
			List<ReviewDTO> list = ReviewController.selectReviewByMovie(movie);
			if(list==null) { 
				//MenuView.printSelectMovie(user);
				printDipsInsert(user,movie);
				return;
			}
			else {
				ReviewController.printReviewList(list);
				printDipsInsert(user,movie);
			}
		} catch (IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
	}
	
	/**
	 * 리뷰에 출력 이후 영화를 찜목록에 추가하기
	 */
	private static void printDipsInsert(UsersDTO user,MovieDTO movie) {
		String insertdips = null;
		List<ReviewDTO> list = ReviewController.selectReviewByMovie(movie);

		while(true) {
			System.out.println("영화를 찜목록에 추가하시겠습니까? 추가 1 | 아니오 2");
			bf = new BufferedReader(new InputStreamReader(System.in));
			try {
				insertdips = bf.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int movieSeq =movie.getMovieSeq();
			switch(Integer.parseInt(insertdips)) {
			case 1 :
				DipsController.insertDips(user,movieSeq); // 찜목록에 등록
				if(list!=null) MenuView.printReviewValue(user,movie); // 리뷰 좋아요 싫어요 입력뷰로 이동
				break;
			case 2 :
				if(list!=null) MenuView.printReviewValue(user,movie);
				return;
			default :
				System.out.println("잘못입력하였습니다!");
				break;
			}
			break;
		}
	}
	
	
	/**
	 * 좋아요 싫어요 누르는 뷰로 넘어가기 (회원 전용)
	 * */
	private static void printReviewValue(UsersDTO user,MovieDTO movie) {
		System.out.println("\n1. 리뷰에 좋아요/싫어요 누르기   |   2. 나가기");
		
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			menu = Integer.parseInt(bf.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.!");
		}
		
		switch (menu) {
		case 1 :
			MenuView.selectLikeOrHate(user, movie);
			break;
		case 2 :
			MenuView.printSelectMovie(user);
			break;
		default :
			FailView.errorMessage("Consol 이외의 값을 입력하였습니다.!");
		}
	}
	
	
	/**
	 * 좋아요/싫어요 입력하는 화면 (회원 전용)
	 * @param user
	 * @param movie
	 * @throws SearchException
	 * @throws SQLException
	 */
	private static void selectLikeOrHate(UsersDTO user, MovieDTO movie) {
		//----좋아요/싫어요를 누를 리뷰를 선택----
		System.out.print("\n좋아요 또는 싫어요를 누를 리뷰의 번호를 골라주세요 > ");
		
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			menu = Integer.parseInt(bf.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.!");
		}
		//System.out.println(menu);
		List<ReviewDTO> list = ReviewController.selectReviewByMovie(movie);
		ReviewDTO review = list.get(menu-1);
		
		//----선택한 리뷰에 좋아요 또는 싫어요 누르기----
		ReviewEtcDTO reviewEtcDTO = null;
		
		System.out.println("1. 좋아요   |   2. 싫어요   |   3. 나가기 ");
		
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			menu = Integer.parseInt(bf.readLine());
		} catch (NumberFormatException | IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.!");
		}
		
		switch (menu) {
		
		case 1 : // 좋아요
			reviewEtcDTO = new ReviewEtcDTO(user.getUserSeq(), review.getReviewSeq(),1);
			ReviewController.insertLike(user,reviewEtcDTO);
			break;
		case 2 : // 싫어요
			reviewEtcDTO = new ReviewEtcDTO(user.getUserSeq(), review.getReviewSeq(),-1);
			ReviewController.insertHate(user,reviewEtcDTO);
			break;
		case 3 :
			MenuView.printSelectMovie(user);
		default :
			FailView.errorMessage("Consol 이외의 값을 입력하였습니다.!");
		}
	}
	
//--------------------------------------------------------------------	
	/**
	 * 감독 이름으로 영화 검색 화면
	 * */
	private static void selectMovieDirector() {
		String movieDirector = null;
		
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 영화 검색 ===");
			System.out.print("검색할 감독 이름 => ");
			movieDirector = bf.readLine();
			MovieController.selectMovieByDirector(movieDirector);
		} catch(IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
	}
	
	
	/**
	 * 장르로 영화 검색 화면
	 * */
	private static void selectMovieGenre() {
		String movieGenre = null;
		
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 영화 검색 ===");
			System.out.println("검색할 장르 숫자로 입력");
			System.out.println("1: 액션, 2: 드라마, 3: 코미디, 4: 호러");
			movieGenre = bf.readLine();
			MovieController.selectMovieByGenre(movieGenre);
		} catch(IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
	}
	
	
	/**
	 * 개봉 연도로 영화 검색 화면
	 * */
	private static void selectMovieReleaseDate(){
		 String movieReleaseDate = null;
		 
		 try {
			 bf = new BufferedReader(new InputStreamReader(System.in));
			 System.out.println("=== 영화 검색 ===");
			 System.out.println("개봉 연도 입력(EX.0000)");
			 
			 movieReleaseDate = bf.readLine();
			 MovieController.selectMovieByReleaseDate(movieReleaseDate);
		 } catch(IOException e) {
			 FailView.errorMessage("잘못된 값을 입력하였습니다.");
		 }
	}
	
	
	/**
	 * 영화 등록 화면
	 */
	private static void printInsertMovie() {
		
		String movieName = null;
		int movieGenre = 0;
		String movieDirecter = null;
		String releaseDate = null;
		List<String> leadActor = new ArrayList<String>();
		List<String> supportActor = new ArrayList<String>();
	
		try{
			
			 bf = new BufferedReader(new InputStreamReader(System.in));
		        System.out.println("=== 실마리 영화 등록 ===");
		        System.out.print("영화 이름 => ");
		        movieName = bf.readLine();
//		        System.out.println(movieName);
				/*
				 * System.out.println("영화 장르"); movieGenre = bf.readLine();
				 */
		        System.out.print("장르를 숫자로 입력하세요 (1: 액션, 2: 드라마, 3: 코미디, 4: 호러)");
	            System.out.println();
		        String movieGenreStr = bf.readLine();
	            movieGenre = Integer.parseInt(movieGenreStr);
//	            System.out.println(movieGenre);
		        System.out.print("영화 감독 => ");
		        movieDirecter = bf.readLine();
		        
		        System.out.print("개봉 날짜(EX.0000-00-00) => ");
		        releaseDate = bf.readLine();
		     
		        System.out.print("주연 배우(\",\" 로 구분) => ");
		        String leadActorsInput = bf.readLine();
		        leadActor.addAll(Arrays.asList(leadActorsInput.split(",")));
//		        System.out.println(leadActor);
		        
		        System.out.print("조연 배우(\",\" 로 구분) => ");
		        String supportActorsInput = bf.readLine();
		        supportActor.addAll(Arrays.asList(supportActorsInput.split(",")));
//		        System.out.println(supportActor);
		        
//		        for (String actor : leadActor) {
//		        	System.out.println("주연배우 = "+ actor);
//		        }
		        
			        
		} catch (IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
	

		MovieController.insertMovie(movieName,movieGenre,movieDirecter,releaseDate,leadActor,supportActor);

	}
	
	
	/**
	 * 리뷰 등록 화면
	 * @param user
	 */
	private static void printInsertReview(UsersDTO user) {
		String movieName = null;
		String review = null;
		int movieScore = 0;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 실마리 리뷰 등록 ===");
			
			System.out.print("영화 이름 => ");
			movieName = bf.readLine();
			MovieDTO movie = MovieController.selectMovieByName(movieName);
			System.out.print("리뷰 내용 => ");
			review = bf.readLine();
			
			while(true) {
				System.out.print("영화 평점(10점 만점)	=>	");
				movieScore = Integer.parseInt(bf.readLine());
				if(movieScore<1 || movieScore>10) {
					System.out.println("평점은 1~10 사이의 정수로 입력해주세요");
				} else break;
			}
			
			ReviewDTO reviewDTO = new ReviewDTO(user.getUserSeq(), movie.getMovieSeq(), review, movieScore);
			ReviewController.insertReview(reviewDTO);
		}catch (IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.");
		}
		
	}
	
	
	
	
	
	


}
