package fit.piris.evz.services.dao.vlasnik;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import fit.piris.evz.entities.users.Vlasnik;

public class VlasnikDAOImpl implements VlasnikDAO{

	@Inject
	private Session session;
	
	
	public boolean update(Vlasnik v) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(Integer jmbg) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean register(String email, String password, Integer jmbg,
			String ime, String prezime, String adresa, String telefon) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
