package fit.piris.evz.services.dao.owner;

import fit.piris.evz.entities.users.Owner;

public interface OwnerDAO {

	public boolean register(String email, String password, Integer personalId, String firsstName,
			String lastName, String address, String phone);
	public boolean update(Owner o);
	public boolean update(Integer personalId);
}
