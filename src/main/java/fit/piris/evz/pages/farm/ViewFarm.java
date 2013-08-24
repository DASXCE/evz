package fit.piris.evz.pages.farm;

import java.util.List;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import fit.piris.evz.annotations.VetAccess;
import fit.piris.evz.annotations.OwnerAccess;
import fit.piris.evz.entities.animal.EarTag;
import fit.piris.evz.entities.animal.Animal;
import fit.piris.evz.entities.farm.Farm;
import fit.piris.evz.entities.farm.Product;
import fit.piris.evz.entities.farm.AnimalSpecie;
import fit.piris.evz.entities.users.Vet;
import fit.piris.evz.entities.users.Owner;
import fit.piris.evz.pages.animal.AddZivotinja;
import fit.piris.evz.pages.animal.ViewZivotinja;
import fit.piris.evz.pages.user.ViewUser;
import fit.piris.evz.services.dao.farm.FarmDAO;
import fit.piris.evz.services.security.Authenticator;

@OwnerAccess
@VetAccess
@Import(library = { "context:layout/canvasAdmin/javascripts/all.js",
		"context:layout/canvasAdmin/javascripts/jquery.blockUI.js" })
public class ViewFarm {

	@Persist
	public static Farm farm;

	@Inject
	private Session session;

	@Property
	private Animal animalTmp;

	/*
	 * data
	 */
	@Property
	private String code;

	@Property
	private String name;

	/*
	 * address
	 */
	@Property
	private String town;

	@Property
	private Integer postal;

	@Property
	private String street;

	/*
	 * products
	 */

	private List<Product> products;

	@Property
	private boolean meat;
	@Property
	private boolean milk;
	@Property
	private boolean eggs;

	/*
	 * species
	 */

	private List<AnimalSpecie> animalSpecies;
	@Property
	private boolean cattle;
	@Property
	private boolean sheep;
	@Property
	private boolean goats;
	@Property
	private boolean pigs;
	@Property
	private boolean ungulates;
	@Property
	private boolean poultry;

	@Inject
	private FarmDAO dao;

	@Property
	@Persist(PersistenceConstants.FLASH)
	private boolean showsuccess;

	@Inject
	private Authenticator authenticator;

	@Property
	@Persist(PersistenceConstants.FLASH)
	private boolean writePermission;

	public boolean isAdminLoggedIn() {
		if (authenticator.getLoggedUser() instanceof Owner
				|| authenticator.getLoggedUser() instanceof Vet) {
			return false;
		}
		return true;
	}

	public void onActivate() {
		animalSpecies = farm.getAnimalSpecies();
		products = farm.getProducts();
		code = farm.getCode();
		name = farm.getName();
		town = farm.getAddress().getTown();
		postal = farm.getAddress().getPostal();
		street = farm.getAddress().getStreet();

		// List<Zivotinja> l = session.createCriteria(Zivotinja.class).list();
		// for (Zivotinja zivotinja : l) {
		// System.out.println(zivotinja.getDrzava()+zivotinja.getBroj());
		// }

		// Zivotinja test = new Zivotinja();
		// test.setBroj(5L);
		// test.setDrzava("SRB");
		// System.out.println(test.getDrzava()+test.getBroj());
	}

	public String getProducts() {
		StringBuffer bf = new StringBuffer();

		for (Product pro : products) {
			bf.append(pro.getType() + ", ");
		}

		return bf.toString();
	}

	public String getAnimalSpecies() {
		StringBuffer bf = new StringBuffer();

		for (AnimalSpecie sp : animalSpecies) {
			bf.append(sp.getSpecie() + ", ");
		}

		return bf.toString();
	}

	public Object onActionFromEditOwner(Owner owner) {
		ViewUser.user = owner;
		return ViewUser.class;
	}

	@SuppressWarnings("unchecked")
	public List<Animal> getAnimals() {
		// List<Zivotinja> l = session.createCriteria(Zivotinja.class).list();
		// for (Zivotinja zivotinja : l) {
		// System.out.println(zivotinja.getBroj());
		// }
		// return l;
		return session.createCriteria(Animal.class).list();
	}

	public Object onActionFromAdd() {
		AddZivotinja.gazdinstvo = farm;
		return AddZivotinja.class;
	}

	public boolean isNotNull(Animal animal) {
		if (animal != null) {
			return true;
		}
		return false;
	}

	public Object onActionFromDetails(Long number, String country) {
		EarTag et = new EarTag(number, country);
		Animal a = (Animal) session.get(Animal.class, et);
		ViewZivotinja.zivotinja = a;
		return ViewZivotinja.class;
	}

	public Object[] getMyContext() {
		return new Object[] { animalTmp.getTag().getNumber(),
				animalTmp.getTag().getCountry() };
	}
}
