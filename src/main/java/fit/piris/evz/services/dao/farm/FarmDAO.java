package fit.piris.evz.services.dao.farm;

import java.util.List;

import fit.piris.evz.entities.Address;
import fit.piris.evz.entities.animal.Animal;
import fit.piris.evz.entities.farm.Farm;
import fit.piris.evz.entities.farm.Product;
import fit.piris.evz.entities.farm.AnimalSpecie;
import fit.piris.evz.entities.users.Owner;

public interface FarmDAO {

	public void save(String code, String name, Address address,
			Owner owner, List<Product> products,
			List<AnimalSpecie> species, List<Animal> animals);

	public void addAnimal(Farm farm,Animal animal);
	
	public boolean update(Farm f);

	public boolean update(Long code);

	public boolean update(String name);
}
