package fit.piris.evz.entities.zivotinje;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@Table(name = "evidencije_kretanja")
public class EvidencijaKretanja {

	@Id
	@NonVisual
	@Column(name = "pk_evidencija_kretanja_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "datum_odlazka")
	private Date datumOdlazka;

	@Column(name = "datum_dolazka")
	private Date datumDolazka;

	@Column(name = "mjesto")
	private String mjesto;

	@ManyToOne
	@JoinColumn(name = "fk_veterinar_id")
	private Veterinar veterinar;

	@ManyToMany
	@JoinTable(name = "evidencije_kretanja_zivotinja", joinColumns = { @JoinColumn(name = "fk_evidencija_kretanja_id") }, inverseJoinColumns = { @JoinColumn(name = "fk_zivotinja_broj_markice"),@JoinColumn(name = "fk_zivotinja_drzava_markice") })
	private List<Zivotinja> zivotinje = new ArrayList<>();

	public EvidencijaKretanja() {
	}

	public EvidencijaKretanja(Date datumOdlazka, Date datumDolazka,
			String mjesto, Veterinar veterinar, List<Zivotinja> zivotinje) {
		super();
		this.datumOdlazka = datumOdlazka;
		this.datumDolazka = datumDolazka;
		this.mjesto = mjesto;
		this.veterinar = veterinar;
		this.zivotinje = zivotinje;
	}

	public Long getEvBroj() {
		return id;
	}

	public void setEvBroj(Long id) {
		this.id = id;
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

	public List<Zivotinja> getZivotinje() {
		return zivotinje;
	}

	public void setZivotinje(List<Zivotinja> zivotinje) {
		this.zivotinje = zivotinje;
	}

}
