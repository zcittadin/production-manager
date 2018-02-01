package zan.ind.productionmanager.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 2276457338706574034L;

	private String name;
	private String lastName;
	private String email;
	private String password;
	private int active;
	private String roles;

	public UserDTO() {

	}

	public UserDTO(String name, String lastName, String email, String password, int active, String roles) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserDTO [name=" + name + ", lastName=" + lastName + ", email=" + email + ", password=" + password
				+ ", active=" + active + ", roles=" + roles + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

}
