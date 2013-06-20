package fit.piris.evz.pages.zivotinja;

import java.util.Date;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;

import fit.piris.evz.annotations.VeterinarAccess;
import fit.piris.evz.annotations.VlasnikAccess;
import fit.piris.evz.entities.gazdinstvo.Gazdinstvo;
import fit.piris.evz.entities.zivotinje.Zivotinja;
import fit.piris.evz.services.dao.gazdinstvo.GazdinstvoDAO;
import fit.piris.evz.services.dao.zivotinja.ZivotinjaDAO;

@VlasnikAccess
@VeterinarAccess
@Import(library = { "context:layout/canvasAdmin/javascripts/all.js"})
public class ViewZivotinja {
	@Persist
	public static Zivotinja zivotinja;

	@Property
	private Long broj;

	@Property
	private String drzava;

	@Property
	private Long broj_mame;

	@Property
	private String drzava_mame;

	@Property
	private Long broj_tate;

	@Property
	private String drzava_tate;

	@Property
	private String ime;

	@Property
	private String pol;

	@Property
	private String drodjenja;

	@Property
	private String duginuca;

	@Inject
	private Response response;

	@Inject
	private ComponentResources resources;

	@Property
	@Persist(PersistenceConstants.FLASH)
	private int showsuccess;

	@Inject
	private ZivotinjaDAO zivotinjaDAO;
	
	@Inject
	private GazdinstvoDAO gazdinstvoDAO;
	
	public void onActivate(){
		broj = zivotinja.getMarkica().getBroj();
		drzava = zivotinja.getMarkica().getDrzava();
		if (zivotinja.getMajka()!=null) {
			broj_mame = zivotinja.getMajka().getMarkica().getBroj();
			drzava_mame = zivotinja.getMajka().getMarkica().getDrzava();
		}
		if (zivotinja.getOtac()!=null) {
			broj_tate = zivotinja.getOtac().getMarkica().getBroj();
			drzava_tate = zivotinja.getOtac().getMarkica().getDrzava();
		}
		ime = zivotinja.getIme();
		pol = zivotinja.getPol().toString();
		drodjenja = zivotinja.getDatumRodjenja().toString();
		if (zivotinja.getDatumUginuca()!=null) {
			duginuca = zivotinja.getDatumUginuca().toString();
		}
		
	}
}
