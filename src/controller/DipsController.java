package controller;

import java.sql.SQLException;
import java.util.List;

import exception.SearchException;
import management.DTO.DipsDTO;
import management.DTO.MovieDTO;
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

}
