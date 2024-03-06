package management.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import exception.InsertException;
import management.DAO.interfaces.MovieDAO;
import management.DTO.ActorDTO;
import management.DTO.MovieDTO;
import util.DbManager;


public class MovieDAOImpl implements MovieDAO {
	
	
	
	@Override
	public int insertMovie( String movieName,int movieGenre, String movieDerector, String releaseDate, List<String> leadActor,
			List<String> supportActor) throws InsertException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String sql = "insert into movie (movie_seq,movie_name,movie_genre_seq,movie_director,release_date) "
				+ "values(movie_seq.nextval,?,?,?,?)";

		try {
			//1영화정보삽입
			con = DbManager.getConnection();
			con.setAutoCommit(false);
			 
			ps = con.prepareStatement(sql);
			ps.setString(1, movieName);
			ps.setInt(2, movieGenre);
			ps.setString(3, movieDerector);
			ps.setString(4, releaseDate);
			System.out.println("여기까지가능?");
			result = ps.executeUpdate();
			con.commit();
			ps.close();
		
		       
		    int movieSeq = 0;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT movie_seq.CURRVAL FROM dual");
				 if (rs.next()) {
			            movieSeq = rs.getInt(1);
			        }
			        rs.close();
			        stmt.close();
			        
			        
		int result1 = this.insertleadActor(con,movieSeq,leadActor);
			        if(result1==0) { throw new SQLException("주연배우 입력 오류");}
			        
	    result1 = this.insertSupportActor(con, movieSeq, supportActor);
	    if(result1 ==0) {throw new SQLException("조연배우 입력 오류");}
			        System.out.println("여기까지 성공");
	      con.commit();
		}catch(SQLException e) {
			try {
	            if (con != null) {
	                con.rollback(); // 롤백
	            }
			   } catch (SQLException ex) {
		            ex.printStackTrace();
		            System.out.println("여기서 오류1");
		        }
			
			
			throw new InsertException("등록하는데 오류가 발생했습니다");
		
		}finally {
			DbManager.close(con, ps, null);
		}
		
					
		return result;
	}
	
	

	public int insertleadActor(Connection con,int movieSeq, List<String> leadActor ) {
		
		PreparedStatement ps = null;
		String sql ="insert into movie_actor (movie_actor_seq,movie_seq,actor_name, actor_role) "
				+ "values (movie_actor_seq.nextval,?,?,?)";
		int result =0;
		try {

			for(String actor : leadActor) {
			
			ps = con.prepareStatement(sql);
			ps.setInt(1,movieSeq );
			ps.setString(2,actor);
			ps.setString(3,"주연");
			System.out.println(movieSeq);
			System.out.println(actor);
			System.out.println("주연배우 등록 성공!");
			result = ps.executeUpdate();
			ps.close();
		
			System.out.println("주연배우 등록 성공!!!@");
			}
			
			//result = ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbManager.close(null, ps, null);
		}
		
		return result;
		
	}
	
