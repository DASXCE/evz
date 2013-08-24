package fit.piris.evz.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

import fit.piris.evz.entities.animal.Animal;
import fit.piris.evz.entities.users.Owner;

@Entity
@Table(name = "sales_agreements")
public class SalesAgreement {

	@Id
	@NonVisual
	@Column(name = "pk_contract_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date")
	private Date date;

	@Column(name = "value")
	private Double value;

	@ManyToMany
	@JoinTable(name = "contracts_animals", joinColumns = { @JoinColumn(name = "fk_contract_id") }, inverseJoinColumns = {
			@JoinColumn(name = "fk_animal_tag_number"),
			@JoinColumn(name = "fk_animal_tag_country") })
	private List<Animal> animals = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "fk_seller_id")
	private Owner seller;

	@ManyToOne
	@JoinColumn(name = "fk_buyer_id")
	private Owner buyer;

	public SalesAgreement() {
	}

	public SalesAgreement(Long id, Date date, Double value,
			List<Animal> animals, Owner seller, Owner buyer) {
		super();
		this.id = id;
		this.date = date;
		this.value = value;
		this.animals = animals;
		this.seller = seller;
		this.buyer = buyer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	public Owner getSeller() {
		return seller;
	}

	public void setSeller(Owner seller) {
		this.seller = seller;
	}

	public Owner getBuyer() {
		return buyer;
	}

	public void setBuyer(Owner buyer) {
		this.buyer = buyer;
	}

}
