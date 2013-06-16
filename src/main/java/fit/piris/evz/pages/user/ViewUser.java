package fit.piris.evz.pages.user;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import fit.piris.evz.annotations.VeterinarAccess;
import fit.piris.evz.annotations.VlasnikAccess;
import fit.piris.evz.entities.users.User;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.services.security.Authenticator;

@VlasnikAccess
@VeterinarAccess
public class ViewUser {

	@Persist
	private User user;
	
	@Property
	private String naslov;

	@Inject
	private Authenticator authenticator;

	public void onActivate() {
		if (user == null) {
			user = authenticator.getLoggedUser();
			
		}
		if (user instanceof Vlasnik) {
			naslov = ((Vlasnik) user).getIme()+" "+((Vlasnik) user).getPrezime();
		}
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
