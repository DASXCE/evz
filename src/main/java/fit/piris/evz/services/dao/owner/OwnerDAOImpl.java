package fit.piris.evz.services.dao.owner;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import fit.piris.evz.entities.users.Owner;

public class OwnerDAOImpl implements OwnerDAO{

	@Inject
	private Session session;
	
	
	public boolean update(Owner o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(Integer personalId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean register(String email, String password, Integer personalId,
			String firstName, String lastName, String address, String phone) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
