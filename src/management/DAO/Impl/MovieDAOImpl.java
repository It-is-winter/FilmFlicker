package management.DAO.Impl;

import java.util.List;

import management.DAO.interfaces.MovieDAO;
import management.DTO.MovieDTO;

public class MovieDAOImpl implements MovieDAO {

	@Override
	public int insertMovie(String movieGenre, String movieName, String movieDerector, String releaseDate) {
		// TODO Auto-generated method stub
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
	public MovieDTO selectMovieByName(String movieName) {
		// TODO Auto-generated method stub
		return null;
	}

}
