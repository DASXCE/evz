package fit.piris.evz.pages.admin.add;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.catalina.connector.Request;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import fit.piris.evz.entities.Address;
import fit.piris.evz.entities.farm.Product;
import fit.piris.evz.entities.farm.AnimalSpecie;
import fit.piris.evz.entities.users.Owner;
import fit.piris.evz.enums.ProductType;
import fit.piris.evz.enums.Species;
import fit.piris.evz.services.dao.farm.FarmDAO;

@Import(library = { "context:layout/canvasAdmin/javascripts/all.js",
		"context:layout/canvasAdmin/javascripts/jquery.blockUI.js" })
public class AddFarm {

	@Inject
	private Session session;

	/*
	 * farm information
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
	 * animal Species
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

	/*
	 * owner
	 */

	// @Persist(PersistenceConstants.FLASH)
	private Owner owner;

	@Inject
	private FarmDAO dao;

	@Property
	@Persist(PersistenceConstants.FLASH)
	private boolean showsuccess;

	@CommitAfter
	public void onSubmitFromForm() {
		/*
		 * animal species
		 */
		animalSpecies = new CopyOnWriteArrayList<AnimalSpecie>();
		if (cattle) {
			animalSpecies.add(new AnimalSpecie(Species.CATTLE));
		}
		if (ungulates) {
			animalSpecies.add(new AnimalSpecie(Species.UNGULATE));
		}
		if (goats) {
			animalSpecies.add(new AnimalSpecie(Species.GOAT));
		}
		if (sheep) {
			animalSpecies.add(new AnimalSpecie(Species.SHEEP));
		}
		if (pigs) {
			animalSpecies.add(new AnimalSpecie(Species.PIG));
		}
		if (poultry) {
			animalSpecies.add(new AnimalSpecie(Species.POULTRY));
		}

		/*
		 * produtcs
		 */
		products = new CopyOnWriteArrayList<Product>();
		if (eggs) {
			products.add(new Product(ProductType.EGGS));
		}
		if (milk) {
			products.add(new Product(ProductType.MILK));
		}
		if (meat) {
			products.add(new Product(ProductType.MEAT));
		}
		for (Product tip : products) {
			System.out.println(tip.getType());
		}
		dao.save(code, name, new Address(town, postal, street), owner,
				products, animalSpecies, null);
		showsuccess = true;

	}

	@Property
	private Owner ownerTmp;

	@Property
	@Persist
	private List<Owner> owners;

	@SuppressWarnings("unchecked")
	public List<Owner> allOwners() {

		List<Owner> list = session.createCriteria(Owner.class).list();
		List<Owner> l = new ArrayList<Owner>();
		for (Owner o : list) {
			if (o.getFarm() == null) {
				l.add(o);
			}
		}

		return l;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public boolean forOwner() {
		if (owner != null) {
			return true;
		}
		return false;
	}

	public void onActivate(Owner owner) {
		this.owner = owner;
	}

	public Object onPassivate() {
		return owner;
	}

	@Property
	@Persist(PersistenceConstants.FLASH)
	private boolean showTable;

	public void onActionFromFillTable() {
		owners = allOwners();
		showTable = true;
	}

	public boolean isSelected(Owner o) {
		if (owner == null || o == null) {
			return false;
		}
		if (o.getPersonalId().equals(owner.getPersonalId())) {
			return true;
		}
		return false;
	}


	public void onActionFromSelect(Owner owner) {
		this.owner = owner;

	}
	
	public void onActionFromDeSelect() {
		this.owner = null;

	}
}
