package fit.piris.evz.entities.animal;

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

import fit.piris.evz.entities.users.Vet;

@Entity
@Table(name = "movement_records")
public class MovementRecord {

	@Id
	@NonVisual
	@Column(name = "pk_record_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "departure_date")
	private Date departureDate;

	@Column(name = "arrival_date")
	private Date arrivalDate;

	@Column(name = "location")
	private String location;

	@ManyToOne
	@JoinColumn(name = "fk_vet_id")
	private Vet vet;

	@ManyToMany
	@JoinTable(name = "animal_movement_records", joinColumns = { @JoinColumn(name = "fk_movement_record_id") }, inverseJoinColumns = {
			@JoinColumn(name = "fk_animal_tag_number"),
			@JoinColumn(name = "fk_animal_tag_country") })
	private List<Animal> animals = new ArrayList<>();

	public MovementRecord() {
	}

	public MovementRecord(Long id, Date departureDate, Date arrivalDate,
			String location, Vet vet, List<Animal> animals) {
		super();
		this.id = id;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.location = location;
		this.vet = vet;
		this.animals = animals;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Vet getVet() {
		return vet;
	}

	public void setVet(Vet vet) {
		this.vet = vet;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

}
