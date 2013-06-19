package fit.piris.evz.entities.zivotinje;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
@Table(name = "pasosi")
public class Pasos {

	@Id
	@NonVisual
	@Column(name = "pk_pasos_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Pasos() {
	}

	public Pasos(Long id) {
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
