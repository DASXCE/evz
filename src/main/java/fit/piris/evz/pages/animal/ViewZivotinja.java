package fit.piris.evz.pages.animal;

import java.util.Date;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;

import fit.piris.evz.annotations.VetAccess;
import fit.piris.evz.annotations.OwnerAccess;
import fit.piris.evz.entities.animal.Animal;
import fit.piris.evz.entities.farm.Farm;
import fit.piris.evz.services.dao.farm.FarmDAO;
import fit.piris.evz.services.dao.zivotinja.ZivotinjaDAO;

@OwnerAccess
@VetAccess
@Import(library = { "context:layout/canvasAdmin/javascripts/all.js"})
public class ViewZivotinja {
	@Persist
	public static Animal zivotinja;

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
	private FarmDAO gazdinstvoDAO;
	
	public void onActivate(){
		broj = zivotinja.getTag().getNumber();
		drzava = zivotinja.getTag().getCountry();
		if (zivotinja.getMother()!=null) {
			broj_mame = zivotinja.getMother().getTag().getNumber();
			drzava_mame = zivotinja.getMother().getTag().getCountry();
		}
		if (zivotinja.getFather()!=null) {
			broj_tate = zivotinja.getFather().getTag().getNumber();
			drzava_tate = zivotinja.getFather().getTag().getCountry();
		}
		ime = zivotinja.getName();
		pol = zivotinja.getSex().toString();
		drodjenja = zivotinja.getBorn().toString();
		if (zivotinja.getDied()!=null) {
			duginuca = zivotinja.getDied().toString();
		}
		
	}
}
