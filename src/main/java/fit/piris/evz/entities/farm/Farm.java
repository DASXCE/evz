package fit.piris.evz.entities.farm;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import fit.piris.evz.entities.Address;
import fit.piris.evz.entities.animal.Animal;
import fit.piris.evz.entities.users.Owner;

@Entity
@Table(name = "farms")
public class Farm {

	@Id
	@NonVisual
	@Column(name = "pk_farm_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code", unique = true)
	private String code;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "fk_address_id")
	private Address address;

	@OneToOne(mappedBy = "farm")
	private Owner owner;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "farm_products", joinColumns = { @JoinColumn(name = "fk_farm_id") }, inverseJoinColumns = { @JoinColumn(name = "fk_products_id") })
	private List<Product> products = new ArrayList<>();

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "farm_animal_species", joinColumns = { @JoinColumn(name = "fk_farm_id") }, inverseJoinColumns = { @JoinColumn(name = "fk_animal_species_id") })
	private List<AnimalSpecie> animalSpecies = new ArrayList<>();

	@OneToMany
	@JoinColumn(name = "fk_farm_id")
	private List<Animal> animals = new ArrayList<>();

	public Farm() {
	}

	public Farm(String code, String name, Address address,
			List<Product> products,
			List<AnimalSpecie> animalSpecies, List<Animal> animals) {
		super();
		this.code = code;
		this.name = name;
		this.address = address;
		this.products = products;
		this.animalSpecies = animalSpecies;
		this.animals = animals;
	}

	public Farm(Long id, String code, String name, Address address,
			Owner owner, List<Product> products,
			List<AnimalSpecie> animalSpecies, List<Animal> animals) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.owner = owner;
		this.address = address;
		this.products = products;
		this.animalSpecies = animalSpecies;
		this.animals = animals;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Owner getOwner() {
		return owner;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<AnimalSpecie> getAnimalSpecies() {
		return animalSpecies;
	}

	public void setAnimalSpecies(List<AnimalSpecie> animalSpecies) {
		this.animalSpecies = animalSpecies;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

}
