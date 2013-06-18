package fit.piris.evz.pages.errorPages;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.services.Response;

import fit.piris.evz.annotations.VeterinarAccess;
import fit.piris.evz.annotations.VlasnikAccess;

@VlasnikAccess
@VeterinarAccess
@Import(stylesheet = "context:layout/canvasAdmin/stylesheets/all.css", library = ("context:layout/canvasAdmin/javascripts/all.js"))

public class Error_500 {

	@Persist
	@Property
	public static String PREVIOUS_PAGE;
	
	@Inject
	private Response response;
	
	public void onActionFromBack() {
		try {
			response.sendRedirect("/evz/"+PREVIOUS_PAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
