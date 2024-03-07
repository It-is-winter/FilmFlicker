package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import controller.MovieController;
import controller.ReviewController;
import controller.UserController;
import exception.SearchException;
import management.DTO.MovieDTO;
import management.DTO.ReviewDTO;
import management.DTO.UsersDTO;
import service.MovieService;
import service.Impl.MovieServiceImpl;
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
	private static StringTokenizer st = null;
	private static int menu;
	
	
	
	
	public static void menu() {
		
		while(true) {
			UsersSessionSet userSessionSet = UsersSessionSet.getInstance();
			System.out.println("현재 접속중인 회원 " +userSessionSet.getSet());
			
			MenuView.printMenu();  // 첫 화면 나오기 1)회원 2)비회원 3)회원가입 9)종료
			
			try{
				bf = new BufferedReader(new InputStreamReader(System.in));
				menu = Integer.parseInt(bf.readLine());
				
			} catch (IOException e) {
				FailView.errorMessage("잘못된 값을 입력하였습니다.!");
			}
			

			switch(menu) {
			case 1 :
				MenuView.printMember(); // 회원으로 접속 화면 나오기
				break;
			case 2 :
				MenuView.printNotMember(); // 비회원 접속 화면 나오기
				break;
			case 3 :
				MenuView.printRegister(); // 회원 가입 화면 나오기
				break;
			case 4 :
				MenuView.printFindPassword(); // 비밀번호 찾기 화면 나오기
				break;
			case 9 : 
				System.exit(0);
				break;
			default :
				FailView.errorMessage("Consol 이외의 값을 입력하였습니다.!");
			}
		}

	}
	
	
	private static void printFindPassword() {
		String id = null;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 실마리 영화 커뮤니티 비밀번호 찾기 ===");
			System.out.print("ID	=>	");
			id = bf.readLine();
			
		} catch (IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다.!");
		}
		
		
			UserController.searchByUserID(id);
		
	}


	private static void printRegister() {
		String id = null;
		String password = null;
		String userName = null;
		String birth = null;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 실마리 영화 커뮤니티 회원 가입 ===");
			System.out.print("ID	=>	");
			id = bf.readLine();
			System.out.print("pwd	=>	");
			password = bf.readLine();
			System.out.print("이름	=>	");
			userName = bf.readLine();
			System.out.print("생년월일(6자리)	=>	");
			birth = bf.readLine();
			System.out.println("");
			
		} catch (IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.!");
		}
		


	}
	
	 


	private static void printNotMember() {
		
		
	}


	private static void printMember() {
		
		String id = null;
		String password = null;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 실마리 영화 커뮤니티 로그인 ===");
			System.out.print("ID	=>	");
			id = bf.readLine();
			System.out.print("pwd	=>	");
			password = bf.readLine();
			System.out.println("");
		} catch (IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.!");
		}

		UsersDTO user = UserController.login(id, password);
		
		
	}


	public static void printMenu() {
		System.out.println("=== 실마리 영화 커뮤니티 ===");
		System.out.println("1. 회원으로 접속...		|	2. 비회원으로 접속...	"
				+ "|	3. 회원가입 하기	"
				+ "|	4. 비밀번호 찾기	9. 종료");
	}




	public static void printUserMenu(UsersDTO user) throws SearchException, SQLException {
		
		while(true) {
			
			System.out.println("=== 실마리 " + user.getName() + " 회원님 " +" ===");
			System.out.println("1. 영화등록		|	2. 리뷰등록	|	3. 영화검색	|	4. 마이페이지	|	"
					+ "5. 뒤로가기	|	6. 로그아웃	|9. 종료");
			

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
				MenuView.printSelectMovie(user); // 영화 검색 화면 나오기
				break;
			case 4 :
				MyPageView.printMyPage(user); // 마이페이지 화면 나오기
				break;
			case 5 :
				 return;// 뒤로가기
			case 6 :
				 MenuView.printLogOut(user);
				 break;// 로그아웃하기
			case 9 : 
				System.exit(0);
			default :
				FailView.errorMessage("Consol 이외의 값을 입력하였습니다.!");
			}
			
		}
		
		
	}


	// UserSessionSet 에서 userSession를 제거하여 로그아웃 진행
	private static void printLogOut(UsersDTO user) {
		UsersSession userSession  = new UsersSession(user.getIdEmail());
		
		UsersSessionSet userSessionSet = UsersSessionSet.getInstance();
		userSessionSet.remove(userSession);
		
	}


	private static void printInsertReview(UsersDTO user) throws SearchException, SQLException {
		MovieService movieService = new MovieServiceImpl();
		
		String movieName = null;
		String review = null;
		int movieScore = 0;
		
		try{
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 실마리 리뷰 등록 ===");
			
			System.out.print("영화 이름	=>	");
			movieName = bf.readLine();
			MovieDTO movie = movieService.selectMovieByName(movieName);
			System.out.print("리뷰 내용	=>	");
			review = bf.readLine();
			System.out.print("영화 평점(10점 만점)	=>	");
			movieScore = Integer.parseInt(bf.readLine());
			
			ReviewDTO reviewDTO = new ReviewDTO(user.getUserSeq(), movie.getMovieSeq(), review, movieScore);
			ReviewController.insertReview(reviewDTO);
			
		}catch (IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.!");
		}
		
		
	}


	
