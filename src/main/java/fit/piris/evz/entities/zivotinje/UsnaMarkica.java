package fit.piris.evz.entities.zivotinje;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UsnaMarkica implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="pk_zivotinja_broj_markice")
	public Long broj;

	@Column(name="pk_zivotinja_drzava_markice")
	public String drzava;

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

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UsnaMarkica that = (UsnaMarkica) o;

		if (that.getBroj() != this.broj || that.getDrzava() != this.drzava) {
			return false;
		}

		return true;
	}
}
