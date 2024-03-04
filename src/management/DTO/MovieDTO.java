package management.DTO;

import java.util.List;

public class MovieDTO {
	private int movie_seq;
	private String movieName ;
	private String movieDirecter ;
	private String releaseDate ;
	
	
	private List<ActorDTO> supportActorList;
	private List<ActorDTO> leadAcotrList;
	
	public MovieDTO() {};
	
	public MovieDTO(int movie_seq, String movieName, String movieDirecter, String releaseDate
			,List<ActorDTO> supportActorList,List<ActorDTO> leadAcotrList
			) {
		super();
		this.movie_seq = movie_seq;
		this.movieName = movieName;
		this.movieDirecter = movieDirecter;
		this.releaseDate = releaseDate;
		this.supportActorList = supportActorList;
		this.leadAcotrList = leadAcotrList;
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
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public List<ActorDTO> getSupportActorList() {
		return supportActorList;
	}
	public void setSupportActorList(List<ActorDTO> supportActorList) {
		this.supportActorList = supportActorList;
	}
	public List<ActorDTO> getLeadAcotrList() {
		return leadAcotrList;
	}
	public void setLeadAcotrList(List<ActorDTO> leadAcotrList) {
		this.leadAcotrList = leadAcotrList;
	}
	/*
	 * @Override public String toString() { StringBuilder builder = new
	 * StringBuilder(); builder.append("MovieDTO [movieName=");
	 * builder.append(movieName); builder.append(", movieDirecter=");
	 * builder.append(movieDirecter); builder.append(", releaseDate=");
	 * builder.append(releaseDate); builder.append("]"); return builder.toString();
	 * }
	 * 
	 */

	@Override
	public String toString() {
		return "MovieDTO [movie_seq=" + movie_seq + ", movieName=" + movieName + ", movieDirecter=" + movieDirecter
				+ ", releaseDate=" + releaseDate + ", supportActorList=" + supportActorList + ", leadAcotrList="
				+ leadAcotrList + "]";
	}
}
