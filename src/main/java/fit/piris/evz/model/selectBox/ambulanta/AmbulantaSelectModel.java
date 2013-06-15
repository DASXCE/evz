package fit.piris.evz.model.selectBox.ambulanta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.util.AbstractSelectModel;

import fit.piris.evz.entities.Ambulanta;

public class AmbulantaSelectModel extends AbstractSelectModel {

	private Collection<Ambulanta> ambulante;

	public AmbulantaSelectModel(Collection<Ambulanta> ambulante) {
		this.ambulante = ambulante;
	}

	public List<OptionGroupModel> getOptionGroups() {
		return null;
	}

	public List<OptionModel> getOptions() {
		List<OptionModel> list = new ArrayList<OptionModel>();
		for (Ambulanta a : ambulante) {
			list.add(new AmbulantaOptionModel(a));
		}
		return list;
	}

}
