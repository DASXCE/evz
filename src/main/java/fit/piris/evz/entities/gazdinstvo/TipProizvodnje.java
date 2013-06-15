package fit.piris.evz.entities.gazdinstvo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

import fit.piris.evz.enums.TipProizvoda;

@Entity
@Table(name = "tipovi_proizvodnje")
public class TipProizvodnje {

	@Id
	@NonVisual
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tip")
	private TipProizvoda tip;

	public TipProizvodnje() {
	}

	public TipProizvodnje(TipProizvoda tip) {
		super();
		this.tip = tip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipProizvoda getTip() {
		return tip;
	}

	public void setTip(TipProizvoda tip) {
		this.tip = tip;
	}

}
