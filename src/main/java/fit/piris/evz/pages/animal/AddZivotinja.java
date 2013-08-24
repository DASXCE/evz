package fit.piris.evz.pages.animal;

import java.io.IOException;
import java.util.Date;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.DateField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;

import fit.piris.evz.annotations.VetAccess;
import fit.piris.evz.entities.Address;
import fit.piris.evz.entities.animal.EarTag;
import fit.piris.evz.entities.animal.MedicalRecord;
import fit.piris.evz.entities.animal.Animal;
import fit.piris.evz.entities.farm.Farm;
import fit.piris.evz.enums.Sex;
import fit.piris.evz.pages.Index;
import fit.piris.evz.pages.errorPages.Error_500;
import fit.piris.evz.pages.farm.ViewFarm;
import fit.piris.evz.services.dao.farm.FarmDAO;
import fit.piris.evz.services.dao.zivotinja.ZivotinjaDAO;

@VetAccess
@Import(library = { "context:layout/canvasAdmin/javascripts/all.js" })
public class AddZivotinja {

	@Persist
	public static Farm gazdinstvo;

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
	private Date drodjenja;

	@Property
	private Date duginuca;

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
	
	@CommitAfter
	public void onSubmitFromForma() throws IOException {
		try {
			
			Animal mama = zivotinjaDAO.getRoditelj(broj_mame, drzava_mame);
			Animal tata = zivotinjaDAO.getRoditelj(broj_tate, drzava_tate);
			Animal z = new Animal(new EarTag(broj, drzava), null,
					new MedicalRecord(), ime, drodjenja, duginuca, null,
					mama, tata);
			if (pol.equalsIgnoreCase("MUSKO")) {
				z.setSex(Sex.MALE);
			}else {
				z.setSex(Sex.FEMALE);
			}
			zivotinjaDAO.save(z);
			gazdinstvoDAO.addAnimal(gazdinstvo, z);
			Index.newEntity = true;
			// set flag for success message
			showsuccess = 1;
			response.sendRedirect("/evz/gazdinstvo/view");
		} catch (Exception e) {
			// mora ovako, ne moze sa return Error_500.class jer ga prestigne
			// drugi exception
			Error_500.PREVIOUS_PAGE = resources.getPageName();
			response.sendRedirect("/evz/errorPages/Error_500");
		}
	}

}
