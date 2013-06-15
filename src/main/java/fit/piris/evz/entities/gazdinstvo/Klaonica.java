package fit.piris.evz.entities.gazdinstvo;

import java.util.Date;
import java.util.Set;

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
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "datum")
	private Date datum;

	@OneToMany
	@JoinColumn(name = "klaonica")
	private Set<Zivotinja> zivotinje;

	public Klaonica() {
	}

	public Klaonica(Date datum, Set<Zivotinja> zivotinje) {
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

	public Set<Zivotinja> getZivotinje() {
		return zivotinje;
	}

	public void setZivotinje(Set<Zivotinja> zivotinje) {
		this.zivotinje = zivotinje;
	}

}
