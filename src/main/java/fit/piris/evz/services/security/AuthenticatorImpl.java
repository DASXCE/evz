package fit.piris.evz.services.security;

import java.io.IOException;

import org.apache.tapestry5.annotations.BeforeRenderBody;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.services.Session;

import fit.piris.evz.entities.users.User;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.model.MD5;
import fit.piris.evz.pages.Index;
import fit.piris.evz.pages.admin.add.AddUser;
import fit.piris.evz.services.dao.user.UserDAO;

public class AuthenticatorImpl implements Authenticator {

	public static final String AUTH_TOKEN = "authToken";

	@Inject
	private UserDAO userDAO;

	@Inject
	private Request request;

	@Inject
	private Response response;
	
	public void login(String email, String password)
			throws AuthenticationException {
		
		User user = userDAO.find(email, MD5.md5(password));

		if (user == null) {
			try {
				response.sendRedirect("errorPages/Error_401");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		request.getSession(true).setAttribute(AUTH_TOKEN, user);
	}

	public boolean LoggedIn() {
		Session session = request.getSession(false);
		if (session != null) {
			return session.getAttribute(AUTH_TOKEN) != null;
		}
		return false;
	}

	public void logout() {
		Session session = request.getSession(false);
		if (session != null) {
			session.setAttribute(AUTH_TOKEN, null);
			session.invalidate();
		}
	}

	public User getLoggedUser() {
		User user = null;

		if (LoggedIn()) {
			user = (User) request.getSession(true).getAttribute(AUTH_TOKEN);
		} else {
			throw new IllegalStateException("NO USER IS LOGGED IN!");
		}
		return user;
	}

}
