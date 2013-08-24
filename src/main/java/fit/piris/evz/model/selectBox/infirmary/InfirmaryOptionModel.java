package fit.piris.evz.model.selectBox.infirmary;

import java.util.Map;

import org.apache.tapestry5.OptionModel;

import fit.piris.evz.entities.Infirmary;

public class InfirmaryOptionModel implements OptionModel{
	
	private Infirmary infirmary;
	
	public InfirmaryOptionModel(Infirmary infirmary) {
		this.infirmary = infirmary;
	}

	public String getLabel() {
		return this.infirmary.getName();
	}

	public boolean isDisabled() {
		return false;
	}

	public Map<String, String> getAttributes() {
		return null;
	}

	public Object getValue() {
		return this.infirmary;
	}

}
