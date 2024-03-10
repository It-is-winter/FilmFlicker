package management.DTO;

public class ReviewEtcDTO {
	private int like; // 1 이면 좋아요 -1 이면 싫어요 0 이면 default
	private int countLike;
//	private int reviewEtcSeq;
	
	ReviewDTO review = new ReviewDTO();
	private int reviewSeq = review.getReviewSeq();
	
	UsersDTO user = new UsersDTO();
	private int userSeq = user.getUserSeq();
	
//	MovieDTO movie = new MovieDTO();
//	private int MovieSeq = movie.getMovieSeq();
	
	public ReviewEtcDTO() {}
	
	public ReviewEtcDTO(int userSeq, int reviewSeq, int like) {
		super();
		this.like = like;
		this.reviewSeq = reviewSeq;
		this.userSeq = userSeq;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getCountLike() {
		return countLike;
	}
	public void setCountLike(int countLike) {
		this.countLike = countLike;
	}
	
	public ReviewDTO getReview() {
		return review;
	}

	public void setReview(ReviewDTO review) {
		this.review = review;
	}

	public int getReviewSeq() {
		return reviewSeq;
	}

	public void setReviewSeq(int reviewSeq) {
		this.reviewSeq = reviewSeq;
	}
	
	public UsersDTO getUser() {
		return user;
	}

	public void setUser(UsersDTO user) {
		this.user = user;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int UserSeq) {
		this.userSeq = UserSeq;
	}

	@Override
	public String toString() {
		return "ReviewEtcDTO [like=" + like + ", countLike=" + countLike + "]";
	}


	
	
}
