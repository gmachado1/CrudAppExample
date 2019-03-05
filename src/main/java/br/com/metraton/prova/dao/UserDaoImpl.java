package br.com.metraton.prova.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.metraton.prova.model.User;
import br.com.metraton.prova.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	Transaction transaction = null;

	@Override
	public void add(User u) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// inicia transacao
			transaction = session.beginTransaction();
			// salva usuario
			session.save(u);
			// persiste usuario
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	@Override
	public List<User> getSpecificUsers(User user) {
		List<User> users = new ArrayList<User>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			StringBuilder sql = new StringBuilder("select * from user where");
			//SQLQuery query = session.createSQLQuery();
			if (user.getName().length()>2) {
				sql.append(" name like'"+user.getName()+"%' and ");
			}
			if (user.getPhone().length()>2) {
				sql.append(" phone like'"+user.getPhone()+"%' and ");
			}
			if (user.getRole().length()>2) {
				sql.append(" name like'"+user.getRole()+"%' and ");
			}
			System.out.println(sql.substring(0, sql.toString().length()-4));
			SQLQuery query = session.createSQLQuery(sql.substring(0, sql.toString().length()-5));
			if(query.list().size()>0) {
				users.addAll(mapResult(query.list()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	private List<User> mapResult(List<Object[]> list) {
		List<User> users = new ArrayList<User>();
		for (Object[] row: list) {
			User user = new User();
			user.setId(Integer.parseInt(row[0].toString()));
			user.setName(row[1].toString());
			user.setPhone(row[2].toString());
			user.setRole(row[3].toString());
			users.add(user);
		}
		return users;
	}

	private Object fillQueryUserFields(User user, StringBuilder sql) {
		if (!user.getName().equals("")) {

		}
		return sql;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			users = session.createQuery("from User", User.class).list();
			// TODO colocar em log.debug
			users.forEach(s -> {
				System.out.println("Usuario: " + s.toString());
			});
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void remove(User u) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// inicia transacao
			transaction = session.beginTransaction();
			// remove usuario
			session.remove(u);
			// persiste usuario
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(int index) {
		User u = new User();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// inicia a transaction
			transaction = session.beginTransaction();
			// busca usuario
			u = session.get(User.class, index);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public void edit(User user, int indice) {
		{
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				// inicia transaacao
				transaction = session.beginTransaction();
				// edita usuario
				session.saveOrUpdate(user);
				// commit transaction
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
		}

	}

}
