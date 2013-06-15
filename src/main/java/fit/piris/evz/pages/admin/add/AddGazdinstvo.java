package fit.piris.evz.pages.admin.add;

import org.apache.regexp.recompile;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

import fit.piris.evz.entities.Adresa;
import fit.piris.evz.entities.gazdinstvo.Gazdinstvo;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.services.dao.gazdinstvo.GazdinstvoDAO;

public class AddGazdinstvo {

	/*
	 * podaci
	 */
	@Property
	private String sifra;

	@Property
	private String naziv;

	/*
	 * adresa
	 */
	@Property
	private String grad;

	@Property
	private Integer posta;

	@Property
	private String ulica;

	/*
	 * tipovi proizvodnje
	 */
	@Property
	private boolean meso;
	@Property
	private boolean mlijeko;
	@Property
	private boolean jaja;

	/*
	 * vrste zivotinja
	 */
	@Property
	private boolean goveda;
	@Property
	private boolean ovce;
	@Property
	private boolean koze;
	@Property
	private boolean svinje;
	@Property
	private boolean kopitari;
	@Property
	private boolean zivina;

	/*
	 * vlasnik
	 */

	@Persist
	private Vlasnik vlasnik;

	@Inject
	private GazdinstvoDAO dao;

	@Property
	@Persist(PersistenceConstants.FLASH)
	private boolean showsuccess;

	@CommitAfter
	public void onSubmitFromForma() {
		dao.save(sifra, naziv, new Adresa(grad, posta, ulica), vlasnik, null,
				null, null);
		showsuccess = true;
	}
	
	public void onActivate() {
		System.out.println("AddGazdinstvo.onActivate() vlasnik: "+vlasnik.getIme());
	}

	public Vlasnik getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Vlasnik vlasnik) {
		this.vlasnik = vlasnik;
	}

	public boolean forVlasnik() {
		if (vlasnik != null) {
			return true;
		}
		return false;
	}
}
