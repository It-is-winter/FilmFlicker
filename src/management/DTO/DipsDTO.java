package management.DTO;

import java.util.List;

public class DipsDTO {
	

	private String movieName;
	private String movieDirecter;
	private String movieGenre;
	private int userSeq;
	
	
	public DipsDTO() {}
	

	public DipsDTO(String movieName, String movieDirecter, String movieGenre, int userSeq) {
		super();
		this.movieName = movieName;
		this.movieDirecter = movieDirecter;
		this.movieGenre = movieGenre;
		this.userSeq = userSeq;
	}
	
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieDirecter() {
		return movieDirecter;
	}
	public void setMovieDirecter(String movieDirecter) {
		this.movieDirecter = movieDirecter;
	}
	public String getMovieGenre() {
		return movieGenre;
	}
	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" [movieName= ");
		builder.append(movieName);
		builder.append(", movieDirecter= ");
		builder.append(movieDirecter);
		builder.append(", movieGenre= ");
		builder.append(movieGenre);
		builder.append("]");
		return builder.toString();
	}
	

	
	
	
	
	
	
}
