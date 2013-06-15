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
		Gazdinstvo gazdinstvo = null;
		Vlasnik v = null;
		@SuppressWarnings("unchecked")
		List<Adresa> adrese = session.createCriteria(Adresa.class).list();
		for (Adresa adr : adrese) {
			if (adr.equals(adresa)) {
				gazdinstvo = new Gazdinstvo(sifra, naziv, adr, null, null, null);
				session.save(gazdinstvo);
				if (vlasnik != null) {
					System.out.println("od argumenta vlasnik: "
							+ vlasnik.getId());
					v = (Vlasnik) session.get(Vlasnik.class,
							new Long(vlasnik.getId()));
					System.out.println("od povucenog iz baze: " + v.getId());
					v.setGazdinstvo(gazdinstvo);
					session.update(v);
				}
				return;
			}
		}
		session.save(adresa);
		gazdinstvo = new Gazdinstvo(sifra, naziv, adresa, null, null, null);
		session.save(gazdinstvo);
		if (vlasnik != null) {
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
