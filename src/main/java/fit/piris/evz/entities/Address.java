package fit.piris.evz.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@NonVisual
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_address_id")
	private Long id;

	@Column(name = "town", nullable = false)
	private String town;

	@Column(name = "postal", nullable = false)
	private Integer postal;

	@Column(name = "street", nullable = false)
	private String street;

	public Address() {
	}

	public Address(Long id, String town, Integer postal, String street) {
		super();
		this.id = id;
		this.town = town;
		this.postal = postal;
		this.street = street;
	}

	public Address(String town, Integer postal, String street) {
		super();
		this.town = town;
		this.postal = postal;
		this.street = street;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public Integer getPostal() {
		return postal;
	}

	public void setPostal(Integer postal) {
		this.postal = postal;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public boolean equals(Address adresa) {
		if (this.town.equalsIgnoreCase(adresa.getTown())
				&& this.street.equalsIgnoreCase(adresa.getStreet())
				&& this.postal.equals(adresa.getPostal())) {
			return true;
		}
		return false;
	}
}
