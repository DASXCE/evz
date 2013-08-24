package fit.piris.evz.entities.farm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

import fit.piris.evz.enums.Species;

@Entity
@Table(name = "animal_species")
public class AnimalSpecie {

	@Id
	@NonVisual
	@Column(name = "pk_animal_specie_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "specie",unique=true)
	private Species specie;

	public AnimalSpecie() {
	}

	public AnimalSpecie(Species specie) {
		super();
		this.specie = specie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Species getSpecie() {
		return specie;
	}

	public void setSpecie(Species specie) {
		this.specie = specie;
	}

}
