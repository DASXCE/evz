package fit.piris.evz.model.selectBox.ambulanta;

import java.util.Map;

import org.apache.tapestry5.OptionModel;

import fit.piris.evz.entities.Ambulanta;

public class AmbulantaOptionModel implements OptionModel{
	
	private Ambulanta ambulanta;
	
	public AmbulantaOptionModel(Ambulanta ambulanta) {
		this.ambulanta = ambulanta;
	}

	public String getLabel() {
		return this.ambulanta.getNaziv();
	}

	public boolean isDisabled() {
		return false;
	}

	public Map<String, String> getAttributes() {
		return null;
	}

	public Object getValue() {
		return this.ambulanta;
	}

}
