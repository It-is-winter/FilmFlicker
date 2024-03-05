package service.Impl;

import java.util.List;

import exception.DeleteException;
import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DAO.Impl.ReviewDAOImpl;
import management.DAO.Impl.ReviewEtcDAOImpl;
import management.DAO.interfaces.ReviewDAO;
import management.DAO.interfaces.ReviewEtcDAO;
import management.DTO.MovieDTO;
import management.DTO.ReviewDTO;
import management.DTO.ReviewEtcDTO;
import management.DTO.UsersDTO;
import service.ReviewService;

public class ReviewServiceImpl implements ReviewService {
	
	ReviewDAO reviewDAO = new ReviewDAOImpl();
	ReviewEtcDAO reviewEtcDAO = new ReviewEtcDAOImpl();
	
	
	@Override
	public void insertReview(ReviewDTO review, MovieDTO movie, UsersDTO user) throws InsertException {
		//먼저 등록된 좋아요가 있는지 찾는다
		//만약 있으면 예외 발행
		//없으면 등록 호출
		int result = reviewDAO.insertReview(review, movie, user);
		if(result == 0) throw new InsertException("리뷰 입력에 실패했습니다."); 
		
	}

	@Override
	public void updateReview(ReviewDTO review, MovieDTO movie, UsersDTO user) throws UpdateException {
		int result = reviewDAO.updateReview(review, movie, user);
		if(result == 0) throw new UpdateException("리뷰 입력에 실패했습니다."); 
	}

	@Override
	public void deleteReview(ReviewDTO review) throws DeleteException {
		int result = reviewDAO.deleteReview(review);
		if(result == 0) throw new DeleteException("리뷰 입력에 실패했습니다."); 
	}

	@Override
	public ReviewDTO selectReview(ReviewDTO review) throws SearchException {
		ReviewDTO reviewDTO = reviewDAO.selectReview(review);
		if(reviewDTO==null) throw new SearchException("리뷰 입력에 실패했습니다.");
		return reviewDTO;
	}
	
	@Override
	public List<ReviewDTO> selectReviewByMovie(MovieDTO movie) throws SearchException {
		List<ReviewDTO> list = reviewDAO.selectReviewByMovie(movie);
		if(list==null || list.size()==0) throw new SearchException("리뷰 입력에 실패했습니다.");
		return list;
	}
	
	@Override
	public List<ReviewDTO> selectReviewByUser(UsersDTO user) throws SearchException {
		List<ReviewDTO> list = reviewDAO.selectReviewByUser(user);
		if(list==null || list.size()==0) throw new SearchException("리뷰 입력에 실패했습니다.");
		return list;
	}

	@Override
	public void insertLikeReview(ReviewEtcDTO reviewEtc) throws InsertException {
		//먼저 등록된 좋아요가 있는지 찾는다
		//만약 있으면 예외 발행
		//없으면 등록 호출
		
		
		int result = reviewEtcDAO.insertLike(reviewEtc);
		if(result==0) throw new InsertException("좋아요/싫어요 등록이 실패하였습니다.");
	}
	
	@Override
	public void updateLikeReview(ReviewEtcDTO reviewEtc) throws UpdateException {
		
		int result = reviewEtcDAO.updateLike(reviewEtc);
		if(result==0) throw new UpdateException("좋아요/싫어요 수정이 실패하였습니다.");
		
	}

	@Override
	public int countLike(ReviewDTO review) throws SearchException {
		int result = reviewEtcDAO.countLike(review);
		return result;
	}

	@Override
	public int countHate(ReviewDTO review) throws SearchException {
		int result = reviewEtcDAO.countHate(review);
		return result;
	}

}
