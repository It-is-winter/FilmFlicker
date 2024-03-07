package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

import exception.InsertException;
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
	

	//영화 제목으로 검색한 결과 찜목록에 저장
	public static void insertDips(UsersDTO user, int movieSeq) {
		try {
			int result = service.insertDips(user,movieSeq);
			if(result >0) {
				System.out.println("찜목록에 저장 성공");
			}
		}catch(InsertException | SQLException e){
			FailView.errorMessage("이미 등록된 영화입니다");
		}
		
		
	}
	
	//영화 감독이름으로 검색한 결과 중 원하는 영화만 찜목록에 저장
	public static void insertDirectorDips(UsersDTO user) {
		
		try {
		int result= service.insertDirectorDips(user);
		if(result !=0) 
			System.out.println("저장 성공");
		}catch(IOException | SQLException e) { //각각 예외처리
			
		}
		
		
	}
	
	
}
