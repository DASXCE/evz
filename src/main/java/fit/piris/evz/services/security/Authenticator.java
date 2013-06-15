package fit.piris.evz.services.security;

import fit.piris.evz.entities.users.User;

public interface Authenticator {

	public User getLoggedUser();

	public boolean LoggedIn();

	public void login(String username, String password)
			throws AuthenticationException;

	public void logout();
}
