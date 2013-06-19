package fit.piris.evz.entities.zivotinje;

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

import fit.piris.evz.entities.users.Veterinar;

@Entity
@Table(name = "dijagnoze")
public class Dijagnoza {

	@Id
	@NonVisual
	@Column(name = "pk_dijagnoza_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "datum")
	private Date datum;

	@Column(name = "opis_bolesti")
	private String opisBolesti;

	@Column(name = "terapija")
	private String terapija;

	@ManyToOne
	@JoinColumn(name = "fk_veterinar_id")
	private Veterinar veterinar;

	public Dijagnoza() {
	}

	public Dijagnoza(Date datum, String opisBolesti, String terapija,
			Veterinar veterinar) {
		super();
		this.datum = datum;
		this.opisBolesti = opisBolesti;
		this.terapija = terapija;
		this.veterinar = veterinar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getOpisBolesti() {
		return opisBolesti;
	}

	public void setOpisBolesti(String opisBolesti) {
		this.opisBolesti = opisBolesti;
	}

	public String getTerapija() {
		return terapija;
	}

	public void setTerapija(String terapija) {
		this.terapija = terapija;
	}

	public Veterinar getVeterinar() {
		return veterinar;
	}

	public void setVeterinar(Veterinar veterinar) {
		this.veterinar = veterinar;
	}

}
