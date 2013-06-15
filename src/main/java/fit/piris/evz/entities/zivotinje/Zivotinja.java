package fit.piris.evz.entities.zivotinje;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

import fit.piris.evz.enums.Pol;

@Entity
@Table(name = "zivotinje")
public class Zivotinja {

	@Id
	@NonVisual
	@Column(name = "zivotni_broj")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long zivotniBroj;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "markica")
	private UsnaMarkica markica;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pasos")
	private Pasos pasos;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "karton")
	private ZdravstveniKarton karton;

	@Column(name = "ime")
	private String ime;

	@Column(name = "datum_rodjenja")
	private Date datumRodjenja;

	@Column(name = "datum_uginuca")
	private Date datumUginuca;

	@Column(name = "pol")
	private Pol pol;

	@ManyToOne
	@JoinColumn(name = "majka")
	private Zivotinja majka;

	@ManyToOne
	@JoinColumn(name = "otac")
	private Zivotinja otac;

	public Zivotinja() {
	}

	public Zivotinja(Long zivotniBroj, UsnaMarkica markica, String ime,
			Date datumRodjenja, Date datumUginuca, Pol pol, Zivotinja majka,
			Zivotinja otac) {
		super();
		this.zivotniBroj = zivotniBroj;
		this.markica = markica;
		this.ime = ime;
		this.datumRodjenja = datumRodjenja;
		this.datumUginuca = datumUginuca;
		this.pol = pol;
		this.majka = majka;
		this.otac = otac;
	}

	public Long getZivotniBroj() {
		return zivotniBroj;
	}

	public void setZivotniBroj(Long zivotniBroj) {
		this.zivotniBroj = zivotniBroj;
	}

	public UsnaMarkica getMarkica() {
		return markica;
	}

	public void setMarkica(UsnaMarkica markica) {
		this.markica = markica;
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
