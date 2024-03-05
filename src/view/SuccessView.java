package view;

import java.util.List;

import management.DTO.DipsDTO;
import management.DTO.MovieDTO;
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
	
	/***
	 * 
	 * @param movie
	 * movieDto만 띄울때 사용 -> 안에 list들은 따로 매소드 만들어서 for문 돌려야함.
	 */
	public static void successMovie(MovieDTO movie){
		System.out.println(movie);
	}

	public static void printPassword(UsersDTO user) {
		System.out.println("회원의 비밀번호 => "+user.getPassword());
		
	}

	public static void dipsList(List<DipsDTO> dips) {
		System.out.println("===== 회원님의 찜목록 =======");
		
		for (DipsDTO dipsDTO : dips) {
			System.out.println(dipsDTO);
		}
		
	}

}
