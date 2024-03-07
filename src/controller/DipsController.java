package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
