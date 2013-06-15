package fit.piris.evz.pages.admin.add;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

import fit.piris.evz.annotations.GuestAccess;
public class AddGazdinstvo {

	/*
	 * podaci
	 */
	@Property
	private Integer sifra;

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
}
