package controller;

import java.sql.SQLException;
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
	 * @param movieGenre 
	 * @param movieDirector
	 * @param releaseDate
	 * @param leadActor
	 * @param supportActor
	 * 영화 이름 , 영화 감독 , 개봉 날짜 , 주연배우 - list , 조연 배우 - list를 받는다.
	 */
	public static void insertMovie(String movieName, int movieGenre, String movieDirector, String releaseDate, List<String> leadActor,
			List<String> supportActor) {
		
		try {
			int result =service.insertMovie(movieName,movieGenre, movieDirector, releaseDate, leadActor, supportActor);
			if(result ==1) {
			SuccessView.successMessage("등록에 성공했습니다.");}
			else{
				throw new InsertException("등록하는데 오류가 발생했습니다1");
			}
		}catch (InsertException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/***
	 * 
	 * @param movieName
	 * 영화 이름으로 영화 검색
	 * @throws SearchException 
	 */
	public static void selectMovieByName(String movieName) throws SearchException {
		
		try {
			MovieDTO movie =  service.selectMovieByName(movieName);
			if(movie == null) throw new SQLException("찾는 영화가 없습니다");
			SuccessView.successMovie(movie);
			
		}catch (SQLException e) {
		//	e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/*
	 * 감독 이름으로 영화 검색
	 * 
	 * */
	
	public static void selectMovieByDirector(String movieDirector) {
		
		try {
			List<MovieDTO> moviedirector = service.selectMovieByDirector(movieDirector);
			if(moviedirector.isEmpty()) throw new SQLException("찾는 감독이 없습니다");
			SuccessView.successMessage(moviedirector);
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	/*
	 * 장르로 영화 검색
	 * */
	public static void selectMovieByGenre(String movieGenre) throws SearchException {

		try {
			List<MovieDTO> moviegenre = service.selectMovieByGenre(movieGenre);
			if(moviegenre.isEmpty()) throw new SQLException("해당 장르에 영화가 없습니다");
			SuccessView.successMessage(moviegenre);
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	/*
	 * 개봉 날짜로 영화 검색
	 */
	public static void selectMovieByReleaseDate(String movieReleaseDate) throws SearchException {
		try {
			List<MovieDTO> moviereleaseDate = service.selectMovieByReleaseDate(movieReleaseDate);
			if(moviereleaseDate.isEmpty()) throw new SQLException("날짜에 개봉된 영화가 없습니다");
			SuccessView.successMessage(moviereleaseDate);
			
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
		
	}
	
	
	

}
