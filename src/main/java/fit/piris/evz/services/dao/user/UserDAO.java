package fit.piris.evz.services.dao.user;

import fit.piris.evz.entities.Address;
import fit.piris.evz.entities.Infirmary;
import fit.piris.evz.entities.users.User;

public interface UserDAO {

	public void registerAdmin(String email, String password);

	public void registerOwner(String email, String password, Long personalId,
			String firstName, String lastName, Address address, String phone);

	public void registerVet(String email, String password, String firstName,
			String lastName, Infirmary infirmary);

	public boolean update(User u);

	public boolean update(Long id);

	public boolean delete(User u);
	
	public void delete(Long personalId);

	public User find(String username, String password);
}
