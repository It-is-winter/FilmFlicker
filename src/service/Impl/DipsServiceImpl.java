package service.Impl;

import java.sql.SQLException;
import java.util.List;

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
	public int insertDips(UsersDTO users) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDips(UsersDTO users) {
		// TODO Auto-generated method stub
		return 0;
	}

}