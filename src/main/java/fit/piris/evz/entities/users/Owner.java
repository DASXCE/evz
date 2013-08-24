package fit.piris.evz.entities.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import fit.piris.evz.entities.Address;
import fit.piris.evz.entities.farm.Farm;

@Entity
@Table(name = "owners")
@PrimaryKeyJoinColumn(name = "pk_user_id")
public class Owner extends User {

	@Column(name = "personalId", unique = true, nullable = false)
	private Long personalId;

	@Column(name = "firstName", nullable = false)
	private String firstName;

	@Column(name = "lastName", nullable = false)
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "fk_address_id")
	private Address address;

	@Column(name = "phone", unique = true)
	private String phone;

	@OneToOne
	@JoinColumn(name = "fk_farm_id", unique = true)
	private Farm farm;

	public Owner() {
		super();
	}

	public Owner(Long personalId, String firstName, String lastName,
			Address address, String phone, Farm farm) {
		super();
		this.personalId = personalId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.farm = farm;
	}

	public Owner(String email, String password, Long personalId,
			String firstName, String lastName, Address address, String phone,
			Farm farm) {
		super(email, password);
		this.personalId = personalId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.farm = farm;
	}

	public Owner(String email, String password, Long personalId,
			String firstName, String lastName, Address address, String phone) {
		super(email, password);
		this.personalId = personalId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}

	public Long getPersonalId() {
		return personalId;
	}

	public void setPersonalId(Long personalId) {
		this.personalId = personalId;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}

}
