package fit.piris.evz.entities.zivotinje;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
@Table(name = "usne_markice")
public class UsnaMarkica {

	@Id
	@NonVisual
	@Column(name = "broj")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long broj;

	@Column(name = "drzava")
	private String drzava;

	public UsnaMarkica() {
	}

	public UsnaMarkica(Long broj, String drzava) {
		super();
		this.broj = broj;
		this.drzava = drzava;
	}

	public Long getBroj() {
		return broj;
	}

	public void setBroj(Long broj) {
		this.broj = broj;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
}
