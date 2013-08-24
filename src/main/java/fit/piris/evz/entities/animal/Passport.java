package fit.piris.evz.entities.animal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
@Table(name = "passports")
public class Passport {

	@Id
	@NonVisual
	@Column(name = "pk_passport_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Passport() {
	}

	public Passport(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
