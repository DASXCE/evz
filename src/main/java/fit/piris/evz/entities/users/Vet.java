package fit.piris.evz.entities.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import fit.piris.evz.entities.Infirmary;

@Entity
@Table(name = "veterinarians")
@PrimaryKeyJoinColumn(name = "pk_fk_user_id")
public class Vet extends User {

	@Column(name = "firstName", nullable = false)
	private String firstName;

	@Column(name = "lastName", nullable = false)
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "fk_infirmary_id")
	private Infirmary infirmary;

	public Vet() {
	}


	public Vet(String email, String password,String firstName, String lastName, Infirmary infirmary) {
		super(email, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.infirmary = infirmary;
	}


	public Vet(String firstName, String lastName, Infirmary infirmary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.infirmary = infirmary;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Infirmary getInfirmary() {
		return infirmary;
	}

	public void setInfirmary(Infirmary infirmary) {
		this.infirmary = infirmary;
	}

}
