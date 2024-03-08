package controller;

import java.util.List;
import java.util.Set;

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
	 * @param movieGenre 
	 * @param movieDirecter
	 * @param releaseDate
	 * @param leadActor
	 * @param supportActor
	 * 영화 이름 , 영화 감독 , 개봉 날짜 , 주연배우 - list , 조연 배우 - list를 받는다.
	 */
	public static void insertMovie(String movieName, int movieGenre, String movieDirecter, String releaseDate, List<String> leadActor,
			List<String> supportActor) {
		
		try {
			service.insertMovie(movieName,movieGenre, movieDirecter, releaseDate, leadActor, supportActor);
			SuccessView.successMessage("등록에 성공했습니다.");
		}catch (InsertException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/***
	 * @param movieName
	 * 영화 이름으로 영화 검색
	 */
	public static MovieDTO selectMovieByName(String movieName) {
		MovieDTO movie = null;
		try {
			movie = service.selectMovieByName(movieName);
			SuccessView.successMovie(movie);
		} catch (SearchException e) {
		//	e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		return movie;
	}
	
	/*
	 * 감독 이름으로 영화 검색
	 * */
	public static List<MovieDTO> selectMovieByDirecter(String movieDirecter) {
		List<MovieDTO> moviedirecter = null;
		try {
			moviedirecter = service.selectMovieByDirecter(movieDirecter);
			SuccessView.successMessage(moviedirecter);
		}catch(SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
		return moviedirecter;
	}
	
	/*
	 * 장르로 영화 검색
	 * */
	public static List<MovieDTO> selectMovieByGenre(String movieGenre) {
		List<MovieDTO> moviegenre = null;
		try {
			moviegenre = service.selectMovieByGenre(movieGenre);
			SuccessView.successMessage(moviegenre);
		} catch(SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
		return moviegenre;
	}
	
	/*
	 * 개봉 날짜로 영화 검색
	 */
	public static Set<MovieDTO> selectMovieByReleaseDate(String movieReleaseDate) {
		Set<MovieDTO> moviereleaseDate = null;
		try {
			moviereleaseDate = service.selectMovieByReleaseDate(movieReleaseDate);
			SuccessView.successMessage(moviereleaseDate);
			
		}catch(SearchException e) {
			FailView.errorMessage(e.getMessage());
		}
		return moviereleaseDate;
	}

}
