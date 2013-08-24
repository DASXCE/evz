package fit.piris.evz.services.dao.zivotinja;

import fit.piris.evz.entities.animal.Animal;

public interface ZivotinjaDAO {

	public void save(Animal zivotinja);
	
	public Animal getRoditelj(Long broj, String drzava);
}
