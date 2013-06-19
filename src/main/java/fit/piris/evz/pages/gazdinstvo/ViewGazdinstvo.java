package fit.piris.evz.pages.gazdinstvo;

import java.util.List;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import fit.piris.evz.annotations.VeterinarAccess;
import fit.piris.evz.annotations.VlasnikAccess;
import fit.piris.evz.entities.gazdinstvo.Gazdinstvo;
import fit.piris.evz.entities.gazdinstvo.TipProizvodnje;
import fit.piris.evz.entities.gazdinstvo.VrstaZivotinje;
import fit.piris.evz.entities.users.Veterinar;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.entities.zivotinje.Zivotinja;
import fit.piris.evz.pages.user.ViewUser;
import fit.piris.evz.services.dao.gazdinstvo.GazdinstvoDAO;
import fit.piris.evz.services.security.Authenticator;
@VlasnikAccess
@VeterinarAccess
@Import(library = { "context:layout/canvasAdmin/javascripts/all.js",
"context:layout/canvasAdmin/javascripts/jquery.blockUI.js" })
public class ViewGazdinstvo {

	@Persist
	public static Gazdinstvo gazdinstvo;
	
	@Inject
	private Session session;
	
	@Property
	private Zivotinja zivotinjaTmp;
	
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

	private List<TipProizvodnje> tipProizvodnje;

	@Property
	private boolean meso;
	@Property
	private boolean mlijeko;
	@Property
	private boolean jaja;

	/*
	 * vrste zivotinja
	 */

	private List<VrstaZivotinje> vrsteZivotinja;
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

	@Inject
	private GazdinstvoDAO dao;

	@Property
	@Persist(PersistenceConstants.FLASH)
	private boolean showsuccess;
	
	@Inject
	private Authenticator authenticator;
	
	@Property
	@Persist(PersistenceConstants.FLASH)
	private boolean writePermission;
	
	public boolean isAdminLoggedIn() {
		if (authenticator.getLoggedUser() instanceof Vlasnik
				|| authenticator.getLoggedUser() instanceof Veterinar) {
			return false;
		}
		return true;
	}
	
	public void onActivate() {
		vrsteZivotinja = gazdinstvo.getVrsteZivotinja();
		tipProizvodnje = gazdinstvo.getTipProizvodnje();
		sifra = gazdinstvo.getSifra();
		naziv = gazdinstvo.getNaziv();
		grad = gazdinstvo.getAdresa().getGrad();
		posta = gazdinstvo.getAdresa().getBrPoste();
		ulica = gazdinstvo.getAdresa().getUlica();
		
		List<Zivotinja> l = session.createCriteria(Zivotinja.class).list();
		for (Zivotinja zivotinja : l) {
			System.out.println(zivotinja.getBroj());
		}
	}
	
	public String getProizvodi() {
		StringBuffer bf = new StringBuffer();
		
		for (TipProizvodnje tp : tipProizvodnje) {
			bf.append(tp.getTip()+", ");
		}
		
		return bf.toString();
	}
	
	public String getVrsteZivotinja() {
		StringBuffer bf = new StringBuffer();
		
		for (VrstaZivotinje vs : vrsteZivotinja) {
			bf.append(vs.getVrsta()+", ");
		}
		
		return bf.toString();
	}
	
	public Object onActionFromEditVlasnik(Vlasnik vlasnik) {
		ViewUser.user = vlasnik;
		return ViewUser.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Zivotinja> getZivotinjee() {
		List<Zivotinja> l = session.createCriteria(Zivotinja.class).list();
		for (Zivotinja zivotinja : l) {
			System.out.println(zivotinja.getBroj());
		}
		return null;
//		return session.createCriteria(Zivotinja.class).list();
	}
}
