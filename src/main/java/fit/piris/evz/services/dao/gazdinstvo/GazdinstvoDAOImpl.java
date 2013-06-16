package fit.piris.evz.services.dao.gazdinstvo;

import java.util.List;
import java.util.Set;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import fit.piris.evz.entities.Adresa;
import fit.piris.evz.entities.gazdinstvo.Gazdinstvo;
import fit.piris.evz.entities.gazdinstvo.TipProizvodnje;
import fit.piris.evz.entities.gazdinstvo.VrstaZivotinje;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.entities.zivotinje.Zivotinja;

public class GazdinstvoDAOImpl implements GazdinstvoDAO {

	@Inject
	private Session session;

	public void save(String sifra, String naziv, Adresa adresa,
			Vlasnik vlasnik, Set<TipProizvodnje> tipProizvodnje,
			Set<VrstaZivotinje> vrsteZivotinja, Set<Zivotinja> zivotinje) {

		Gazdinstvo gazdinstvo = new Gazdinstvo(sifra, naziv, null, null, null,
				null);
		Vlasnik v = null;

		boolean skip = false;// flag, ako je postavljena adresa nemoj opet postavljat...
								// Zbog duplog koda za update vlasnika

		/*
		 * moramo sacuvati svaki tipProizvodnje u bazu
		 */
		for (TipProizvodnje tp : tipProizvodnje) {
			session.save(tp);
		}
		gazdinstvo.setTipProizvodnje(tipProizvodnje);
		
		/*
		 * i svaku vrstu zivotinje da bi ih mogli setovati gazdinstvu
		 */
		for (VrstaZivotinje vs : vrsteZivotinja) {
			session.save(vs);
		}
		gazdinstvo.setVrsteZivotinja(vrsteZivotinja);
		
		@SuppressWarnings("unchecked")
		List<Adresa> adrese = session.createCriteria(Adresa.class).list();
		for (Adresa adr : adrese) {
			/*
			 * ako se adresa vec nalazi u bazi, ne dodavaj duplikat nego postavi
			 * tu postojecu
			 */
			if (adr.equals(adresa)) {

				gazdinstvo.setAdresa(adr);
				session.save(gazdinstvo);
				skip = true;
				break;
			}
		}
		if (!skip) {
			// sacuvaj novu adresu u bazu
			session.save(adresa);
			gazdinstvo.setAdresa(adresa);
			session.save(gazdinstvo);
		}
		if (vlasnik != null) {
			// ako se kreira direktno za vlasnika moramo update Vlasnik
			v = (Vlasnik) session.get(Vlasnik.class, new Long(vlasnik.getId()));
			v.setGazdinstvo(gazdinstvo);
			session.update(v);
		}
	}

	public boolean update(Gazdinstvo g) {
		return false;
	}

	public boolean update(Long sifra) {
		return false;
	}

	public boolean update(String naziv) {
		return false;
	}

}
