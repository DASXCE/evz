package fit.piris.evz.pages;

import java.util.Date;

import org.apache.tapestry5.annotations.Property;
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
public class Index {

	@Inject
	private Authenticator authenticator;

	@Inject
	private Session session;

	@Property
	private int brKorisnikaUSistemu = session.createCriteria(User.class).list().size();
	
	@Property
	private int brGazdinstavaUSistemu = session.createCriteria(Gazdinstvo.class).list().size();
	
	@Property
	private int brVeterinaraUSistemu = session.createCriteria(Veterinar.class).list().size();
	
	@Property
	private int brZivotinjaUSistemu = session.createCriteria(Zivotinja.class).list().size();
	
	@Property
	private Date danas = new Date();

	void onActivate() {
		danas = new Date();
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
}
