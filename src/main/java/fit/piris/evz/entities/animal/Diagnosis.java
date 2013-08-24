package fit.piris.evz.entities.animal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

import fit.piris.evz.entities.users.Vet;

@Entity
@Table(name = "diagnosis")
public class Diagnosis {

	@Id
	@NonVisual
	@Column(name = "pk_diagnosis_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date")
	private Date date;

	@Column(name = "description")
	private String description;

	@Column(name = "terapy")
	private String terapy;

	@ManyToOne
	@JoinColumn(name = "fk_vet_id")
	private Vet vet;

	public Diagnosis() {
	}

	public Diagnosis(Date date, String description, String terapy, Vet vet) {
		super();
		this.date = date;
		this.description = description;
		this.terapy = terapy;
		this.vet = vet;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTerapy() {
		return terapy;
	}

	public void setTerapy(String terapy) {
		this.terapy = terapy;
	}

	public Vet getVet() {
		return vet;
	}

	public void setVet(Vet vet) {
		this.vet = vet;
	}

}
