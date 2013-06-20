package fit.piris.evz.pages.zivotinja;

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

import fit.piris.evz.annotations.VeterinarAccess;
import fit.piris.evz.entities.Adresa;
import fit.piris.evz.entities.gazdinstvo.Gazdinstvo;
import fit.piris.evz.entities.zivotinje.UsnaMarkica;
import fit.piris.evz.entities.zivotinje.ZdravstveniKarton;
import fit.piris.evz.entities.zivotinje.Zivotinja;
import fit.piris.evz.enums.Pol;
import fit.piris.evz.pages.Index;
import fit.piris.evz.pages.errorPages.Error_500;
import fit.piris.evz.pages.gazdinstvo.ViewGazdinstvo;
import fit.piris.evz.services.dao.gazdinstvo.GazdinstvoDAO;
import fit.piris.evz.services.dao.zivotinja.ZivotinjaDAO;

@VeterinarAccess
@Import(library = { "context:layout/canvasAdmin/javascripts/all.js" })
public class AddZivotinja {

	@Persist
	public static Gazdinstvo gazdinstvo;

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
	private GazdinstvoDAO gazdinstvoDAO;
	
	@CommitAfter
	public void onSubmitFromForma() throws IOException {
		try {
			
			Zivotinja mama = zivotinjaDAO.getRoditelj(broj_mame, drzava_mame);
			Zivotinja tata = zivotinjaDAO.getRoditelj(broj_tate, drzava_tate);
			Zivotinja z = new Zivotinja(new UsnaMarkica(broj, drzava), null,
					new ZdravstveniKarton(), ime, drodjenja, duginuca, null,
					mama, tata);
			if (pol.equalsIgnoreCase("MUSKO")) {
				z.setPol(Pol.MUSKO);
			}else {
				z.setPol(Pol.ZENSKO);
			}
			zivotinjaDAO.save(z);
			gazdinstvoDAO.addZivotinja(gazdinstvo, z);
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
