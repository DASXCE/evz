package fit.piris.evz.model.selectBox.infirmary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.util.AbstractSelectModel;

import fit.piris.evz.entities.Infirmary;

public class InfirmarySelectModel extends AbstractSelectModel {

	private Collection<Infirmary> infirmaries;

	public InfirmarySelectModel(Collection<Infirmary> infirmaries) {
		this.infirmaries = infirmaries;
	}

	public List<OptionGroupModel> getOptionGroups() {
		return null;
	}

	public List<OptionModel> getOptions() {
		List<OptionModel> list = new ArrayList<OptionModel>();
		for (Infirmary a : infirmaries) {
			list.add(new InfirmaryOptionModel(a));
		}
		return list;
	}

}
