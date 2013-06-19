package fit.piris.evz.entities.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import fit.piris.evz.entities.Adresa;
import fit.piris.evz.entities.gazdinstvo.Gazdinstvo;

@Entity
@Table(name = "vlasnici")
@PrimaryKeyJoinColumn(name = "pk_fk_korisnik_id")
public class Vlasnik extends User {

	@Column(name = "jmbg", unique = true, nullable = false)
	private Long jmbg;

	@Column(name = "ime", nullable = false)
	private String ime;

	@Column(name = "prezime", nullable = false)
	private String prezime;

	@ManyToOne
	@JoinColumn(name = "fk_adresa_id")
	private Adresa adresa;

	@Column(name = "telefon", unique = true)
	private String telefon;

	@OneToOne
	@JoinColumn(name = "fk_gazdinstvo_id", unique = true)
	private Gazdinstvo gazdinstvo;

	public Vlasnik() {
		super();
	}

	public Vlasnik(String email,String password,Long jmbg, String ime, String prezime, Adresa adresa,
			String telefon) {
		super(email,password);
		this.jmbg = jmbg;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.telefon = telefon;
	}

	public Vlasnik(Long jmbg, String ime, String prezime, Adresa adresa,
			String telefon, Gazdinstvo gazdinstvo) {
		super();
		this.jmbg = jmbg;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.telefon = telefon;
		this.gazdinstvo = gazdinstvo;
	}

	public Long getJmbg() {
		return jmbg;
	}

	public void setJmbg(Long jmbg) {
		this.jmbg = jmbg;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Gazdinstvo getGazdinstvo() {
		return gazdinstvo;
	}

	public void setGazdinstvo(Gazdinstvo gazdinstvo) {
		this.gazdinstvo = gazdinstvo;
	}

}
