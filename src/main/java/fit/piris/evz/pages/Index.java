package fit.piris.evz.pages;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;
import org.hibernate.Session;

import fit.piris.evz.annotations.VeterinarAccess;
import fit.piris.evz.annotations.VlasnikAccess;
import fit.piris.evz.entities.gazdinstvo.Gazdinstvo;
import fit.piris.evz.entities.users.User;
import fit.piris.evz.entities.users.Veterinar;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.entities.zivotinje.Zivotinja;
import fit.piris.evz.pages.user.ViewUser;
import fit.piris.evz.services.dao.user.UserDAO;
import fit.piris.evz.services.security.Authenticator;

/**
 * Start page of application evz.
 */
@VeterinarAccess
@VlasnikAccess
@Import(stylesheet = { "context:layout/canvasAdmin/stylesheets/sample_pages/invoice.css" })
public class Index {

	/*
	 * Tapestry Services
	 */
	@Inject
	private Authenticator authenticator;
	
	/*
	 * Hibernate Services
	 */
	@Inject
	private Session session;
	
	/*
	 * My Services
	 */
	@Inject
	private UserDAO userDAO;

	/*
	 * kocke za statistiku sa brojevima
	 */
	@Persist
	@Property
	private int brKorisnikaUSistemu;

	@Persist
	@Property
	private int brGazdinstavaUSistemu;

	@Persist
	@Property
	private int brVeterinaraUSistemu;

	@Persist
	@Property
	private int brZivotinjaUSistemu;

	@Property
	private Date danas = new Date();
	
	@Property
	private Vlasnik vlasnikTmp;

	/*
	 * lista svih vlasnika iz baze
	 */
	@Property
	private List<Vlasnik> vlasnici;

	@Component
	private Zone statZone;
	
	/*
	 * refresuj samo ako se dodalo nesto novo u bazi...bilo sta...mora se setovati posle unosa!
	 */
	@Persist(PersistenceConstants.FLASH)
	public static boolean newEntity;

	public void onActivate() {
		danas = new Date();
		if (newEntity) {
			brKorisnikaUSistemu = session.createCriteria(User.class).list().size();

			brGazdinstavaUSistemu = session.createCriteria(Gazdinstvo.class).list()
					.size();

			brVeterinaraUSistemu = session.createCriteria(Veterinar.class).list()
					.size();

			brZivotinjaUSistemu = session.createCriteria(Zivotinja.class).list()
					.size();
			
			
		}
//		vlasnici = sviVlasnici();
	}

	/*
	 * ako je administrator, koristim za prikaz komponenti koje zahtjevaju ovu privilegiju
	 */
	public boolean isAdmin() {
		if (!(authenticator.getLoggedUser() instanceof Veterinar)
				&& !(authenticator.getLoggedUser() instanceof Vlasnik)) {
			return true;
		}
		return false;
	}

	/*
	 * privremena promjenljiva kojom se krecem kroz petlju - za tabelu vlasnika
	 */
	
	/*
	 * metoda koja puni listu vlasnici
	 */
	@SuppressWarnings("unchecked")
	public List<Vlasnik> getVlasnicii() {
		return session.createCriteria(Vlasnik.class).list();
	}

	public Object onActionFromRefresh() {

		brKorisnikaUSistemu = session.createCriteria(User.class).list().size();

		brGazdinstavaUSistemu = session.createCriteria(Gazdinstvo.class).list()
				.size();

		brVeterinaraUSistemu = session.createCriteria(Veterinar.class).list()
				.size();

		brZivotinjaUSistemu = session.createCriteria(Zivotinja.class).list()
				.size();
		
		return statZone.getBody();
	}

	public boolean akoNijeNull(Gazdinstvo gazdinstvo) {
		if (gazdinstvo != null) {
			return true;
		}
		return false;
	}
	
	@CommitAfter
	public Object onActionFromDeleteVlasnik(Vlasnik vlasnik) {
		userDAO.delete(vlasnik);
		// return null da bi uradio refresh strane, sa void to nebi
		return null;
	}
	
	public Object onActionFromEditVlasnik(Vlasnik vlasnik) {
		ViewUser.user = vlasnik;
		return ViewUser.class;
	}
}
