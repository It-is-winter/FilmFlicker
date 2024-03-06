package management.DTO;

public class UsersDTO {

	private int userSeq;
	private String idEmail;
	private String password;
	private String name;
	private String birth;
	private String regDate;
	
	
	public UsersDTO() {};
	
	public UsersDTO(int user_seq, String idEmail, String password, String name, String birth,
			String regDate) {
		this.userSeq = user_seq;
		this.idEmail = idEmail;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.regDate = regDate;
	}


	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getIdEmail() {
		return idEmail;
	}
	public void setIdEmail(String idEmail) {
		this.idEmail = idEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsersDTO [user_seq=");
		builder.append(userSeq);
		builder.append(", idEmail=");
		builder.append(idEmail);
		builder.append(", password=");
		builder.append(password);
		builder.append(", name=");
		builder.append(name);
		builder.append(", birth=");
		builder.append(birth);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
