package fit.piris.evz.services.dao.gazdinstvo;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import fit.piris.evz.entities.gazdinstvo.Gazdinstvo;

public class GazdinstvoDAOImpl implements GazdinstvoDAO {

	@Inject
	private Session session;

	public boolean save(Gazdinstvo g) {
		// TODO Auto-generated method stub
		session.save(g);
		return false;
	}

	public boolean update(Gazdinstvo g) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(Long sifra) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(String naziv) {
		// TODO Auto-generated method stub
		return false;
	}

}
