package fit.piris.evz.services.dao.vlasnik;

import fit.piris.evz.entities.users.Vlasnik;

public interface VlasnikDAO {

	public boolean register(String email, String password, Integer jmbg, String ime,
			String prezime, String adresa, String telefon);
	public boolean update(Vlasnik v);
	public boolean update(Integer jmbg);
}
