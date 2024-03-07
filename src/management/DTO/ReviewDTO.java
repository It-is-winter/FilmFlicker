package management.DTO;

public class ReviewDTO {
	private int reviewSeq;
	private int score;
	private String review;
	
	MovieDTO movie = new MovieDTO();
	private int movieSeq = movie.getMovieSeq();
	
	UsersDTO user = new UsersDTO();	
	private int userSeq = user.getUserSeq();
	private String userName = user.getName();
	
	public ReviewDTO() {}
	
	public ReviewDTO( int userSeq, int movieSeq, String review, int score) {
		super();
		this.movieSeq = movieSeq;
		this.userSeq = userSeq;
		this.score = score;
		this.review = review;
	}
	
	public ReviewDTO(int reviewSeq, int userSeq, int movieSeq, String review, int score) {
		super();
		this.movieSeq = movieSeq;
		this.userSeq = userSeq;
		this.reviewSeq = reviewSeq;
		this.score = score;
		this.review = review;
	}

	public int getReviewSeq() {
		return reviewSeq;
	}

	public void setReviewSeq(int reviewSeq) {
		this.reviewSeq = reviewSeq;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public MovieDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}

	public int getMovieSeq() {
		return movieSeq;
	}


	public void setMovieSeq(int movieSeq) {
		this.movieSeq = movieSeq;
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

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return review + "\n  평점 : " + score + "/10";
	}
	

}
