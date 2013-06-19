package fit.piris.evz.entities.gazdinstvo;

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

import fit.piris.evz.entities.zivotinje.Zivotinja;

@Entity
@Table(name = "klaonice")
public class Klaonica {

	@Id
	@NonVisual
	@Column(name = "pk_klaonica_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "datum")
	private Date datum;

	@OneToMany
	@JoinColumn(name = "fk_klaonica_id")
	private List<Zivotinja> zivotinje = new ArrayList<>();

	public Klaonica() {
	}

	public Klaonica(Date datum, List<Zivotinja> zivotinje) {
		super();
		this.datum = datum;
		this.zivotinje = zivotinje;
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

	public List<Zivotinja> getZivotinje() {
		return zivotinje;
	}

	public void setZivotinje(List<Zivotinja> zivotinje) {
		this.zivotinje = zivotinje;
	}

}
