package management.DTO;

public class UsersDTO {

	private int user_seq;
	private String idEmail;
	private String password;
	private String name;
	private String birth;
	
	
	public UsersDTO() {};
	
	public UsersDTO(int user_seq, String idEmail, String password, String name, String birth) {
		this.user_seq = user_seq;
		this.idEmail = idEmail;
		this.password = password;
		this.name = name;
		this.birth = birth;
	}


	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsersDTO [user_seq=");
		builder.append(user_seq);
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
