package fit.piris.evz.services.dao.zivotinja;

import javax.inject.Inject;

import org.hibernate.Session;

import fit.piris.evz.entities.zivotinje.UsnaMarkica;
import fit.piris.evz.entities.zivotinje.Zivotinja;

public class ZivotinjaDAOImpl implements ZivotinjaDAO{

	@Inject
	private Session session;
	@Override
	public void save(Zivotinja zivotinja) {
		session.save(zivotinja);
	}
	
	@Override
	public Zivotinja getRoditelj(Long broj, String drzava) {
		
		return (Zivotinja) session.get(Zivotinja.class, new UsnaMarkica(broj,drzava));
	}

}
