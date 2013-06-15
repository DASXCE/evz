package fit.piris.evz.services.dao.gazdinstvo;

import java.util.Set;

import fit.piris.evz.entities.Adresa;
import fit.piris.evz.entities.gazdinstvo.Gazdinstvo;
import fit.piris.evz.entities.gazdinstvo.TipProizvodnje;
import fit.piris.evz.entities.gazdinstvo.VrstaZivotinje;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.entities.zivotinje.Zivotinja;

public interface GazdinstvoDAO {

	public void save(String sifra, String naziv, Adresa adresa,
			Vlasnik vlasnik, Set<TipProizvodnje> tipProizvodnje,
			Set<VrstaZivotinje> vrsteZivotinja, Set<Zivotinja> zivotinje);

	public boolean update(Gazdinstvo g);

	public boolean update(Long sifra);

	public boolean update(String naziv);
}
