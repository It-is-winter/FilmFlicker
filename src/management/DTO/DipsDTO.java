package management.DTO;

import java.util.List;

public class DipsDTO {
	
	private String movie_name;
	private String movie_directer;
	private String movie_genre;
	private int userSeq;
	
	
	public DipsDTO() {}
	
	public DipsDTO(String movie_name, String movie_directer, String movie_genre, int userSeq) {
		super();
		this.movie_name = movie_name;
		this.movie_directer = movie_directer;
		this.movie_genre = movie_genre;
		this.userSeq = userSeq;
	}
	
	
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getMovie_directer() {
		return movie_directer;
	}
	public void setMovie_directer(String movie_directer) {
		this.movie_directer = movie_directer;
	}
	public String getMovie_genre() {
		return movie_genre;
	}
	public void setMovie_genre(String movie_genre) {
		this.movie_genre = movie_genre;
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
		builder.append(" [movie_name= ");
		builder.append(movie_name);
		builder.append(", movie_directer= ");
		builder.append(movie_directer);
		builder.append(", movie_genre= ");
		builder.append(movie_genre);
		builder.append("]");
		return builder.toString();
	}
	

	
	
	
	
	
	
}
