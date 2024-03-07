package controller;

import java.sql.SQLException;
import java.util.List;

import exception.InsertException;
import exception.SearchException;
import management.DTO.DipsDTO;
import management.DTO.UsersDTO;
import service.DipsService;
import service.Impl.DipsServiceImpl;
import view.FailView;
import view.SuccessView;

public class DipsController {

	
	public static DipsService service = new DipsServiceImpl();
	
	public static void selectDips(UsersDTO user) {
		try {
			
			List<DipsDTO> dips = service.selectDipsListAll(user);
			SuccessView.dipsList(dips);
			
		} catch (SearchException e) {
			FailView.errorMessage(e.getMessage());
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}

	
	public static void insertDips(UsersDTO user, int movieSeq) {
		try {
			int insertdips = service.insertDips(user,movieSeq);
			if(insertdips >0) {
				System.out.println("찜목록에 저장 성공");
			}
		}catch(InsertException | SQLException e){
			FailView.errorMessage("이미 등록된 영화입니다");
		}
		
		
	}
	
	
}
