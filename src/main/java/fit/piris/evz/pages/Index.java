package fit.piris.evz.pages;

import java.util.Date;
import java.util.List;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import fit.piris.evz.annotations.VeterinarAccess;
import fit.piris.evz.annotations.VlasnikAccess;
import fit.piris.evz.entities.gazdinstvo.Gazdinstvo;
import fit.piris.evz.entities.users.User;
import fit.piris.evz.entities.users.Veterinar;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.entities.zivotinje.Zivotinja;
import fit.piris.evz.services.security.Authenticator;

/**
 * Start page of application evz.
 */
@VeterinarAccess
@VlasnikAccess
@Import(stylesheet = { "context:layout/canvasAdmin/stylesheets/sample_pages/invoice.css" })
public class Index {

	@Inject
	private Authenticator authenticator;

	@Inject
	private Session session;

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
	
	@Persist(PersistenceConstants.FLASH)
	public static boolean newEntity;

	void onActivate() {
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
	}

	public boolean isAdmin() {
		if (!(authenticator.getLoggedUser() instanceof Veterinar)
				&& !(authenticator.getLoggedUser() instanceof Vlasnik)) {
			return true;
		}
		return false;
	}

	public boolean isVlasnik() {
		if (authenticator.getLoggedUser() instanceof Vlasnik) {
			return true;
		}
		return false;
	}

	public boolean isVeterinar() {
		if (authenticator.getLoggedUser() instanceof Veterinar) {
			return true;
		}
		return false;
	}

	@Property
	private Vlasnik vlasnikTmp;

	@Property
	private List<Vlasnik> vlasnici = sviVlasnici();

	@SuppressWarnings("unchecked")
	public List<Vlasnik> sviVlasnici() {
		return session.createCriteria(Vlasnik.class).list();
	}

	@InjectComponent
	private Zone statZone;

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
}
