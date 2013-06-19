package fit.piris.evz.entities.gazdinstvo;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import fit.piris.evz.entities.Adresa;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.entities.zivotinje.Zivotinja;

@Entity
@Table(name = "gazdinstva")
public class Gazdinstvo {

	@Id
	@NonVisual
	@Column(name = "pk_gazdinstvo_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sifra", unique = true)
	private String sifra;

	@Column(name = "naziv", unique = true, nullable = false)
	private String naziv;

	@ManyToOne
	@JoinColumn(name = "fk_adresa_id")
	private Adresa adresa;

	@OneToOne(mappedBy = "gazdinstvo")
	private Vlasnik vlasnik;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "gazdinstvo_tipovi_proizvodnje", joinColumns = { @JoinColumn(name = "fk_gazdinstvo_id") }, inverseJoinColumns = { @JoinColumn(name = "fk_tip_proizvodnje_id") })
	private List<TipProizvodnje> tipProizvodnje= new ArrayList<>();

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "gazdinstvo_vrste_zivotinja", joinColumns = { @JoinColumn(name = "fk_gazdinstvo_id") }, inverseJoinColumns = { @JoinColumn(name = "fk_vrsta_zivotinje_id") })
	private List<VrstaZivotinje> vrsteZivotinja= new ArrayList<>();

	@OneToMany
	@JoinColumn(name = "fk_gazdinstvo_id")
	private List<Zivotinja> zivotinje = new ArrayList<>();

	public Gazdinstvo() {
	}

	public Gazdinstvo(String sifra, String naziv, Adresa adresa,
			List<TipProizvodnje> tipProizvodnje,
			List<VrstaZivotinje> vrsteZivotinja, List<Zivotinja> zivotinje) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.adresa = adresa;
		this.tipProizvodnje = tipProizvodnje;
		this.vrsteZivotinja = vrsteZivotinja;
		this.zivotinje = zivotinje;
	}

	public Gazdinstvo(Long id, String sifra, String naziv, Adresa adresa,
			Vlasnik vlasnik, List<TipProizvodnje> tipProizvodnje,
			List<VrstaZivotinje> vrsteZivotinja, List<Zivotinja> zivotinje) {
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

	public List<TipProizvodnje> getTipProizvodnje() {
		return tipProizvodnje;
	}

	public void setTipProizvodnje(List<TipProizvodnje> tipProizvodnje) {
		this.tipProizvodnje = tipProizvodnje;
	}

	public List<VrstaZivotinje> getVrsteZivotinja() {
		return vrsteZivotinja;
	}

	public void setVrsteZivotinja(List<VrstaZivotinje> vrsteZivotinja) {
		this.vrsteZivotinja = vrsteZivotinja;
	}

	public List<Zivotinja> getZivotinje() {
		return zivotinje;
	}

	public void setZivotinje(List<Zivotinja> zivotinje) {
		this.zivotinje = zivotinje;
	}

}
