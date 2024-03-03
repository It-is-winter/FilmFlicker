package controller;

import java.util.List;

import exception.InsertException;
import exception.SearchException;
import management.DTO.MovieDTO;
import service.MovieService;
import service.Impl.MovieServiceImpl;
import view.FailView;
import view.SuccessView;

public class MovieController {
	
	public static MovieService service = new MovieServiceImpl();

	/***
	 * 
	 * @param movieName
	 * @param movieDirecter
	 * @param releaseDate
	 * @param leadActor
	 * @param supportActor
	 * 영화 이름 , 영화 감독 , 개봉 날짜 , 주연배우 - list , 조연 배우 - list를 받는다.
	 */
	public static void insertMovie(String movieName, String movieDirecter, String releaseDate, List<String> leadActor,
			List<String> supportActor) {
		
		try {
			service.insertMovie(movieName, movieDirecter, releaseDate, leadActor, supportActor);
			SuccessView.successMessage("등록에 성공했습니다.");
		}catch (InsertException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/***
	 * 
	 * @param movieName
	 * 영화 이름으로 영화 검색
	 */
	public static void selectMovieByName(String movieName) {
		
		try {
			MovieDTO movie =  service.selectMovieByName(movieName);
			SuccessView.successMovie(movie);
		}catch (SearchException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

}
