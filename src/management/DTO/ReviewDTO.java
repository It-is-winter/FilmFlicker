package management.DTO;

public class ReviewDTO {
	private int review_seq;
	private int score;
	private String review;
	
	MovieDTO movie = new MovieDTO();
	private int movie_seq = movie.getMovie_seq();
	
	UsersDTO user = new UsersDTO();	
	private int user_seq = user.getUser_seq();
	
	public ReviewDTO() {}
	
	public ReviewDTO(int review_seq, int movie_seq, String review, int score) {
		super();
		this.review_seq = review_seq;
		this.movie_seq = movie_seq;
		this.score = score;
		this.review = review;
	}

	public int getReview_seq() {
		return review_seq;
	}

	public void setReview_seq(int review_seq) {
		this.review_seq = review_seq;
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

	public int getMovie_seq() {
		return movie_seq;
	}

	public void setMovie_seq(int movie_seq) {
		this.movie_seq = movie_seq;
	}

	public UsersDTO getUser() {
		return user;
	}

	public void setUser(UsersDTO user) {
		this.user = user;
	}

	public int getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}

	@Override
	public String toString() {
		return "리뷰 : " + review +
				"\n평점 : " + score + "/10";
	}
	

}
