package service.Impl;

import java.util.List;

import exception.DeleteException;
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
	public List<DipsDTO> selectDipsListAll(UsersDTO users) throws SearchException {
		List<DipsDTO> list = dipsDAO.selectDipsListAll(users);
		
		if(list.isEmpty()) {
			throw new SearchException("찜목록이 없습니다.");
		}
		
		return list;
	}

	@Override
	public int insertDips(UsersDTO user, int movieSeq) throws InsertException {
		int insertdips = dipsDAO.insertDips(user, movieSeq);
		if(insertdips != 1) {
			throw new InsertException("찜등록에 실패했습니다");
		}
		
		
		return insertdips;
	}

	@Override
	public int deleteDips(DipsDTO dips) throws DeleteException {
		int result = dipsDAO.deleteDips(dips);
		if(result == 0) throw new DeleteException("찜목록 삭제에 실패했습니다."); 
		
		return result;
	}

}
