package fit.piris.evz.entities;

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

import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.entities.zivotinje.Zivotinja;

@Entity
@Table(name = "ugovori_o_prodaji")
public class UgovorOProdaji {

	@Id
	@NonVisual
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "datum")
	private Date datum;

	@Column(name = "cijena")
	private Double cijena;

	@ManyToMany
	@JoinTable(name = "ugovori_zivotinje", joinColumns = { @JoinColumn(name = "ugovor") }, inverseJoinColumns = { @JoinColumn(name = "zivotinja") })
	private Set<Zivotinja> zivotinje;

	@ManyToOne
	@JoinColumn(name = "prodavac")
	private Vlasnik prodavac;

	@ManyToOne
	@JoinColumn(name = "kupac")
	private Vlasnik kupac;

	public UgovorOProdaji() {
	}

	public UgovorOProdaji(Date datum, Double cijena, Set<Zivotinja> zivotinje,
			Vlasnik prodavac, Vlasnik kupac) {
		super();
		this.datum = datum;
		this.cijena = cijena;
		this.zivotinje = zivotinje;
		this.prodavac = prodavac;
		this.kupac = kupac;
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

	public Double getCijena() {
		return cijena;
	}

	public void setCijena(Double cijena) {
		this.cijena = cijena;
	}

	public Set<Zivotinja> getZivotinje() {
		return zivotinje;
	}

	public void setZivotinje(Set<Zivotinja> zivotinje) {
		this.zivotinje = zivotinje;
	}

	public Vlasnik getProdavac() {
		return prodavac;
	}

	public void setProdavac(Vlasnik prodavac) {
		this.prodavac = prodavac;
	}

	public Vlasnik getKupac() {
		return kupac;
	}

	public void setKupac(Vlasnik kupac) {
		this.kupac = kupac;
	}

}
