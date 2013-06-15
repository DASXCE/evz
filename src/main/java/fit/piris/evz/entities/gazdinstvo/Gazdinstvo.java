package fit.piris.evz.entities.gazdinstvo;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

import fit.piris.evz.entities.Adresa;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.entities.zivotinje.Zivotinja;

@Entity
@Table(name = "gazdinstva")
public class Gazdinstvo {

	@Id
	@NonVisual
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sifra", unique = true)
	private String sifra;

	@Column(name = "naziv", unique = true, nullable = false)
	private String naziv;

	@ManyToOne
	@JoinColumn(name = "adresa")
	private Adresa adresa;

	@OneToOne(mappedBy = "gazdinstvo")
	private Vlasnik vlasnik;

	@ManyToMany
	@JoinTable(name = "gazdinstvo_tipovi_proizvodnje", joinColumns = { @JoinColumn(name = "gazdinstvo") }, inverseJoinColumns = { @JoinColumn(name = "tip_proizvodnje") })
	private Set<TipProizvodnje> tipProizvodnje;

	@ManyToMany
	@JoinTable(name = "gazdinstvo_vrste_zivotinja", joinColumns = { @JoinColumn(name = "gazdinstvo") }, inverseJoinColumns = { @JoinColumn(name = "vrste_zivotinja") })
	private Set<VrstaZivotinje> vrsteZivotinja;

	@OneToMany
	@JoinColumn(name = "gazdinstvo")
	private Set<Zivotinja> zivotinje;

	public Gazdinstvo() {
	}

	public Gazdinstvo(String sifra, String naziv, Adresa adresa,
			Set<TipProizvodnje> tipProizvodnje,
			Set<VrstaZivotinje> vrsteZivotinja, Set<Zivotinja> zivotinje) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.adresa = adresa;
		this.tipProizvodnje = tipProizvodnje;
		this.vrsteZivotinja = vrsteZivotinja;
		this.zivotinje = zivotinje;
	}

	public Gazdinstvo(Long id, String sifra, String naziv, Adresa adresa,
			Vlasnik vlasnik, Set<TipProizvodnje> tipProizvodnje,
			Set<VrstaZivotinje> vrsteZivotinja, Set<Zivotinja> zivotinje) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
		this.adresa = adresa;
		this.vlasnik = vlasnik;
		this.tipProizvodnje = tipProizvodnje;
		this.vrsteZivotinja = vrsteZivotinja;
		this.zivotinje = zivotinje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public Vlasnik getVlasnik() {
		return vlasnik;
	}

	public Set<TipProizvodnje> getTipProizvodnje() {
		return tipProizvodnje;
	}

	public void setTipProizvodnje(Set<TipProizvodnje> tipProizvodnje) {
		this.tipProizvodnje = tipProizvodnje;
	}

	public Set<VrstaZivotinje> getVrsteZivotinja() {
		return vrsteZivotinja;
	}

	public void setVrsteZivotinja(Set<VrstaZivotinje> vrsteZivotinja) {
		this.vrsteZivotinja = vrsteZivotinja;
	}

	public Set<Zivotinja> getZivotinje() {
		return zivotinje;
	}

	public void setZivotinje(Set<Zivotinja> zivotinje) {
		this.zivotinje = zivotinje;
	}

}
