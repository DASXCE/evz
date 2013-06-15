package fit.piris.evz.services.dao.gazdinstvo;


import fit.piris.evz.entities.gazdinstvo.Gazdinstvo;


public interface GazdinstvoDAO {

	public boolean save(Gazdinstvo g);
	public boolean update(Gazdinstvo g);
	public boolean update(Long sifra);
	public boolean update(String naziv);
}
