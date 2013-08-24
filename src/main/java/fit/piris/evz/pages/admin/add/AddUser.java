package fit.piris.evz.pages.admin.add;

import java.io.IOException;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;
import org.hibernate.Session;

import fit.piris.evz.entities.Address;
import fit.piris.evz.entities.Infirmary;
import fit.piris.evz.entities.users.Owner;
import fit.piris.evz.model.selectBox.infirmary.InfirmaryEncoder;
import fit.piris.evz.model.selectBox.infirmary.InfirmarySelectModel;
import fit.piris.evz.pages.Index;
import fit.piris.evz.pages.errorPages.Error_500;
import fit.piris.evz.services.dao.user.UserDAO;

@Import(library = { "context:layout/canvasAdmin/javascripts/all.js",
		"context:layout/canvasAdmin/javascripts/jquery.blockUI.js" })
public class AddUser {

	/*
	 * user type
	 */
	@Property
	private String privilege;

	/*
	 * common fields
	 */
	@Property
	private String email;

	@Property
	private String password2;// the confirmed password

	/*
	 * owner info
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
	 * vet info
	 */
	@Property
	private String vetFirstname;

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

	@Inject
	private UserDAO userDAO;

	@Inject
	private Session session;

	/*
	 * infirmary
	 */
	@Property
	private Infirmary infirmary;

	@Property
	private String infTown;

	@Property
	private Integer infPostal;

	@Property
	private String infStreet;

	@Property
	private String infName;

	/*
	 * 
	 */

	@Property
	@Persist(PersistenceConstants.FLASH)
	private int showsuccess;

	@InjectComponent
	private Zone infZone;

	@Inject
	private Response response;

	@Inject
	private ComponentResources resources;

	@CommitAfter
	public void onSubmitFromForm() throws IOException {
		try {
			if (privilege.toLowerCase().equals("admin")) {
				userDAO.registerAdmin(email, password2);
			} else if (privilege.toLowerCase().equals("owner")) {
				userDAO.registerOwner(email, password2, personalId, firstName, lastName,
						new Address(town, postal, street), phone);

				// set flag for success message and button link to +Farm
				showsuccess = 2;
				return;
			} else if (privilege.toLowerCase().equals("vet")) {
				userDAO.registerVet(email, password2, vetFirstname,
						vetLastName, infirmary);
			}
			// set flag for stats refresh
			Index.newEntity = true;
			// set flag for success message
			showsuccess = 1;
		} catch (Exception e) {
			// mora ovako, ne moze sa return Error_500.class jer ga prestigne
			// drugi exception
			Error_500.PREVIOUS_PAGE = resources.getPageName();
			response.sendRedirect("/evz/errorPages/Error_500");
		}
	}

	@CommitAfter
	public Object onSubmitFromInfForm() {
		Address address = new Address(infTown, infPostal, infStreet);

		@SuppressWarnings("unchecked")
		List<Address> addresses = session.createCriteria(Address.class).list();
		for (Address addr : addresses) {
			if (addr.equals(address)) {
				infirmary = new Infirmary(infName, addr);
				session.save(infirmary);
				return null;
			}
		}
		session.save(address);

		infirmary = new Infirmary(infName, address);
		session.save(infirmary);
		return infZone.getBody();
	}

	@InjectPage
	private AddFarm farm;

	public Object onActionFromNewFarm() {
		@SuppressWarnings("unchecked")
		List<Owner> list = session.createCriteria(Owner.class).list();
		farm.setOwner(list.get(list.size() - 1));
		return farm;
	}

	@SuppressWarnings("unchecked")
	public SelectModel getInfirmaryModel() {
		return new InfirmarySelectModel(session.createCriteria(Infirmary.class)
				.list());

	}

	public InfirmaryEncoder getInfirmaryEncoder() {
		return new InfirmaryEncoder(session);
	}

	public boolean isSuccessfull() {
		if (showsuccess == 1) {
			return true;
		}
		return false;
	}

	public boolean isSuccessfullFromOwner() {
		if (showsuccess == 2) {
			return true;
		}
		return false;
	}
}
