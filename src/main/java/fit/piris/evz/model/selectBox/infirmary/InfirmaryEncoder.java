package fit.piris.evz.model.selectBox.infirmary;

import org.apache.tapestry5.ValueEncoder;
import org.hibernate.Session;


import fit.piris.evz.entities.Infirmary;

public class InfirmaryEncoder implements ValueEncoder<Infirmary> {

	private Session session;

	public InfirmaryEncoder(Session session) {
		this.session = session;
	}

	public String toClient(Infirmary value) {
		return value.getId().toString();
	}

	public Infirmary toValue(String clientValue) {
		return (Infirmary) session.load(Infirmary.class, new Long(clientValue));
	}

}
