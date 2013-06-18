package fit.piris.evz.services.dao.user;

import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fit.piris.evz.entities.Adresa;
import fit.piris.evz.entities.Ambulanta;
import fit.piris.evz.entities.users.User;
import fit.piris.evz.entities.users.Veterinar;
import fit.piris.evz.entities.users.Vlasnik;
import fit.piris.evz.model.MD5;

public class UserDAOImpl implements UserDAO{

	@Inject
	private Session session;

	public boolean update(User u) {
		session.update(u);
		return true;
	}

	public boolean update(Long id) {
		return false;
	}

	public boolean delete(User u) {
//		if (u instanceof Vlasnik) {
//			
//		}
//		User u = (User) session.get(User.class, new Long(id));

		session.delete(u);
		return true;
	}

	public User find(String email, String password) {
		@SuppressWarnings("unchecked")
		List<User> l = session.createCriteria(User.class)
				.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", password)).list();
		if (l != null && l.size() == 1) {
			return l.get(0);
		}
		return null;
	}

	public void registerAdmin(String email, String password) {
		User u = new User(email, MD5.md5(password));
		session.save(u);
	}

	public void registerVlasnik(String email, String password, Long jmbg,
			String ime, String prezime, Adresa adresa, String telefon) {

		Vlasnik v = new Vlasnik(email, MD5.md5(password),jmbg, ime, prezime, null, telefon);
		
		@SuppressWarnings("unchecked")
		List<Adresa> adrese = session.createCriteria(Adresa.class).list();
		for (Adresa adr : adrese) {
			if (adr.equals(adresa)) {
				v.setAdresa(adr);
				session.save(v);
				return;
			}
		}
		session.save(adresa);

		v.setAdresa(adresa);
		session.save(v);
	}

	public void registerVeterinar(String email, String password, String ime,
			String prezime, Ambulanta ambulanta) {

		Veterinar v = new Veterinar(email, MD5.md5(password), ime, prezime,
				ambulanta);
		session.save(v);
	}

	public void delete(Long id) {
		
	}

}
