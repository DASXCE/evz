package fit.piris.evz.pages.errorPages;

import org.apache.tapestry5.annotations.Import;

import fit.piris.evz.annotations.GuestAccess;
import fit.piris.evz.annotations.VeterinarAccess;
import fit.piris.evz.annotations.VlasnikAccess;
@GuestAccess
@VlasnikAccess
@VeterinarAccess
@Import(stylesheet = "context:layout/canvasAdmin/stylesheets/all.css", library = ("context:layout/canvasAdmin/javascripts/all.js"))
public class Error_401 {

}
