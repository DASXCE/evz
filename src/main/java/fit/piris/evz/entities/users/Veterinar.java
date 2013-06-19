package fit.piris.evz.entities.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import fit.piris.evz.entities.Ambulanta;

@Entity
@Table(name = "veterinari")
@PrimaryKeyJoinColumn(name = "pk_fk_korisnik_id")
public class Veterinar extends User {

	@Column(name = "ime", nullable = false)
	private String ime;

	@Column(name = "prezime", nullable = false)
	private String prezime;

	@ManyToOne
	@JoinColumn(name = "fk_ambulanta_id")
	private Ambulanta ambulanta;

	public Veterinar() {
	}

	public Veterinar(String ime, String prezime, Ambulanta ambulanta) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.ambulanta = ambulanta;
	}

	public Veterinar(String email, String password, String ime, String prezime,
			Ambulanta ambulanta) {
		super(email, password);
		this.ime = ime;
		this.prezime = prezime;
		this.ambulanta = ambulanta;
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

	public Ambulanta getAmbulanta() {
		return ambulanta;
	}

	public void setAmbulanta(Ambulanta ambulanta) {
		this.ambulanta = ambulanta;
	}

}
