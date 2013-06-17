package fit.piris.evz.pages.admin.add;

import java.io.IOException;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;
import org.hibernate.Session;

import fit.piris.evz.entities.Adresa;
import fit.piris.evz.entities.Ambulanta;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.model.selectBox.ambulanta.AmbulantaEncoder;
import fit.piris.evz.model.selectBox.ambulanta.AmbulantaSelectModel;
import fit.piris.evz.pages.Index;
import fit.piris.evz.pages.errorPages.Error_500;
import fit.piris.evz.services.dao.user.UserDAO;

@Import(library = { "context:layout/canvasAdmin/javascripts/all.js",
		"context:layout/canvasAdmin/javascripts/jquery.blockUI.js" })
public class AddUser {

	/*
	 * tip korisnika
	 */
	@Property
	private String privilegija;

	/*
	 * zajednicko za sve korisnike ili samo ako je admin
	 */
	@Property
	private String email;

	@Property
	private String password2;// password '2' zato sto uzimamo potvrdu passworda

	/*
	 * podaci o vlasniku
	 */
	@Property
	private String ime;

	@Property
	private String prezime;

	@Property
	private Long jmbg;

	@Property
	private String telefon;

	/*
	 * podaci o veterinaru
	 */
	@Property
	private String vet_ime;

	@Property
	private String vet_prezime;

	/*
	 * adresa za vlasnika ili za ambulantu
	 */
	@Property
	private String grad;

	@Property
	private Integer posta;

	@Property
	private String ulica;

	@Inject
	private UserDAO userDAO;

	@Inject
	private Session session;

	/*
	 * ambulanta
	 */
	@Property
	private Ambulanta ambulanta;

	@Property
	private String ambGrad;

	@Property
	private Integer ambPosta;

	@Property
	private String ambUlica;

	@Property
	private String amb_naziv;

	/*
	 * 
	 */

	@Property
	@Persist(PersistenceConstants.FLASH)
	private int showsuccess;

	@InjectComponent
	private Zone ambZona;

	@Inject
	private Response response;

	@Inject
	private ComponentResources resources;

	@CommitAfter
	public void onSubmitFromForma() throws IOException {
		try {
			if (privilegija.toLowerCase().equals("admin")) {
				userDAO.registerAdmin(email, password2);
			} else if (privilegija.toLowerCase().equals("vlasnik")) {
				userDAO.registerVlasnik(email, password2, jmbg, ime, prezime,
						new Adresa(grad, posta, ulica), telefon);

				// set flag for success message and button link to +Gazdinstvo
				showsuccess = 2;
				return;
			} else if (privilegija.toLowerCase().equals("veterinar")) {
				userDAO.registerVeterinar(email, password2, vet_ime,
						vet_prezime, ambulanta);
			}
			// set flag for stats refresh
			Index.newEntity = true;
			// set flag for success message
			showsuccess = 1;
		} catch (Exception e) {
			// mora ovako, ne moze sa return Error_500.class jer ga prestigne
			// drugi exception
			Error_500.previousPage = resources.getPageName();
			response.sendRedirect("/evz/errorPages/Error_500");
		}
	}

	@CommitAfter
	public Object onSubmitFromAmbForma() {
		Adresa adresa = new Adresa(ambGrad, ambPosta, ambUlica);

		@SuppressWarnings("unchecked")
		List<Adresa> adrese = session.createCriteria(Adresa.class).list();
		for (Adresa adr : adrese) {
			if (adr.equals(adresa)) {
				ambulanta = new Ambulanta(amb_naziv, adr);
				session.save(ambulanta);
				return null;
			}
		}
		session.save(adresa);

		ambulanta = new Ambulanta(amb_naziv, adresa);
		session.save(ambulanta);
		return ambZona.getBody();
	}

	@InjectPage
	private AddGazdinstvo gazdinstvo;

	public Object onActionFromNovoGazdinstvo() {
		@SuppressWarnings("unchecked")
		List<Vlasnik> list = session.createCriteria(Vlasnik.class).list();
		gazdinstvo.setVlasnik(list.get(list.size() - 1));
		return gazdinstvo;
	}

	@SuppressWarnings("unchecked")
	public SelectModel getAmbulantaModel() {
		return new AmbulantaSelectModel(session.createCriteria(Ambulanta.class)
				.list());

	}

	public AmbulantaEncoder getAmbulantaEncoder() {
		return new AmbulantaEncoder(session);
	}

	public boolean isSuccessfull() {
		if (showsuccess == 1) {
			return true;
		}
		return false;
	}

	public boolean isSuccessfullFromVlasnik() {
		if (showsuccess == 2) {
			return true;
		}
		return false;
	}
}
