package fit.piris.evz.services.security;

import java.io.IOException;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ComponentEventRequestParameters;
import org.apache.tapestry5.services.ComponentRequestFilter;
import org.apache.tapestry5.services.ComponentRequestHandler;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Response;

import fit.piris.evz.annotations.GuestAccess;
import fit.piris.evz.annotations.VeterinarAccess;
import fit.piris.evz.annotations.VlasnikAccess;
import fit.piris.evz.entities.users.User;
import fit.piris.evz.entities.users.Veterinar;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.pages.Login;

public class AuthenticationFilter implements ComponentRequestFilter {

	private final PageRenderLinkSource renderLinkSource;

	private final ComponentSource componentSource;

	private final Response response;

	private final Authenticator authenticator;

	private String loginPage = Login.class.getSimpleName();

	public AuthenticationFilter(PageRenderLinkSource renderLinkSource,
			ComponentSource componentSource, Response response,
			Authenticator authenticator) {
		this.renderLinkSource = renderLinkSource;
		this.componentSource = componentSource;
		this.response = response;
		this.authenticator = authenticator;
	}

	public void handleComponentEvent(
			ComponentEventRequestParameters parameters,
			ComponentRequestHandler handler) throws IOException {

		if (dispatchedToLoginPage(parameters.getActivePageName())) {
			return;
		}

		handler.handleComponentEvent(parameters);

	}

	public void handlePageRender(PageRenderRequestParameters parameters,
			ComponentRequestHandler handler) throws IOException {

		if (dispatchedToLoginPage(parameters.getLogicalPageName())) {
			return;
		}

		handler.handlePageRender(parameters);
	}

	private boolean dispatchedToLoginPage(String pageName) throws IOException {
		Component page = componentSource.getPage(pageName);
		Link linkToIndex = renderLinkSource.createPageRenderLink("Index");
		Link linkToError = renderLinkSource.createPageRenderLink("errorPages/Error_403");
		if (authenticator.LoggedIn()) {
			// Logged user should not go back to Login instead to index
			if (loginPage.equalsIgnoreCase(pageName)) {
				response.sendRedirect(linkToIndex);
				return true;
			}

			// if Vlasnik i logged in and if has privileges to 'page', dont
			// redirect!
			if (authenticator.getLoggedUser() instanceof Vlasnik) {
				if (page.getClass().isAnnotationPresent(VlasnikAccess.class)) {
					return false;
				} else {
					response.sendRedirect(linkToError);
						return true;
				}
			}

			if (authenticator.getLoggedUser() instanceof Veterinar) {
				if (page.getClass().isAnnotationPresent(VeterinarAccess.class)) {
					return false;
				} else {
					response.sendRedirect(linkToError);
					return true;
				}
			}

			// if page is not login then dont redirect ADMIN
			if (authenticator.getLoggedUser() instanceof User) {
				return false;
			}

			return false;

		}
		// if no user logged in and if GuestAccess present ALLOW
		if (page.getClass().isAnnotationPresent(GuestAccess.class)) {
			return false;
		}

		// if no user logged in and no annotation present, dont allow, go to
		// login
		Link login = renderLinkSource.createPageRenderLink("Login");
		response.sendRedirect(login);
		return true;

	}
}
