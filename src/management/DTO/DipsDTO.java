package management.DTO;

import java.util.List;

public class DipsDTO {
	
	private String movie_name;
	private String movie_directer;
	private String movie_genre;
	private int user_seq;
	
	
	public DipsDTO() {}
	
	public DipsDTO(String movie_name, String movie_directer, String movie_genre, int user_seq) {
		super();
		this.movie_name = movie_name;
		this.movie_directer = movie_directer;
		this.movie_genre = movie_genre;
		this.user_seq = user_seq;
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
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
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
