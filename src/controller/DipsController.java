package controller;

import java.sql.SQLException;

import management.DTO.MovieDTO;
import service.MovieService;
import service.Impl.MovieServiceImpl;
import session.UsersSession;
import session.UsersSessionSet;
import view.FailView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.SearchException;
import management.DTO.DipsDTO;
import management.DTO.UsersDTO;
import service.DipsService;
import service.Impl.DipsServiceImpl;
import view.SuccessView;

public class DipsController {
	private static MovieService movieService = new MovieServiceImpl();
	public static DipsService service = new DipsServiceImpl();
	
	public static void putDips(String usersId, String movieName) {
		try {
			MovieDTO movie = movieService.selectMovieByName(movieName);
			if(movie.getMovieName() == null) {
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
	}
		
		//영화 리스트 만들기
	/**
	 * 마이페이지에서 찜 목록 조회
	 */
	public static void selectDips(UsersDTO user) {
		try {
			
			List<DipsDTO> dips = service.selectDipsListAll(user);
			
			
			UsersSessionSet uss = UsersSessionSet.getInstance();
			UsersSession us = uss.get(user.getIdEmail());
			
			Map<Integer, DipsDTO>dipList = (Map<Integer, DipsDTO>)us.getAttribute("찜목록");
			
			if(dipList == null) { 
				dipList = new HashMap<>(); 
				us.setAttribute("찜목록", dipList);
			}
			
			int i = 0;
			
			for (DipsDTO dipsDTO : dips) {
				i++;
				dipList.put(i, dipsDTO);
			}
			SuccessView.dipsList(dipList);
			
		} catch (SearchException e) {
			FailView.errorMessage(e.getMessage());
		} catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

}
