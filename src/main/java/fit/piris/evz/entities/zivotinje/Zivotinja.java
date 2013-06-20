package fit.piris.evz.entities.zivotinje;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import fit.piris.evz.enums.Pol;

@Entity
@Table(name = "zivotinje")
//@IdClass(UsnaMarkica.class)
public class Zivotinja {
	
//	@Id
//	@Column(name="pk_zivotinja_broj_markice")
//	public Long broj;
//
//	@Column(name="pk_zivotinja_drzava_markice")
//	public String drzava;

	@EmbeddedId
	private UsnaMarkica markica;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_pasos_id")
	private Pasos pasos;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_karton_id")
	private ZdravstveniKarton karton;

	@Column(name = "ime")
	private String ime;

	@Column(name = "datum_rodjenja")
	private Date datumRodjenja;

	@Column(name = "datum_uginuca")
	private Date datumUginuca;

	@Enumerated(EnumType.STRING)
	@Column(name = "pol")
	private Pol pol;

	@ManyToOne
	@JoinColumns(value={@JoinColumn(name="fk_zivotinja_majka_broj_markice"),@JoinColumn(name="fk_zivotinja_majka_drzava_markice")})
	private Zivotinja majka;

	@ManyToOne
	@JoinColumns(value={@JoinColumn(name="fk_zivotinja_otac_broj_markice"),@JoinColumn(name="fk_zivotinja_otac_drzava_markice")})
	private Zivotinja otac;

	public Zivotinja() {
	}

	public Zivotinja(UsnaMarkica markica,Pasos pasos,
			ZdravstveniKarton karton, String ime, Date datumRodjenja,
			Date datumUginuca, Pol pol, Zivotinja majka, Zivotinja otac) {
		super();
		this.markica = markica;
		this.pasos = pasos;
		this.karton = karton;
		this.ime = ime;
		this.datumRodjenja = datumRodjenja;
		this.datumUginuca = datumUginuca;
		this.pol = pol;
		this.majka = majka;
		this.otac = otac;
	}

	public UsnaMarkica getMarkica() {
		return markica;
	}

	public void setMarkica(UsnaMarkica markica) {
		this.markica = markica;
	}

	public Pasos getPasos() {
		return pasos;
	}

	public void setPasos(Pasos pasos) {
		this.pasos = pasos;
	}

	public ZdravstveniKarton getKarton() {
		return karton;
	}

	public void setKarton(ZdravstveniKarton karton) {
		this.karton = karton;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Date getDatumUginuca() {
		return datumUginuca;
	}

	public void setDatumUginuca(Date datumUginuca) {
		this.datumUginuca = datumUginuca;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public Zivotinja getMajka() {
		return majka;
	}

	public void setMajka(Zivotinja majka) {
		this.majka = majka;
	}

	public Zivotinja getOtac() {
		return otac;
	}

	public void setOtac(Zivotinja otac) {
		this.otac = otac;
	}

	
}
