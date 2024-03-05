package service.Impl;

import exception.InsertException;
import management.DTO.MovieDTO;
import management.DTO.ReviewDTO;
import management.DTO.UsersDTO;
import service.ReviewService;

public class ReviewServiceImpl implements ReviewService {


	@Override
	public int insertReview(String movieName, String review, int movieScore,UsersDTO user) throws InsertException {
		//먼저 등록된 좋아요가 있는지 찾는다
		//만약 있으면 예외 발행
		//없으면 등록 호출
		
		return 0;
	}

	@Override
	public int updateReview(MovieDTO movie) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReview(MovieDTO movie) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ReviewDTO selectReview(MovieDTO movie) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
