package fit.piris.evz.services.dao.user;

import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fit.piris.evz.entities.Address;
import fit.piris.evz.entities.Infirmary;
import fit.piris.evz.entities.users.User;
import fit.piris.evz.entities.users.Vet;
import fit.piris.evz.entities.users.Owner;
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

	public void registerOwner(String email, String password, Long personalId,
			String firstName, String lastName, Address address, String phone) {

		Owner o = new Owner(email, MD5.md5(password),personalId, firstName, lastName, null, phone);
		
		@SuppressWarnings("unchecked")
		List<Address> addresses = session.createCriteria(Address.class).list();
		for (Address addr : addresses) {
			if (addr.equals(address)) {
				o.setAddress(addr);
				session.save(o);
				return;
			}
		}
		session.save(address);

		o.setAddress(address);
		session.save(o);
	}

	public void registerVet(String email, String password, String firstName,
			String lastName, Infirmary infirmary) {

		Vet v = new Vet(email, MD5.md5(password), firstName, lastName,
				infirmary);
		session.save(v);
	}

	public void delete(Long id) {
		
	}

}
