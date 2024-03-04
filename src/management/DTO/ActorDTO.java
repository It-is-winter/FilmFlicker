package management.DTO;

public class ActorDTO {

	private String name;
	private String role;
	
	
	
	public ActorDTO() {}



	public ActorDTO(String name, String role) {
		super();
		this.name = name;
		this.role = role;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "ActorDTO [name=" + name + ", role=" + role + "]";
	}

	
}
