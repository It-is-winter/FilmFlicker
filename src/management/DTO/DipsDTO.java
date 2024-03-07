package management.DTO;

import java.util.List;

public class DipsDTO {
	

	private String movieName;
	private String movieDirector;
	private String movieGenre;
	private int userSeq;
	
	
	public DipsDTO() {}
	

	public DipsDTO(String movieName, String movieDirector, String movieGenre, int userSeq) {
		super();
		this.movieName = movieName;
		this.movieDirector = movieDirector;
		this.movieGenre = movieGenre;
		this.userSeq = userSeq;
	}
	
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieDirector() {
		return movieDirector;
	}
	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
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
		builder.append(", movieDirector= ");
		builder.append(movieDirector);
		builder.append(", movieGenre= ");
		builder.append(movieGenre);
		builder.append("]");
		return builder.toString();
	}
	

	
	
	
	
	
	
}
