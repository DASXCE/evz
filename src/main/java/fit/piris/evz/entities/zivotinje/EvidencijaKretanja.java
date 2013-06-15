package fit.piris.evz.entities.zivotinje;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

import fit.piris.evz.entities.users.Veterinar;

@Entity
@Table(name = "ev_kretanja")
public class EvidencijaKretanja {

	@Id
	@NonVisual
	@Column(name = "evidencioni_broj")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long evBroj;

	@Column(name = "datum_odlazka")
	private Date datumOdlazka;

	@Column(name = "datum_dolazka")
	private Date datumDolazka;

	@Column(name = "mjesto")
	private String mjesto;

	@ManyToOne
	@JoinColumn(name = "veterinar")
	private Veterinar veterinar;

	@ManyToMany
	@JoinTable(name = "ev_kretanja_zivotinja", joinColumns = { @JoinColumn(name = "ev_kretanja") }, inverseJoinColumns = { @JoinColumn(name = "zivotinja") })
	private Set<Zivotinja> zivotinje;

	public EvidencijaKretanja() {
	}

	public EvidencijaKretanja(Date datumOdlazka, Date datumDolazka,
			String mjesto, Veterinar veterinar, Set<Zivotinja> zivotinje) {
		super();
		this.datumOdlazka = datumOdlazka;
		this.datumDolazka = datumDolazka;
		this.mjesto = mjesto;
		this.veterinar = veterinar;
		this.zivotinje = zivotinje;
	}

	public Long getEvBroj() {
		return evBroj;
	}

	public void setEvBroj(Long evBroj) {
		this.evBroj = evBroj;
	}

	public Date getDatumOdlazka() {
		return datumOdlazka;
	}

	public void setDatumOdlazka(Date datumOdlazka) {
		this.datumOdlazka = datumOdlazka;
	}

	public Date getDatumDolazka() {
		return datumDolazka;
	}

	public void setDatumDolazka(Date datumDolazka) {
		this.datumDolazka = datumDolazka;
	}

	public String getMjesto() {
		return mjesto;
	}

	public void setMjesto(String mjesto) {
		this.mjesto = mjesto;
	}

	public Veterinar getVeterinar() {
		return veterinar;
	}

	public void setVeterinar(Veterinar veterinar) {
		this.veterinar = veterinar;
	}

	public Set<Zivotinja> getZivotinje() {
		return zivotinje;
	}

	public void setZivotinje(Set<Zivotinja> zivotinje) {
		this.zivotinje = zivotinje;
	}

}