public int insertSupportActor(Connection con,int movieSeq, List<String> supportActor ) {
		
		PreparedStatement ps = null;
		String sql ="insert into movie_actor (movie_actor_seq,movie_seq,actor_name, actor_role) "
				+ "values (movie_actor_seq.nextval,?,?,?)";
		int result =0;
		try {
			for(String actor : supportActor) {
			ps = con.prepareStatement(sql);
			ps.setInt(1,movieSeq );
			ps.setString(2,actor);
			ps.setString(3,"조연");
			System.out.println(movieSeq);
			System.out.println(actor);
			System.out.println("조연배우 등록 성공!");
			result = ps.executeUpdate();
			ps.close();
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbManager.close(null, ps, null);
		}
		
		return result;
		
	}
	
	
	/*
	@Override
	public int insertMovie( String movieName, String movieDerector, String releaseDate, List<String> leadActor,
			List<String> supportActor) throws InsertException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "insert into movie (movie_seq,movie_name,movie_director,release_date) "
				+ "values(movie_seq.nextval,?,?,?)";
		
		
		try {
			//1영화정보삽입
			con = DbManager.getConnection();
			con.setAutoCommit(false);
			 
			ps = con.prepareStatement(sql);
			ps.setString(1, movieName);
			ps.setString(2, movieDerector);
			ps.setString(3, releaseDate);
		
			result = ps.executeUpdate();
			ps.close();
			//2삽입된 영화의 movie_seq값 가져오기
	
			 int movieSeq = 0;
			 String actorSql = "insert into movie_actor (movie_actor_seq,movie_seq, actor_name, actor_role) "
						+ "values (movie_actor_seq.nextval,?, ?, ?)";
			 Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT movie_seq.NEXTVAL FROM dual");
		       //ResultSet rs = stmt.executeQuery("select movie_seq.currval from dual");
		        if (rs.next()) {
		            movieSeq = rs.getInt(1);
		        }
		        rs.close();
		        stmt.close();
			
		        // 주연 배우 삽입
		        for (String actor : leadActor) {
		            ps = con.prepareStatement(actorSql);
		            ps.setInt(1, movieSeq-1);
		            ps.setString(2, actor);
		            ps.setString(3, "주연");
		            System.out.println(movieSeq-1);
		            System.out.println(actor);
					ps.close();
		        }
		        
 
		        // 조연 배우 삽입
		        for (String actor : supportActor) {
		            ps = con.prepareStatement(actorSql);
		            ps.setInt(1, movieSeq-1);
		            ps.setString(2, actor);
		            ps.setString(3, "조연");
		            System.out.println(actor);
		            ps.close();

		        }
		       con.commit();
		        
			
		}catch(SQLException e) {
			try {
	            if (con != null) {
	                con.rollback(); // 롤백
	            }
			   } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			
			throw new InsertException("등록하는데 오류가 발생했습니다");
		
		}finally {
			DbManager.close(con, ps, null);
		}
		
					
		return result;
	}
*/
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
		String sql = "select movie_name,movie_director,release_date from movie where movie_name =?";
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, movieName);
			rs= ps.executeQuery();
			
			if(rs.next()) {
				
				//날짜에 년월일 패턴을 입힘
				  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		          String releaseDateStr = dateFormat.format(rs.getDate(3));

				// 배우 리스트를 불러오는 메서드 호출
		          List<ActorDTO> leadactorlist = selectLeadActor(movieName);
		          List<ActorDTO> supportactorlist = selectSupportActor(movieName);
		          
		            moviedto = new MovieDTO(0, rs.getString(1), rs.getInt(2),rs.getString(3), releaseDateStr, leadactorlist, supportactorlist);
		        }
			
			
		}finally {
			DbManager.close(con, ps, rs);
		}
		
					
		return moviedto;
	}
	//메서드끝
	
	public List<ActorDTO> selectLeadActor(String movieName) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ActorDTO> leadactorlist= new ArrayList<>();
		
		
		String sql = "select actor_name from movie_actor where actor_role='주연'";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			rs= ps.executeQuery();
			
			while (rs.next()) {
				ActorDTO actorDTO = new ActorDTO();
				actorDTO.setName(rs.getString("actor_name"));
				//actorDTO.setRole(rs.getString("actor_role"));
				leadactorlist.add(actorDTO);
	
			}
}finally {
	DbManager.close(con, ps, rs);
}
		return leadactorlist;
}//메서드 끝
	
	public List<ActorDTO> selectSupportActor(String movieName) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ActorDTO> supportactorlist= new ArrayList<>();
		
		
		String sql = "select actor_name from movie_actor where actor_role='조연'";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			rs= ps.executeQuery();
			
			while (rs.next()) {
				ActorDTO actorDTO = new ActorDTO();
				actorDTO.setName(rs.getString("actor_name"));
				supportactorlist.add(actorDTO);
	
			}
}finally {
	DbManager.close(con, ps, rs);
}
		return supportactorlist;
}//메서드 끝
}