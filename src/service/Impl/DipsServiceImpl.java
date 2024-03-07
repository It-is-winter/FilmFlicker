package service.Impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import exception.InsertException;
import exception.SearchException;
import management.DAO.Impl.DipsDAOImpl;
import management.DAO.interfaces.DipsDAO;
import management.DTO.DipsDTO;
import management.DTO.UsersDTO;
import service.DipsService;


public class DipsServiceImpl implements DipsService {

	DipsDAO dipsDAO = new DipsDAOImpl();
	
	@Override
	public List<DipsDTO> selectDipsListAll(UsersDTO users) throws SearchException,SQLException {
		List<DipsDTO> list = dipsDAO.selectDipsListAll(users);
		
		if(list.isEmpty()) {
			throw new SearchException("찜목록이 없습니다.");
		}
		
		
		
		
		
		return list;
	}

	@Override
	public int insertDips(UsersDTO user, int movieSeq) throws InsertException, SQLException{
		
		int result = dipsDAO.insertDips(user, movieSeq);
		if(result != 1) {
			throw new InsertException("찜등록에 실패했습니다");
		}
		
		
		return result;
	}

	@Override
	public int deleteDips(UsersDTO users) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertDirectorDips(UsersDTO user) throws IOException, SQLException {

		int result= dipsDAO.insertDirectorDips(user);
		if(result != 1) {
		}
		
		return 0;
	}

}
