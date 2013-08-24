package fit.piris.evz.services.dao.zivotinja;

import javax.inject.Inject;

import org.hibernate.Session;

import fit.piris.evz.entities.animal.EarTag;
import fit.piris.evz.entities.animal.Animal;

public class ZivotinjaDAOImpl implements ZivotinjaDAO{

	@Inject
	private Session session;
	@Override
	public void save(Animal zivotinja) {
		session.save(zivotinja);
	}
	
	@Override
	public Animal getRoditelj(Long broj, String drzava) {
		
		return (Animal) session.get(Animal.class, new EarTag(broj,drzava));
	}

}
