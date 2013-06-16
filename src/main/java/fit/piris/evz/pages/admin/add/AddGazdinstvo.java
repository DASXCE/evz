package fit.piris.evz.pages.admin.add;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.regexp.recompile;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fit.piris.evz.entities.Adresa;
import fit.piris.evz.entities.gazdinstvo.Gazdinstvo;
import fit.piris.evz.entities.gazdinstvo.TipProizvodnje;
import fit.piris.evz.entities.gazdinstvo.VrstaZivotinje;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.enums.TipProizvoda;
import fit.piris.evz.enums.Vrsta;
import fit.piris.evz.services.dao.gazdinstvo.GazdinstvoDAO;

@Import(library = {
		"context:layout/canvasAdmin/javascripts/all.js",
		"context:layout/canvasAdmin/javascripts/jquery.blockUI.js" })

public class AddGazdinstvo {
	
	@Inject
	private Session session;

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

	private Set<TipProizvodnje> tipProizvodnje;

	@Property
	private boolean meso;
	@Property
	private boolean mlijeko;
	@Property
	private boolean jaja;

	/*
	 * vrste zivotinja
	 */

	private Set<VrstaZivotinje> vrsteZivotinja;
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

	@Persist(PersistenceConstants.FLASH)
	private Vlasnik vlasnik;

	@Inject
	private GazdinstvoDAO dao;

	@Property
	@Persist(PersistenceConstants.FLASH)
	private boolean showsuccess;

	@CommitAfter
	public void onSubmitFromForma() {
		/*
		 * vrste zivotinaj add
		 */
		vrsteZivotinja = new HashSet<VrstaZivotinje>();
		if (goveda) {
			vrsteZivotinja.add(new VrstaZivotinje(Vrsta.GOVEDA));
		}
		if (kopitari) {
			vrsteZivotinja.add(new VrstaZivotinje(Vrsta.KOPITARI));
		}
		if (koze) {
			vrsteZivotinja.add(new VrstaZivotinje(Vrsta.KOZE));
		}
		if (ovce) {
			vrsteZivotinja.add(new VrstaZivotinje(Vrsta.OVCE));
		}
		if (svinje) {
			vrsteZivotinja.add(new VrstaZivotinje(Vrsta.SVINJE));
		}
		if (zivina) {
			vrsteZivotinja.add(new VrstaZivotinje(Vrsta.ZIVINA));
		}
		
		/*
		 * tipovi proizvodnje add
		 */
		tipProizvodnje = new HashSet<TipProizvodnje>();
		if (jaja) {
			tipProizvodnje.add(new TipProizvodnje(TipProizvoda.JAJA));
		}
		if (mlijeko) {
			tipProizvodnje.add(new TipProizvodnje(TipProizvoda.MLIJECNI));
		}
		if (meso) {
			tipProizvodnje.add(new TipProizvodnje(TipProizvoda.MESNI));
		}
		
		
		dao.save(sifra, naziv, new Adresa(grad, posta, ulica), vlasnik, tipProizvodnje,
				vrsteZivotinja, null);
		showsuccess = true;
	}
	
	@Property
	private Vlasnik vlasnikTmp;
	
	@Property
	private List<Vlasnik> vlasnici = sviVlasnici();

	@SuppressWarnings("unchecked")
	public List<Vlasnik> sviVlasnici() {
		return session.createCriteria(Vlasnik.class).list();
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
	
	public boolean testVlasnici() {
		if (vlasnici!=null) {
			return true;
		}
		return false;
	}
	
	public boolean isSelected(Vlasnik v) {
		if (vlasnik==null || v==null) {
			return false;
		}
		if (v.getJmbg().equals(vlasnik.getJmbg())) {
			return true;
		}
		return false;
	}
	
	public void onActionFromSelect(Vlasnik vlasnik) {
		this.vlasnik=vlasnik;
	}
}
