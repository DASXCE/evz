package fit.piris.evz.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
@Table(name = "adrese")
public class Adresa {

	@Id
	@NonVisual
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "grad", nullable = false)
	private String grad;

	@Column(name = "brPoste", nullable = false)
	private Integer brPoste;

	@Column(name = "ulica", nullable = false)
	private String ulica;

	public Adresa() {
	}

	public Adresa(String grad, Integer brPoste, String ulica) {
		super();
		this.grad = grad;
		this.brPoste = brPoste;
		this.ulica = ulica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public Integer getBrPoste() {
		return brPoste;
	}

	public void setBrPoste(Integer brPoste) {
		this.brPoste = brPoste;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public boolean equals(Adresa adresa) {
		if (this.grad.equalsIgnoreCase(adresa.getGrad())
				&& this.ulica.equalsIgnoreCase(adresa.getUlica())
				&& this.brPoste.equals(adresa.getBrPoste())) {
			return true;
		}
		return false;
	}
}
