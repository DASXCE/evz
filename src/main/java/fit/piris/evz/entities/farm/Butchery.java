package fit.piris.evz.entities.farm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

import fit.piris.evz.entities.animal.Animal;

@Entity
@Table(name = "butcheries")
public class Butchery {

	@Id
	@NonVisual
	@Column(name = "pk_butchery_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date")
	private Date date;

	@OneToMany
	@JoinColumn(name = "fk_butchery_id")
	private List<Animal> animals = new ArrayList<>();

	public Butchery() {
	}

	public Butchery(Date date, List<Animal> animals) {
		super();
		this.date = date;
		this.animals = animals;
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

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

}
