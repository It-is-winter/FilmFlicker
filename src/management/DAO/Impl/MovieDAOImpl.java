package management.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import management.DAO.interfaces.MovieDAO;
import management.DTO.ActorDTO;
import management.DTO.MovieDTO;
import util.DbManager;

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
	public MovieDTO selectMovieByName(String movieName) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MovieDTO moviedto = null;
		String sql = "select movie_name,movie_director from movie where movie_name =?";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, movieName);
			rs= ps.executeQuery();
			
			if(rs.next()) {
				/*
				 * moviedto = new MovieDTO(null,
				 * rs.getString("movie_name"),rs.getString("movie_director"),
				 * rs.getString("release_date"),rs.getString("reg_date"), null);
				 */
				
				moviedto = new MovieDTO(0,null, rs.getString(1),rs.getString(2), null, null);
				/*
				 * moviedto = new MovieDTO(rs.getInt(1),rs.getString(2),rs.getString(3),
				 * rs.getNString(4),null,null);
				 * 
				 * moviedto = new
				 * MovieDTO(null,null,rs.getString(3),rs.getString(4),rs.getString(5),
				 * rs.getNString(6));
				 */
			}
		}finally {
			DbManager.close(con, ps, rs);
		}
		
		
		/*
		 * 
		 * List<ActorDTO> leaderActorList = new ArrayList<>();
		 * 
		 * 
		 * 
		 * // 리스트를 생성하고 값을 추가 List<ActorDTO> supportActorList = new ArrayList<>();
		 * 
		 * // 예시로 여러 개의 ActorDTO를 생성하고 리스트에 추가하는 방법 for (int i = 1; i <= 3; i++) {
		 * ActorDTO actorDTO = new ActorDTO(); actorDTO.setActorName("Actor " + i); //
		 * 다른 필요한 속성들도 설정
		 * 
		 * supportActorList.add(actorDTO); }
		 * 
		 * // MovieDTO에 리스트 설정 moviedto.setSupportActorList(supportActorList);
		 */
	
		
		
		return moviedto;
	}

}
