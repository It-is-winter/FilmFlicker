package management.DTO;

import java.util.List;

public class MovieDTO {
	private int movieSeq;
	private String movieGenre;
	private String movieName ;
	private String movieDirecter ;
	private String releaseDate ;
	
	
	private List<ActorDTO> supportActorList;
	private List<ActorDTO> leadAcotrList;
	
	public MovieDTO() {};
		
	public MovieDTO(int movieSeq,String movieName, String movieGenre,  String movieDirecter, String releaseDate,
			List<ActorDTO> supportActorList, List<ActorDTO> leadAcotrList) {
		super();
		this.movieSeq = movieSeq;
		this.movieGenre = movieGenre;
		this.movieName = movieName;
		this.movieDirecter = movieDirecter;
		this.releaseDate = releaseDate;
		this.supportActorList = supportActorList;
		this.leadAcotrList = leadAcotrList;
	}


	
	public int getMovieSeq() {
		return movieSeq;
	}

	public void setMovieSeq(int movieSeq) {
		this.movieSeq = movieSeq;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
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
	

	@Override
	public String toString() {
		return  "영화 제목 = " + movieName +", 영화 장르 = "+movieGenre +", 감독 =" + movieDirecter
				+ ", 개봉일 =" + releaseDate + ", 주연배우 = " +leadAcotrList  + ", 조연배우 = "
				+ supportActorList;
	}
}
