package fit.piris.evz.services.dao.user;

import fit.piris.evz.entities.Adresa;
import fit.piris.evz.entities.Ambulanta;
import fit.piris.evz.entities.users.User;

public interface UserDAO {

	public void registerAdmin(String email, String password);

	public void registerVlasnik(String email, String password, Long jmbg,
			String ime, String prezime, Adresa adresa, String telefon);

	public void registerVeterinar(String email, String password, String ime,
			String prezime, Ambulanta ambulanta);

	public boolean update(User u);

	public boolean update(Long id);

	public boolean delete(User u);
	
	public void delete(Long jmbg);

	public User find(String username, String password);
}
