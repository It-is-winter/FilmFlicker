package management.DTO;

public class ReviewEtcDTO {
	private int like; // 1 이면 좋아요 -1 이면 싫어요 0 이면 default
	private int countLike;
	private int countHate;
	private int reviewEtcSeq;
	
	ReviewDTO review = new ReviewDTO();
	private int reviewSeq = review.getReviewSeq();
	
	UsersDTO user = new UsersDTO();
	private int UserSeq = user.getUserSeq();
	
	MovieDTO movie = new MovieDTO();
	private int MovieSeq = movie.getMovieSeq();
	
	public ReviewEtcDTO() {}
	
	public ReviewEtcDTO(int reviewEtcSeq, ReviewDTO review, int reviewSeq,
			UsersDTO user, int UserSeq, int like) {
		super();
		this.like = like;
		this.reviewEtcSeq = reviewEtcSeq;
		this.review = review;
		this.reviewSeq = reviewSeq;
		this.user = user;
		this.UserSeq = UserSeq;
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
	public int getCountHate() {
		return countHate;
	}
	public void setCountHate(int countHate) {
		this.countHate = countHate;
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
		return UserSeq;
	}

	public void setUserSeq(int UserSeq) {
		this.UserSeq = UserSeq;
	}

	@Override
	public String toString() {
		return "ReviewEtcDTO [like=" + like + ", countLike=" + countLike + ", countHate=" + countHate + "]";
	}


	
	
}
