package fit.piris.evz.pages;

import java.io.IOException;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;

import fit.piris.evz.annotations.GuestAccess;
import fit.piris.evz.services.security.AuthenticationException;
import fit.piris.evz.services.security.Authenticator;

@GuestAccess
@Import(stylesheet = { "context:layout/canvasAdmin/stylesheets/reset.css",
		"context:layout/canvasAdmin/stylesheets/text.css",
		"context:layout/canvasAdmin/stylesheets/buttons.css",
		"context:layout/canvasAdmin/stylesheets/theme-default.css",
		"context:layout/canvasAdmin/stylesheets/login.css" }, library = ("context:layout/canvasAdmin/javascripts/all.js"))
public class Login {

	@Property
	private String email;

	@Property
	private String password;

	@Inject
	private Authenticator authenticator;

	@Component
	private Form loginForm;

	@Inject
	private Messages messages;

	@Inject
	private Response response;

	@Inject
	private Request request;


	public void onSubmitFromLoginForm() throws IOException {

		try {
			authenticator.login(email, password);
		} catch (AuthenticationException ex) {
			loginForm.recordError(messages.get("error.login"));
			return;
		} catch (Exception e) {
			response.sendRedirect("errorPages/Error_401");
			return;
		}

		response.sendRedirect("index");
	}
}
