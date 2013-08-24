package fit.piris.evz.entities.animal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EarTag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "pk_animal_tag_number")
	public Long number;

	@Column(name = "pk_animal_tag_country")
	public String country;

	public EarTag() {
	}

	public EarTag(Long number, String country) {
		super();
		this.number = number;
		this.country = country;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		EarTag that = (EarTag) o;

		if (that.getNumber() != this.number || that.getCountry() != this.country) {
			return false;
		}

		return true;
	}
}
