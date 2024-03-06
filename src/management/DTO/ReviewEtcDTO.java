package management.DTO;

public class ReviewEtcDTO {
	private int like; // 1 이면 좋아요 -1 이면 싫어요 0 이면 default
	private int countLike;
	private int countHate;
	private int reviewEtcSeq;
	
	ReviewDTO review = new ReviewDTO();
	private int review_seq = review.getReviewSeq();
	
	UsersDTO user = new UsersDTO();
	private int user_seq = user.getUserSeq();
	
	MovieDTO movie = new MovieDTO();
	private int movie_seq = movie.getMovieSeq();
	
	public ReviewEtcDTO() {}
	
	public ReviewEtcDTO(int reviewEtc_seq, ReviewDTO review, int review_seq,
			UsersDTO user, int user_seq, int like) {
		super();
		this.like = like;
		this.reviewEtcSeq = reviewEtc_seq;
		this.review = review;
		this.review_seq = review_seq;
		this.user = user;
		this.user_seq = user_seq;
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
		return review_seq;
	}

	public void setReviewSeq(int review_seq) {
		this.review_seq = review_seq;
	}
	
	public UsersDTO getUser() {
		return user;
	}

	public void setUser(UsersDTO user) {
		this.user = user;
	}

	public int getUserSeq() {
		return user_seq;
	}

	public void setUserSeq(int user_seq) {
		this.user_seq = user_seq;
	}

	@Override
	public String toString() {
		return "ReviewEtcDTO [like=" + like + ", countLike=" + countLike + ", countHate=" + countHate + "]";
	}


	
	
}
