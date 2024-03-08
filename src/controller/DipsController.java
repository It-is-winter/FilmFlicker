package controller;

import exception.InsertException;
import management.DTO.UsersDTO;
import service.MovieService;
import service.Impl.MovieServiceImpl;
import session.UsersSession;
import session.UsersSessionSet;
import view.FailView;

import service.DipsService;
import service.Impl.DipsServiceImpl;

public class DipsController {
	private static MovieService movieService = new MovieServiceImpl();
	public static DipsService service = new DipsServiceImpl();
	

	/**
	 * 영화 목록 보기
	 * */
	public static void movieList(String usersId) {
		UsersSessionSet uss = UsersSessionSet.getInstance();
		UsersSession session = uss.get(usersId);
	}
	
	/**
	 * 찜목록 추가하기
	 * @param user
	 * @param movieSeq
	 */
	public static void insertDips(UsersDTO user, int movieSeq) {
		try {
			int insertdips = service.insertDips(user,movieSeq);
			if(insertdips >0) {
				System.out.println("찜목록에 저장 성공");
			}
		}catch(InsertException e){
			FailView.errorMessage("이미 등록된 영화입니다");
		}
	}

}
