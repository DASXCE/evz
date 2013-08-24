package fit.piris.evz.entities.animal;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
//import javax.persistence.Id;
//import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fit.piris.evz.enums.Sex;

@Entity
@Table(name = "animals")
// @IdClass(UsnaMarkica.class)
public class Animal {

	// @Id
	// @Column(name="pk_zivotinja_broj_markice")
	// public Long broj;
	//
	// @Column(name="pk_zivotinja_drzava_markice")
	// public String drzava;

	@EmbeddedId
	private EarTag tag;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_passport_id")
	private Passport passport;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_medical_record_id")
	private MedicalRecord medicalRecord;

	@Column(name = "name")
	private String name;

	@Column(name = "birth_date")
	private Date born;

	@Column(name = "death_date")
	private Date died;

	@Enumerated(EnumType.STRING)
	@Column(name = "sex")
	private Sex sex;

	@ManyToOne
	@JoinColumns(value = { @JoinColumn(name = "fk_animal_mother_tag_number"),@JoinColumn(name = "fk_animal_mother_tag_country") })
	private Animal mother;

	@ManyToOne
	@JoinColumns(value = { @JoinColumn(name = "fk_animal_father_tag_number"),@JoinColumn(name = "fk_animal_father_tag_country") })
	private Animal father;

	public Animal() {
	}

	public Animal(EarTag tag, Passport passport, MedicalRecord medicalRecord,
			String name, Date born, Date died, Sex sex, Animal mother,
			Animal father) {
		super();
		this.tag = tag;
		this.passport = passport;
		this.medicalRecord = medicalRecord;
		this.name = name;
		this.born = born;
		this.died = died;
		this.sex = sex;
		this.mother = mother;
		this.father = father;
	}

	public Animal(Passport passport, MedicalRecord medicalRecord, String name,
			Date born, Date died, Sex sex, Animal mother, Animal father) {
		super();
		this.passport = passport;
		this.medicalRecord = medicalRecord;
		this.name = name;
		this.born = born;
		this.died = died;
		this.sex = sex;
		this.mother = mother;
		this.father = father;
	}

	public EarTag getTag() {
		return tag;
	}

	public void setTag(EarTag tag) {
		this.tag = tag;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBorn() {
		return born;
	}

	public void setBorn(Date born) {
		this.born = born;
	}

	public Date getDied() {
		return died;
	}

	public void setDied(Date died) {
		this.died = died;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Animal getMother() {
		return mother;
	}

	public void setMother(Animal mother) {
		this.mother = mother;
	}

	public Animal getFather() {
		return father;
	}

	public void setFather(Animal father) {
		this.father = father;
	}

}