//--
	
	
	
//--
	private static void printSelectMovie(UsersDTO user) throws SQLException, SearchException{
		
		while(true) {
			System.out.println("==== 영화 검색 방법 ====");
			System.out.println("1. 영화 이름	|	2. 감독 이름	|	3. 영화 장르	|	4. 개봉 연도	|	"
					+ "5. 뒤로가기	|	6. 로그아웃	|9. 종료");
			
			try {
				bf = new BufferedReader(new InputStreamReader(System.in));
				menu = Integer.parseInt(bf.readLine());
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
				FailView.errorMessage("잘못된 값을 입력하였습니다.!");
			}
			
			switch (menu) {
			
			case 1 :
				MenuView.selectMovieName();
				break;
			case 2 :
				MenuView.selectMovieDirecter();
				break;
				
			case 3 :
				MenuView.selectMovieGenre();
				break;
			case 4 :
				MenuView.selectMovieReleaseDate();
				break;
			case 5 :
				MenuView.printUserMenu(user);
			case 6 :
				 MenuView.printLogOut(user);
				 break;// 로그아웃하기
			case 9 :
				System.exit(0);
			
			default :
				FailView.errorMessage("Consol 이외의 값을 입력하였습니다.!");
			}
			
			
		}
	
		
	}


	private static void selectMovieName() throws SQLException {
		MovieService movieService = new MovieServiceImpl();
		
		String movieName = null;
			
			try{
				bf = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("=== 영화 검색 ===");
				System.out.print("검색할 영화 이름	=>	");
				movieName = bf.readLine();
				//----영화에 달린 리뷰들 호출----
				MovieController.selectMovieByName(movieName);
				MovieDTO movie = movieService.selectMovieByName(movieName);
				ReviewController.selectReviewByMovie(movie);
			}catch (IOException e) {
				e.printStackTrace();
				FailView.errorMessage("잘못된 값을 입력하였습니다.!");
			}
			
		}
			
		

	private static void selectMovieDirecter() {
	
		String movieDirecter = null;
		
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 영화 검색 ===");
			System.out.print("검색할 감독 이름	=>	");
			movieDirecter = bf.readLine();
		}catch(IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다");
		}
		
		MovieController.selectMovieByDirecter(movieDirecter);
		
}

	private static void selectMovieGenre() {
		String movieGenre = null;
		try {
			bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("=== 영화 검색 ===");
			System.out.println("검색할 장르 숫자로 입력");
			System.out.println("1: 액션, 2: 드라마, 3: 코미디, 4: 호러");
	
		movieGenre = bf.readLine();
		
		}catch(IOException e) {
			FailView.errorMessage("잘못된 값을 입력하였습니다");
		}
		MovieController.selectMovieByGenre(movieGenre);
	}
	

	private static void selectMovieReleaseDate() {

		 String movieReleaseDate = null;
		 try {
			 bf = new BufferedReader(new InputStreamReader(System.in));
			 System.out.println("=== 영화 검색 ===");
			 System.out.println("개봉 날짜 입력");
			 
			 movieReleaseDate = bf.readLine();
			 
		 }catch(IOException e) {
			 FailView.errorMessage("잘못된 값을 입력하였습니다");
		 }
		
		 MovieController.selectMovieByReleaseDate(movieReleaseDate);
}


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
		        System.out.print("영화 이름    =>  ");
		        movieName = bf.readLine();
		        System.out.println(movieName);
				/*
				 * System.out.println("영화 장르"); movieGenre = bf.readLine();
				 */
		        System.out.print("장르를 숫자로 입력하세요 (1: 액션, 2: 드라마, 3: 코미디, 4: 호러");
	            System.out.println();
		        String movieGenreStr = bf.readLine();
	            movieGenre = Integer.parseInt(movieGenreStr);
	            System.out.println(movieGenre);
		        System.out.print("영화 감독    =>  ");
		        movieDirecter = bf.readLine();
		        
		        System.out.print("개봉 날짜    =>  ");
		        releaseDate = bf.readLine();
		     
		        System.out.print("주연 배우(공백 구분)   =>  ");
		        String leadActorsInput = bf.readLine();
		        leadActor.addAll(Arrays.asList(leadActorsInput.split(" ")));
		        System.out.println(leadActor);
		        System.out.print("조연 배우(공백 구분)   =>  ");
		        String supportActorsInput = bf.readLine();
		        supportActor.addAll(Arrays.asList(supportActorsInput.split(" ")));
		        System.out.println(supportActor);
		        for (String actor : leadActor) {
		        	System.out.println("주연배우 = "+ actor);
		        }
		        
			        
		} catch (IOException e) {
			e.printStackTrace();
			FailView.errorMessage("잘못된 값을 입력하였습니다.!");
		}
	

		MovieController.insertMovie(movieName,movieGenre,movieDirecter,releaseDate,leadActor,supportActor);
		
		

	}


}
