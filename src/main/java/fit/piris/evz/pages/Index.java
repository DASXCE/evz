package fit.piris.evz.pages;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import fit.piris.evz.annotations.VetAccess;
import fit.piris.evz.annotations.OwnerAccess;
import fit.piris.evz.entities.animal.Animal;
import fit.piris.evz.entities.farm.Farm;
import fit.piris.evz.entities.users.User;
import fit.piris.evz.entities.users.Vet;
import fit.piris.evz.entities.users.Owner;
import fit.piris.evz.pages.farm.ViewFarm;
import fit.piris.evz.pages.user.ViewUser;
import fit.piris.evz.services.dao.user.UserDAO;
import fit.piris.evz.services.security.Authenticator;

/**
 * Start page of application evz.
 */
@VetAccess
@OwnerAccess
@Import(stylesheet = { "context:layout/canvasAdmin/stylesheets/sample_pages/invoice.css" })
public class Index {

	/*
	 * Tapestry Services
	 */
	@Inject
	private Authenticator authenticator;
	
	/*
	 * Hibernate Services
	 */
	@Inject
	private Session session;
	
	/*
	 * My Services
	 */
	@Inject
	private UserDAO userDAO;

	/*
	 * stats
	 */
	@Persist
	@Property
	private int noOfUsers;

	@Persist
	@Property
	private int noOfFarms;

	@Persist
	@Property
	private int noOfVets;

	@Persist
	@Property
	private int noOfAnimals;

	@Property
	private Date now = new Date();
	
	@Property
	private Owner ownerTmp;
	
	@Property
	private Farm farmTmp;

	/*
	 * 
	 */
//	@Property
//	private List<Owner> owners;

	@Component
	private Zone statZone;
	
	/*
	 * refresuj samo ako se dodalo nesto novo u bazi...bilo sta...mora se setovati posle unosa!
	 */
	@Persist(PersistenceConstants.FLASH)
	public static boolean newEntity;

	public void onActivate() {
		now = new Date();
		if (newEntity) {
			noOfUsers = session.createCriteria(User.class).list().size();

			noOfFarms = session.createCriteria(Farm.class).list()
					.size();

			noOfVets = session.createCriteria(Vet.class).list()
					.size();

			noOfAnimals = session.createCriteria(Animal.class).list()
					.size();
			
			
		}
//		vlasnici = sviVlasnici();
	}

	/*
	 * ako je administrator, koristim za prikaz komponenti koje zahtjevaju ovu privilegiju
	 */
	public boolean isAdmin() {
		if (!(authenticator.getLoggedUser() instanceof Vet)
				&& !(authenticator.getLoggedUser() instanceof Owner)) {
			return true;
		}
		return false;
	}

	/*
	 * privremena promjenljiva kojom se krecem kroz petlju - za tabelu vlasnika
	 */
	
	/*
	 * metoda koja puni listu vlasnici
	 */
	@SuppressWarnings("unchecked")
	public List<Owner> getOwners() {
		return session.createCriteria(Owner.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Farm> getFarms() {
		List<Farm> list = session.createCriteria(Farm.class).list();
		List<Farm> l = new CopyOnWriteArrayList<>();
//		return session.createCriteria(Gazdinstvo.class).add(Restrictions.isNull("vlasnik")).list();
		for (Farm farm : list) {
			System.out.println(farm.getName());
			System.out.println(farm.getOwner());
			if (farm.getOwner()!=null) {
				l.add(farm);
			}
		}
		return l;
	}

	public Object onActionFromRefresh() {

		noOfUsers = session.createCriteria(User.class).list().size();

		noOfFarms = session.createCriteria(Farm.class).list()
				.size();

		noOfVets = session.createCriteria(Vet.class).list()
				.size();

		noOfAnimals = session.createCriteria(Animal.class).list()
				.size();
		
		return statZone.getBody();
	}

	public boolean notNull(Farm farm) {
		if (farm != null) {
			return true;
		}
		return false;
	}
	
	@CommitAfter
	public Object onActionFromDeleteOwner(Owner owner) {
		userDAO.delete(owner);
		// return null da bi uradio refresh strane, sa void to nebi
		return null;
	}
	
	public Object onActionFromEditOwner(Owner owner) {
		ViewUser.user = owner;
		return ViewUser.class;
	}
	
	public Object onActionFromEditOwner2(Owner owner) {
		ViewUser.user = owner;
		return ViewUser.class;
	}
	
	public Object onActionFromEditFarm(Farm farm) {
		ViewFarm.farm = farm;
		return ViewFarm.class;
	}
}
