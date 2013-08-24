package fit.piris.evz.pages.user;

import java.io.IOException;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;
import org.hibernate.Session;

import fit.piris.evz.annotations.VetAccess;
import fit.piris.evz.annotations.OwnerAccess;
import fit.piris.evz.entities.Address;
import fit.piris.evz.entities.Infirmary;
import fit.piris.evz.entities.users.User;
import fit.piris.evz.entities.users.Vet;
import fit.piris.evz.entities.users.Owner;
import fit.piris.evz.model.MD5;
import fit.piris.evz.model.selectBox.infirmary.InfirmaryEncoder;
import fit.piris.evz.model.selectBox.infirmary.InfirmarySelectModel;
import fit.piris.evz.pages.errorPages.Error_401;
import fit.piris.evz.services.dao.user.UserDAO;
import fit.piris.evz.services.security.Authenticator;

@OwnerAccess
@VetAccess
@Import(library = { "context:layout/canvasAdmin/javascripts/all.js",
		"context:layout/canvasAdmin/javascripts/jquery.blockUI.js" })
public class ViewUser {

	@Property
	private String email;

	@Property
	private String password2;

	@Property
	private String oldpass;
	/*
	 * owner data
	 */
	@Property
	private String firstName;

	@Property
	private String lastName;

	@Property
	private Long personalId;

	@Property
	private String phone;

	/*
	 * vet data
	 */
	@Property
	private String vetFirstName;

	@Property
	private String vetLastName;

	/*
	 * address
	 */
	@Property
	private String town;

	@Property
	private Integer postal;

	@Property
	private String street;

	@Persist
	public static User user;

	@Property
	private String title;

	@Property
	private boolean isCurrentUserOwner;

	@Property
	private boolean isCurrentUserVet;
	
	@Property
	private boolean isCurrentUserAdmin;

	@Inject
	private Authenticator authenticator;

	@Inject
	private Session session;

	/*
	 * ambulanta
	 */
	@Property
	private Infirmary infirmary;

	@Property
	private String inf;

	@Inject
	private UserDAO userDAO;

	@Property
	@Persist(PersistenceConstants.FLASH)
	private boolean writePermission;

	@Inject
	private ComponentResources resources;

	@Inject
	private Response response;
	
	@Property
	@Persist(PersistenceConstants.FLASH)
	private int showsuccess;

	public void onActivate() {
		
		if (user == null) {
			user = authenticator.getLoggedUser();
		}
		if (user instanceof Owner) {
			isCurrentUserAdmin = false;
			isCurrentUserVet = false;
			isCurrentUserOwner = true;
			title = ((Owner) user).getFirstName() + " "
					+ ((Owner) user).getLastName();
			email = user.getEmail();
			personalId = ((Owner) user).getPersonalId();
			firstName = ((Owner) user).getFirstName();
			lastName = ((Owner) user).getLastName();
			phone = ((Owner) user).getPhone();
			town = ((Owner) user).getAddress().getTown();
			postal = ((Owner) user).getAddress().getPostal();
			street = ((Owner) user).getAddress().getStreet();
			return;
		}
		if (user instanceof Vet) {
			isCurrentUserAdmin = false;
			isCurrentUserOwner = false;
			isCurrentUserVet = true;
			title = "Veterinar: " + ((Vet) user).getFirstName() + " "
					+ ((Vet) user).getLastName();
			email = user.getEmail();
			vetFirstName = ((Vet) user).getFirstName();
			vetLastName = ((Vet) user).getLastName();
//				amb = ((Veterinar) user).getAmbulanta().getNaziv();
		} else {
			isCurrentUserAdmin = true;
			email = user.getEmail();
		}
	}

	public void onActionFromEdit() {
		writePermission = true;
	}

	public void onActionFromStop() {
		writePermission = false;
	}

	@SuppressWarnings("unchecked")
	public SelectModel getInfirmaryModel() {
		return new InfirmarySelectModel(session.createCriteria(Infirmary.class)
				.list());
	}

	public InfirmaryEncoder getInfirmaryEncoder() {
		return new InfirmaryEncoder(session);
	}

	public boolean hasAccess() {
		if (user.getId() == authenticator.getLoggedUser().getId()
				|| !(authenticator.getLoggedUser() instanceof Owner || authenticator
						.getLoggedUser() instanceof Vet)) {
			return true;
		}
		return false;
	}

	@CommitAfter
	public Object onSubmitFromForm() throws IOException {
		if (password2!=null) {
			if (oldpass==null || !MD5.md5(oldpass).equals(user.getPassword())) {
				Error_401.PREVIOUS_PAGE = resources.getPageName();
				response.sendRedirect("/evz/errorPages/Error_401");
				showsuccess = 3;
				return null;
			}
		}
		if (user instanceof Owner) {
			Owner updated = (Owner) user;
			if (password2!=null) {
				updated.setPassword(MD5.md5(password2));
			}else {
				updated.setPassword(user.getPassword());
			}
			
			updated.setEmail(email);
			updated.setPersonalId(personalId);
			updated.setFirstName(firstName);
			updated.setLastName(lastName);
			updated.setPhone(phone);
			
			Address old = ((Owner) user).getAddress();
			
			Address newAddr = new Address();
			newAddr.setTown(town);;
			newAddr.setPostal(postal);
			newAddr.setStreet(street);
			
			if (!newAddr.equals(old)) {
				session.save(newAddr);
				updated.setAddress(newAddr);
			}
			
			userDAO.update(updated);
			showsuccess = 1;
			return null;
		}
		if (user instanceof Vet) {
			Vet updated = (Vet) user;
			if (password2!=null) {
				updated.setPassword(MD5.md5(password2));
			}else {
				updated.setPassword(user.getPassword());
			}
			updated.setFirstName(vetFirstName);
			updated.setLastName(vetLastName);
			updated.setInfirmary(infirmary);
			userDAO.update(updated);
			showsuccess = 1;
		} else {
			User updated = user;
			if (password2!=null) {
				updated.setPassword(MD5.md5(password2));
			}else {
				updated.setPassword(user.getPassword());
			}
			updated.setEmail(email);
			userDAO.update(updated);
			showsuccess = 1;
		}
		writePermission = false;
		return null;
	}

	public boolean isAdminLoggedIn() {
		if (authenticator.getLoggedUser() instanceof Owner
				|| authenticator.getLoggedUser() instanceof Vet) {
			return false;
		}
		return true;
	}
	
	public boolean isSuccessfull() {
		if (showsuccess == 1) {
			return true;
		}
		return false;
	}
	
	public boolean isError() {
		if (showsuccess == 3) {
			return true;
		}
		return false;
	}
}
