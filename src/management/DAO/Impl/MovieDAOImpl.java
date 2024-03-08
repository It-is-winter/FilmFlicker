package management.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exception.InsertException;
import exception.SearchException;
import management.DAO.interfaces.MovieDAO;
import management.DTO.ActorDTO;
import management.DTO.MovieDTO;
import util.DbManager;


public class MovieDAOImpl implements MovieDAO {
	
	/**
	 * 영화 등록
	 */
	@Override
	public int insertMovie( String movieName,int movieGenre, String movieDerector,
			String releaseDate, List<String> leadActor, List<String> supportActor) throws InsertException {
		
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String sql = "insert into movie (movie_seq,movie_name,movie_genre_seq,movie_director,release_date) "
				+ "values(movie_seq_no.nextval,?,?,?,?)";

		try {
			//1영화정보삽입
			con = DbManager.getConnection();
			con.setAutoCommit(false);
			 
			ps = con.prepareStatement(sql);
			ps.setString(1, movieName);
			ps.setInt(2, movieGenre);
			ps.setString(3, movieDerector);
			ps.setString(4, releaseDate);
			result = ps.executeUpdate();
			
			con.commit();
			ps.close();
			
		    int movieSeq = 0;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT movie_seq_no.CURRVAL FROM dual");
			
			if (rs.next()) {
				movieSeq = rs.getInt(1);
			}
			
			rs.close();
			stmt.close();
			
			int result1 = this.insertleadActor(con,movieSeq,leadActor);
			
			if(result1==0) {
				throw new InsertException("주연 배우 등록 실패");
			}
			
			result1 = this.insertSupportActor(con, movieSeq, supportActor);
			
			if(result1 ==0) {
				throw new InsertException("조연 배우 등록 실패");
			}
			
			con.commit();
		} catch(SQLException e) {
			try {
				if (con != null) {
					con.rollback(); // 롤백
				}
			} catch (SQLException ex) {
				System.out.println("프로그램 오류");
			}
			
			
		//	throw new InsertException("등록하는데 오류가 발생했습니다");
		
		} finally {
			DbManager.close(con, ps, null);
		}
		
					
		return result;
	}
	
	
	/**
	 * 주연 배우 등록 메서드
	 * @param con
	 * @param movieSeq
	 * @param leadActor
	 * @return
	 */
	public int insertleadActor(Connection con,int movieSeq, List<String> leadActor ) throws InsertException {
		
		PreparedStatement ps = null;
		String sql ="insert into movie_actor (movie_actor_seq,movie_seq,actor_name, actor_role) "
				+ "values (movie_actor_seq_no.nextval,?,?,?)";
		int result =0;
		try {

			for(String actor : leadActor) {
			
			ps = con.prepareStatement(sql);
			ps.setInt(1,movieSeq );
			ps.setString(2,actor);
			ps.setString(3,"주연");
//			System.out.println("주연배우 등록 성공!");
			result = ps.executeUpdate();
			ps.close();
		
			}
			
			//result = ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("프로그램 오류");
		}finally {
			DbManager.close(null, ps, null);
		}
		
		return result;
		
	}
	
	/**
	 * 조연 배우 등록 메서드
	 * @param con
	 * @param movieSeq
	 * @param supportActor
	 * @return
	 */
	public int insertSupportActor(Connection con,int movieSeq, List<String> supportActor ) throws InsertException {
		
		PreparedStatement ps = null;
		String sql ="insert into movie_actor (movie_actor_seq,movie_seq,actor_name, actor_role) "
				+ "values (movie_actor_seq_no.nextval,?,?,?)";
		int result =0;
		try {
			for(String actor : supportActor) {
				ps = con.prepareStatement(sql);
				ps.setInt(1,movieSeq );
				ps.setString(2,actor);
				ps.setString(3,"조연");
//				System.out.println("조연배우 등록 성공!");
				result = ps.executeUpdate();
				ps.close();
			}
		}catch(SQLException e) {
			System.out.println("프로그램 오류");
		}finally {
			DbManager.close(null, ps, null);
		}
		
		return result;
		
	}
	
