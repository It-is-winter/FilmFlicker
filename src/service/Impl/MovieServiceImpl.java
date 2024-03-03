package service.Impl;

import java.util.List;

import exception.InsertException;
import exception.SearchException;
import management.DTO.MovieDTO;
import service.MovieService;

public class MovieServiceImpl implements MovieService {


	@Override
	public int insertMovie(String movieName, String movieDirecter, String releaseDate, List<String> leadActor,
			List<String> supportActor) throws InsertException {
		
		return 0;
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
	public MovieDTO selectMovieByName(String movieName) throws SearchException {
		// TODO Auto-generated method stub
		return null;
	}

}
