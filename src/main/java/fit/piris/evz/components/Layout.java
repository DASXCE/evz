package fit.piris.evz.components;

import java.util.Date;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;
import org.hibernate.Session;

import fit.piris.evz.entities.users.User;
import fit.piris.evz.entities.users.Veterinar;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.pages.Login;
import fit.piris.evz.pages.user.ViewUser;
import fit.piris.evz.services.security.Authenticator;


/**
 * Layout component for pages of application evz.
 */
@Import(stylesheet = "context:layout/canvasAdmin/stylesheets/all.css", library = ("context:layout/canvasAdmin/javascripts/all.js"))
public class Layout {
	/**
	 * The page title, for the <title> element and the <h1>element.
	 */
	@Property
	@Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
	private String title;
	
	@Property
	private String a="active";

	@Property
	private Integer activeElement = 0;

	@Property
	private Date sysDate;

	@Property
	private String pageName;

	@Property
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String sidebarTitle;

	@Property
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private Block sidebar;
	
	@Inject
	private Session session;

	@Inject
	private ComponentResources resources;
	
	@Property
	@Inject
	@Symbol(SymbolConstants.APPLICATION_VERSION)
	private String appVersion;
	
	@Inject
	private Authenticator authenticator;

	public String getClassForPageName() {
		return resources.getPageName().equalsIgnoreCase(pageName) ? "current_page_item"
				: null;
	}

	public String[] getPageNames() {
		return new String[] { "Index", "About", "Contact" };
	}
	
	public String getLoggedUser(){
//		if (authenticator.getLoggedUser().getEmail()!=null) {
//			return authenticator.getLoggedUser().getEmail();
//		}
		return authenticator.getLoggedUser().getEmail();
	}
	
	public int getNumberOfUsers() {
		return session.createCriteria(User.class).list().size();
	}
	
	public boolean ifAdmin() {
		if (authenticator.getLoggedUser()instanceof Vlasnik || authenticator.getLoggedUser()instanceof Veterinar) {
			return false;
		}
		return true;
	}
	
	public Object onActionFromLogout(){
		authenticator.logout();
		return Login.class;
	}
	
	public Object onActionFromView() {
		ViewUser.user=null;
		return ViewUser.class;
	}
}
