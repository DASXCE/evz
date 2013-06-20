package fit.piris.evz.services.dao.zivotinja;

import fit.piris.evz.entities.zivotinje.Zivotinja;

public interface ZivotinjaDAO {

	public void save(Zivotinja zivotinja);
	
	public Zivotinja getRoditelj(Long broj, String drzava);
}
