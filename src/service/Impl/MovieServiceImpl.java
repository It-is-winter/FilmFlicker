package service.Impl;

import java.sql.SQLException;
import java.util.List;

import exception.InsertException;
import management.DAO.Impl.MovieDAOImpl;
import management.DAO.interfaces.MovieDAO;
import management.DTO.MovieDTO;
import service.MovieService;

public class MovieServiceImpl implements MovieService {
	MovieDAO moviedao = new MovieDAOImpl();

	@Override
	public int insertMovie(String movieName,int movieGenre, String movieDirecter, String releaseDate, List<String> leadActor,
			List<String> supportActor) throws InsertException {
		
		int result = moviedao.insertMovie( movieName,movieGenre,  movieDirecter,  releaseDate, leadActor, supportActor);
		return result;
	
	}

	@Override
	public List<MovieDTO> selectMovieByGenre(String movieGenre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovieDTO> selectMovieByDerector(String movieDerector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovieDTO> selectMovieByReleaseDate(String releaseDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovieDTO selectMovieByName(String movieName) throws SQLException{
		
		MovieDTO moviedto = moviedao.selectMovieByName(movieName);
	
		if(moviedto ==null) throw new SQLException("찾는 영화가 없습니다");
	
		return moviedto;
	}
		
		
		
	

}
