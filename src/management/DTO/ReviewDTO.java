package management.DTO;

public class ReviewDTO {
	private int review_seq;
	private int score;
	private String review;
	MovieDTO movie = new MovieDTO();
	UsersDTO user = new UsersDTO();	
	
	public ReviewDTO(int review_seq, int score, String review) {
		super();
		this.review_seq = review_seq;
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

	@Override
	public String toString() {
		return "ReviewDTO [review_seq=" + review_seq + ", score=" + score + ", review=" + review + "]";
	}
	

}