	/**
	 * 장르로 영화 찾기
	 */
		@Override
	public List<MovieDTO> selectMovieByGenre(String movieGenre) throws SearchException {
			
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<MovieDTO> list = new ArrayList<>();
			List<String> genrelist = new ArrayList<>();
			String sql = "select movie_name from movie where movie_genre_seq =?"; //검색 장르에 해당하는 영화 제목 가지고오기
			
			MovieDTO moviedto =null;
			
			try {
				con = DbManager.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, movieGenre);
				rs= ps.executeQuery();
				
				while(rs.next()) {
					genrelist.add(rs.getString(1));
					
				}
				
				for(String movieName : genrelist) {
					moviedto = selectMovieByName(movieName);
					list.add(moviedto);
				}
				
			} catch(SQLException e) {
				System.out.println("프로그램 오류");
			} finally {
				DbManager.close(con, ps, rs);
			}
			
			return list;
	}
		
	/**
	 * 감독이름으로 영화찾기
	 */
	@Override
	public List<MovieDTO> selectMovieByDirector(String movieDirector) throws SearchException {
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		List<MovieDTO> list = new ArrayList<MovieDTO>();		
		List<String> movienamelist = new ArrayList<>();
		String sql ="select movie_name from movie where movie_director =?"; //영화 감독이름에 해당하는 영화 가지고오기
		
		MovieDTO moviedto =null;
			
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, movieDirector);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				movienamelist.add(rs.getString(1));// 영화 이름을 리스트에 저장
			}
			
			for(String movieName : movienamelist) {
				moviedto =selectMovieByName(movieName);	
				list.add(moviedto);
			}
			
		} catch(SQLException e) {
			System.out.println("프로그램 오류");
		} finally {
			DbManager.close(con, ps, rs);
		}
		return list;
	}
		
		
	/**
	 * 개봉 연도로 영화찾기(배우 있는 경우)
	 */
	@Override
	public Set<MovieDTO> selectMovieByReleaseDate(String releaseDate) throws SearchException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<MovieDTO> list = new HashSet<MovieDTO>();
		List<ActorDTO> leadList = null;
		List<ActorDTO> supportList = null;
		MovieDTO moviedto = null;
		
		String sql = "SELECT * FROM view_movie_ACTOR_GENRE WHERE TO_CHAR(RELEASE_DATE,'YYYY') = ?";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, releaseDate);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				moviedto = new MovieDTO();
				moviedto.setMovieName(rs.getString("movie_name"));
				moviedto.setMovieGenre(rs.getString("movie_genre"));
				moviedto.setMovieDirector(rs.getString("movie_director"));
				moviedto.setReleaseDate(releaseDate);
				leadList = this.selectLeadActor(moviedto);
				supportList = this.selectSupportActor(moviedto);
				moviedto.setLeadAcotrList(leadList);
				moviedto.setSupportActorList(supportList);
				list.add(moviedto);
			}
			
			if(list.isEmpty()) {
				selectMovieByReleaseDate(con, releaseDate);
			}

		} catch(SQLException e) {
			System.out.println("프로그램 오류");
		} finally {
			DbManager.close(con, ps, rs);
		}
		
		return list;
	}
	
	
	/**
	 * 개봉 연도로 영화찾기(배우 없는 경우)
	 */
	@Override
	public Set<MovieDTO> selectMovieByReleaseDate(Connection con, String releaseDate) throws SearchException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<MovieDTO> list = new HashSet<MovieDTO>();
		List<ActorDTO> leadList = null;
		List<ActorDTO> supportList = null;
		MovieDTO moviedto = null;
		
		String sql = "SELECT * FROM VIEW_MOVIE_GENRE_INFO WHERE TO_CHAR(RELEASE_DATE,'YYYY') = ?";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, releaseDate);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				moviedto = new MovieDTO();
				moviedto.setMovieName(rs.getString("movie_name"));
				moviedto.setMovieGenre(rs.getString("movie_genre"));
				moviedto.setMovieDirector(rs.getString("movie_director"));
				moviedto.setReleaseDate(releaseDate);
				leadList = this.selectLeadActor(moviedto);
				supportList = this.selectSupportActor(moviedto);
				moviedto.setLeadAcotrList(leadList);
				moviedto.setSupportActorList(supportList);
				list.add(moviedto);
			}

		} catch(SQLException e) {
			System.out.println("프로그램 오류");
		} finally {
			DbManager.close(null, ps, rs);
		}
		
		return list;
	}
	
	
	/**
	 * 영화 이름으로 영화찾기
	 */
	@Override
	
	public MovieDTO selectMovieByName(String movieName) throws SearchException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MovieDTO moviedto = null;
		//String sql = "select movie_name,movie_genre,movie_directer,release_date from movie a inner join movie_genre b on a.movie_genre_seq = b.movie_genre_seq "
		//		+ "where movie_name =?";
		String sql ="select movie_seq,movie_name,movie_genre,movie_director,release_date from movie a inner join movie_genre b on a.movie_genre_seq = b.movie_genre_seq where movie_name = ?";
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, movieName);
			rs= ps.executeQuery();
			if(rs.next()) {
				//날짜에 년월일 패턴을 입힘
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        String releaseDateStr = dateFormat.format(rs.getDate(5));

				// 배우 리스트를 불러오는 메서드 호출

		        moviedto = new MovieDTO(rs.getInt("movie_seq"), rs.getString("movie_name"), rs.getString("movie_genre"),rs.getString("movie_director"),
		            		releaseDateStr, null, null);
		         
		        List<ActorDTO> leadactorlist = selectLeadActor(moviedto);
		        List<ActorDTO> supportactorlist = selectSupportActor(moviedto);
		        
		        moviedto.setLeadAcotrList(leadactorlist);
		        moviedto.setSupportActorList(supportactorlist);
		        
		        }
			
		} catch(SQLException e) {
			System.out.println("프로그램 오류");
		} finally {
			DbManager.close(con, ps, rs);
		}
					
		return moviedto;
	}
	//메서드끝


	/**
	 * 주연 배우 리스트 찾기
	 * @param movieSeq
	 * @return
	 * @throws SQLException
	 */
	private List<ActorDTO> selectLeadActor(MovieDTO moviedto) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ActorDTO> leadactorlist= new ArrayList<>();
		
		
		String sql = "select actor_name from view_movie_ACTOR_GENRE where movie_NAME = ? and actor_role='주연'";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, moviedto.getMovieName());
			rs= ps.executeQuery();
	
			while (rs.next()) {
				ActorDTO actorDTO = new ActorDTO();
				actorDTO.setName(rs.getString("actor_name"));
				//actorDTO.setRole(rs.getString("actor_role"));
				leadactorlist.add(actorDTO);
	
			}
		} catch(SQLException e) {
			System.out.println("프로그램 오류");
		} finally {
			DbManager.close(con, ps, rs);
		}
		
		return leadactorlist;
	}//메서드 끝
	
	
	/**
	 * 조연 배우 리스트 찾기
	 * @param movieSeq
	 * @return
	 * @throws SQLException
	 */
	private List<ActorDTO> selectSupportActor(MovieDTO moviedto) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ActorDTO> supportactorlist= new ArrayList<>();
		
		
		String sql = "select actor_name from view_movie_ACTOR_GENRE where movie_NAME = ? and actor_role='조연'";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, moviedto.getMovieName());
			rs= ps.executeQuery();
			
			while (rs.next()) {
				ActorDTO actorDTO = new ActorDTO();
				actorDTO.setName(rs.getString("actor_name"));
				supportactorlist.add(actorDTO);
	
			}
		} catch(SQLException e) {
			System.out.println("프로그램 오류");
		} finally {
			DbManager.close(con, ps, rs);
		}
		return supportactorlist;
	}//메서드 끝
}
