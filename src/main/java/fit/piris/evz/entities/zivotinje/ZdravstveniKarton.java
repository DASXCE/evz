package fit.piris.evz.entities.zivotinje;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
@Table(name = "zdravstveni_karton")
public class ZdravstveniKarton {

	@Id
	@NonVisual
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany
	private List<Dijagnoza> dijagnoze;

	public ZdravstveniKarton() {
	}

	public ZdravstveniKarton(List<Dijagnoza> dijagnoze) {
		super();
		this.dijagnoze = dijagnoze;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Dijagnoza> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(List<Dijagnoza> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

}
