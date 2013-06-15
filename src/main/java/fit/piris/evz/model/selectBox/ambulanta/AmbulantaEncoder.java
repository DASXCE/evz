package fit.piris.evz.model.selectBox.ambulanta;

import org.apache.tapestry5.ValueEncoder;
import org.hibernate.Session;


import fit.piris.evz.entities.Ambulanta;

public class AmbulantaEncoder implements ValueEncoder<Ambulanta> {

	private Session session;

	public AmbulantaEncoder(Session session) {
		this.session = session;
	}

	public String toClient(Ambulanta value) {
		return value.getId().toString();
	}

	public Ambulanta toValue(String clientValue) {
		return (Ambulanta) session.load(Ambulanta.class, new Long(clientValue));
	}

}
