package fit.piris.evz.services.dao.farm;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import fit.piris.evz.entities.Address;
import fit.piris.evz.entities.animal.Animal;
import fit.piris.evz.entities.farm.Farm;
import fit.piris.evz.entities.farm.Product;
import fit.piris.evz.entities.farm.AnimalSpecie;
import fit.piris.evz.entities.users.Owner;

public class FarmDAOImpl implements FarmDAO {

	@Inject
	private Session session;

	public void save(String code, String name, Address address,
			Owner owner, List<Product> products,
			List<AnimalSpecie> species, List<Animal> animals) {

		Farm farm = new Farm(code, name, null, null, null,
				null);
		Owner o = null;

		boolean skip = false;// flag, ako je postavljena adresa nemoj opet postavljat...
								// Zbog duplog koda za update vlasnika

		/*
		 * storing each product in database
		 */
		@SuppressWarnings("unchecked")
		// knownProducts = products already in db
		List<Product> knownProducts = session.createCriteria(Product.class).list();
		a:for (Product pro : products) {
			
			/*
			 * if exist in db assign that, if not persist in db (products)
			 */
			
			for (Product proCheck : knownProducts) {
				if (pro.getType().equals(proCheck.getType())) {
					products.remove(pro);
					products.add(proCheck);
					continue a;
				}
			}
			session.save(pro);
		}
		farm.setProducts(products);
		
		/*
		 * storing each animal species in database
		 */
		@SuppressWarnings("unchecked")
		// knownSpecies = already in db
		List<AnimalSpecie> knownSpecies = session.createCriteria(AnimalSpecie.class).list();
		b:for (AnimalSpecie sp : species) {
			
			/*
			 * if exist in db assign that, if not persist in db (animals)
			 */
			
			for (AnimalSpecie spCheck : knownSpecies) {
				if (sp.getSpecie().equals(spCheck.getSpecie())) {
					species.remove(sp);
					species.add(spCheck);
					continue b;
				}
			}
			session.save(sp);
		}
		farm.setAnimalSpecies(species);
		
		@SuppressWarnings("unchecked")
		List<Address> addresses = session.createCriteria(Address.class).list();
		for (Address addr : addresses) {
			/*
			 *if exist in db assign that, if not persist in db (address)
			 */
			if (addr.equals(address)) {

				farm.setAddress(addr);
				session.save(farm);
				skip = true;
				break;
			}
		}
		if (!skip) {
			// sacuvaj novu adresu u bazu
			session.save(address);
			farm.setAddress(address);
			session.save(farm);
		}
		if (owner != null) {
			// ako se kreira direktno za vlasnika moramo update Vlasnik
			o = (Owner) session.get(Owner.class, new Long(owner.getId()));
			o.setFarm(farm);
			session.update(o);
		}
	}

	public boolean update(Farm f) {
		return false;
	}

	public boolean update(Long code) {
		return false;
	}

	public boolean update(String name) {
		return false;
	}

	@Override
	public void addAnimal(Farm farm,Animal animal) {

		List<Animal> list = session.createCriteria(Animal.class).list();
		list.add(animal);
		farm.setAnimals(list);
		session.update(farm);
	}

}
