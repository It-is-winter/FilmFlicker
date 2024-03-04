package controller;

import java.sql.SQLException;

import management.DTO.MovieDTO;
import service.MovieService;
import service.Impl.MovieServiceImpl;
import session.UsersSession;
import session.UsersSessionSet;
import view.FailView;

public class DipsController {
	private static MovieService movieService = new MovieServiceImpl();
	
	public static void putDips(String usersId, String movieName) {
		try {
			MovieDTO movie = movieService.selectMovieByName(movieName);
			if(movieName == null) {
				throw new SQLException("찾는 영화가 없습니다.");
			}
			UsersSessionSet uss = UsersSessionSet.getInstance();
			UsersSession session = uss.get(usersId);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	/**
	 * 영화 목록 보기
	 * */
	public static void movieList(String usersId) {
		UsersSessionSet uss = UsersSessionSet.getInstance();
		UsersSession session = uss.get(usersId);
		
		//영화 리스트 만들기
	}

}
