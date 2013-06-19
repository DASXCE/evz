package fit.piris.evz.pages.user;

import java.io.IOException;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;
import org.hibernate.Session;

import fit.piris.evz.annotations.VeterinarAccess;
import fit.piris.evz.annotations.VlasnikAccess;
import fit.piris.evz.entities.Adresa;
import fit.piris.evz.entities.Ambulanta;
import fit.piris.evz.entities.users.User;
import fit.piris.evz.entities.users.Veterinar;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.model.MD5;
import fit.piris.evz.model.selectBox.ambulanta.AmbulantaEncoder;
import fit.piris.evz.model.selectBox.ambulanta.AmbulantaSelectModel;
import fit.piris.evz.pages.errorPages.Error_401;
import fit.piris.evz.services.dao.user.UserDAO;
import fit.piris.evz.services.security.Authenticator;

@VlasnikAccess
@VeterinarAccess
@Import(library = { "context:layout/canvasAdmin/javascripts/all.js",
		"context:layout/canvasAdmin/javascripts/jquery.blockUI.js" })
public class ViewUser {

	@Property
	private String email;

	@Property
	private String password2;// password '2' zato sto uzimamo potvrdu passworda

	@Property
	private String oldpass;
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

	@Persist
	public static User user;

	@Property
	private String naslov;

	@Property
	private boolean isCurrentUserVlasnik;

	@Property
	private boolean isCurrentUserVeterinar;
	
	@Property
	private boolean isCurrentUserAdmin;

	@Inject
	private Authenticator authenticator;

	@Inject
	private Session session;

	/*
	 * ambulanta
	 */
	@Property
	private Ambulanta ambulanta;

	@Property
	private String amb;

	@Inject
	private UserDAO userDAO;

	@Property
	@Persist(PersistenceConstants.FLASH)
	private boolean writePermission;

	@Inject
	private ComponentResources resources;

	@Inject
	private Response response;
	
	@Property
	@Persist(PersistenceConstants.FLASH)
	private int showsuccess;

	public void onActivate() {
		
		if (user == null) {
			user = authenticator.getLoggedUser();
		}
		if (user instanceof Vlasnik) {
			isCurrentUserAdmin = false;
			isCurrentUserVeterinar = false;
			isCurrentUserVlasnik = true;
			naslov = ((Vlasnik) user).getIme() + " "
					+ ((Vlasnik) user).getPrezime();
			email = user.getEmail();
			jmbg = ((Vlasnik) user).getJmbg();
			ime = ((Vlasnik) user).getIme();
			prezime = ((Vlasnik) user).getPrezime();
			telefon = ((Vlasnik) user).getTelefon();
			grad = ((Vlasnik) user).getAdresa().getGrad();
			posta = ((Vlasnik) user).getAdresa().getBrPoste();
			ulica = ((Vlasnik) user).getAdresa().getUlica();
			return;
		}
		if (user instanceof Veterinar) {
			isCurrentUserAdmin = false;
			isCurrentUserVlasnik = false;
			isCurrentUserVeterinar = true;
			naslov = "Veterinar: " + ((Veterinar) user).getIme() + " "
					+ ((Veterinar) user).getPrezime();
			email = user.getEmail();
			vet_ime = ((Veterinar) user).getIme();
			vet_prezime = ((Veterinar) user).getPrezime();
//				amb = ((Veterinar) user).getAmbulanta().getNaziv();
		} else {
			isCurrentUserAdmin = true;
			email = user.getEmail();
		}
	}

	public void onActionFromEdit() {
		writePermission = true;
	}

	public void onActionFromStop() {
		writePermission = false;
	}

	@SuppressWarnings("unchecked")
	public SelectModel getAmbulantaModel() {
		return new AmbulantaSelectModel(session.createCriteria(Ambulanta.class)
				.list());
	}

	public AmbulantaEncoder getAmbulantaEncoder() {
		return new AmbulantaEncoder(session);
	}

	public boolean hasAccess() {
		if (user.getId() == authenticator.getLoggedUser().getId()
				|| !(authenticator.getLoggedUser() instanceof Vlasnik || authenticator
						.getLoggedUser() instanceof Veterinar)) {
			return true;
		}
		return false;
	}

	@CommitAfter
	public Object onSubmitFromForma() throws IOException {
		if (password2!=null) {
			if (oldpass==null || !MD5.md5(oldpass).equals(user.getPassword())) {
				Error_401.PREVIOUS_PAGE = resources.getPageName();
				response.sendRedirect("/evz/errorPages/Error_401");
				showsuccess = 3;
				return null;
			}
		}
		if (user instanceof Vlasnik) {
			Vlasnik updated = (Vlasnik) user;
			if (password2!=null) {
				updated.setPassword(MD5.md5(password2));
			}else {
				updated.setPassword(user.getPassword());
			}
			
			updated.setEmail(email);
			updated.setJmbg(jmbg);
			updated.setIme(ime);
			updated.setPrezime(prezime);
			updated.setTelefon(telefon);
			
			Adresa stara = ((Vlasnik) user).getAdresa();
			
			Adresa nova = new Adresa();
			nova.setGrad(grad);
			nova.setBrPoste(posta);
			nova.setUlica(ulica);
			
			if (!nova.equals(stara)) {
				session.save(nova);
				updated.setAdresa(nova);
			}
			
			userDAO.update(updated);
			showsuccess = 1;
			return null;
		}
		if (user instanceof Veterinar) {
			Veterinar updated = (Veterinar) user;
			if (password2!=null) {
				updated.setPassword(MD5.md5(password2));
			}else {
				updated.setPassword(user.getPassword());
			}
			updated.setIme(vet_ime);
			updated.setPrezime(vet_prezime);
			updated.setAmbulanta(ambulanta);
			userDAO.update(updated);
			showsuccess = 1;
		} else {
			User updated = user;
			if (password2!=null) {
				updated.setPassword(MD5.md5(password2));
			}else {
				updated.setPassword(user.getPassword());
			}
			updated.setEmail(email);
			userDAO.update(updated);
			showsuccess = 1;
		}
		writePermission = false;
		return null;
	}

	public boolean isAdminLoggedIn() {
		if (authenticator.getLoggedUser() instanceof Vlasnik
				|| authenticator.getLoggedUser() instanceof Veterinar) {
			return false;
		}
		return true;
	}
	
	public boolean isSuccessfull() {
		if (showsuccess == 1) {
			return true;
		}
		return false;
	}
	
	public boolean isError() {
		if (showsuccess == 3) {
			return true;
		}
		return false;
	}
}
