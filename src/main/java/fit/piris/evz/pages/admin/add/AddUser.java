package fit.piris.evz.pages.admin.add;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import fit.piris.evz.annotations.GuestAccess;
import fit.piris.evz.entities.Adresa;
import fit.piris.evz.entities.Ambulanta;
import fit.piris.evz.model.selectBox.ambulanta.AmbulantaEncoder;
import fit.piris.evz.model.selectBox.ambulanta.AmbulantaSelectModel;
import fit.piris.evz.services.dao.user.UserDAO;
@Import(stylesheet = "context:layout/canvasAdmin/stylesheets/all.css", library = { "context:layout/canvasAdmin/javascripts/all.js" })
@GuestAccess
public class AddUser {

	@Property
	private String privilegija;

	@Property
	private String email;

	@Property
	private String password2;

	@Property
	private String ime;

	@Property
	private String prezime;

	@Property
	private String vet_ime;

	@Property
	private String vet_prezime;

	@Property
	private String grad;

	@Property
	private Integer posta;

	@Property
	private String ulica;

	@Property
	private Long jmbg;

	@Property
	private String telefon;

	@Inject
	private UserDAO userDAO;

	@Inject
	private Session session;

	@Property
	private Ambulanta ambulanta;

	@Property
	@Persist(PersistenceConstants.FLASH)
	private boolean showsuccess;

	@CommitAfter
	public Object onSubmitFromForma() {
		if (privilegija.toLowerCase().equals("admin")) {
			userDAO.registerAdmin(email, password2);
		} else if (privilegija.toLowerCase().equals("vlasnik")) {
			userDAO.registerVlasnik(email, password2, jmbg, ime, prezime,
					new Adresa(grad, posta, ulica), telefon);
		} else if (privilegija.toLowerCase().equals("veterinar")) {
			userDAO.registerVeterinar(email, password2, vet_ime, vet_prezime,
					ambulanta);
		}
		showsuccess = true;
		// return AddGazdinstvo.class;
		return null;
	}

	@SuppressWarnings("unchecked")
	public SelectModel getAmbulantaModel() {
		return new AmbulantaSelectModel(session.createCriteria(Ambulanta.class)
				.list());

	}

	public AmbulantaEncoder getAmbulantaEncoder() {
		return new AmbulantaEncoder(session);
	}
}
