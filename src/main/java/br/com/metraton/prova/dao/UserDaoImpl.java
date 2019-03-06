package br.com.metraton.prova.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.metraton.prova.model.Phone;
import br.com.metraton.prova.model.Role;
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();// tx
			StringBuilder sql = new StringBuilder(
					"select u.id_usuario as idUsuario, u.nome as nomeUsuario , c.id_cargo as idCargo, "
							+ "c.nome as nomeCargo, t.id_telefone as idTel, t.ddd as ddd, t.numero as numero from metratonweb.usuario as u "
							+ "	left join metratonweb.telefone as t on t.id_usuario=u.id_usuario "
							+ "	inner join metratonweb.cargo as c on  u.id_cargo=c.id_cargo where");
			// SQLQuery query = session.createSQLQuery();
			if (user.getName().length() > 2) {
				sql.append(" u.nome like'" + user.getName() + "%' and ");
			}
			if (user.getPhone().length() > 5) {
				try {
					String ddd = user.getPhone().substring(0, 2);
					String number = user.getPhone().substring(2, user.getPhone().length() - 1);
					sql.append(" t.ddd = " + ddd + " and t.numero like'" + number + "%' and ");
				} catch (Exception e) {
					System.out.println("Dados de usuários não esperado." + user);
					e.printStackTrace();
				}
			}
			if (user.getRole().length() > 2) {
				sql.append(" c.nome like'" + user.getRole() + "%' and ");
			}
			System.out.println(sql.substring(0, sql.toString().length() - 5));
			SQLQuery query = session.createSQLQuery(sql.substring(0, sql.toString().length() - 5));
			if (query.list().size() > 0) {
				users.addAll(mapDummieResult(query.list()));
			}

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		return users;
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

	@Override
	public List<User> getAllUsersMapped() {
		List<User> users = new ArrayList<User>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			transaction = session.beginTransaction();
			StringBuilder sql = new StringBuilder(
					"Select u.id_usuario as idUsuario, u.nome as nomeUsuario , c.id_cargo as idCargo, "
							+ "c.nome as nomeCargo, t.id_telefone as idTel, t.ddd as ddd, t.numero as numero from metratonweb.usuario as u "
							+ "	left join metratonweb.telefone as t on t.id_usuario=u.id_usuario "
							+ "	inner join metratonweb.cargo as c on  u.id_cargo=c.id_cargo ");
			// SQLQuery query = session.createSQLQuery();
			SQLQuery query = session.createSQLQuery(sql.toString());
			if (query.list().size() > 0) {
				users.addAll(mapDummieResult(query.list()));
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		return users;
	}

	private List mapDummieResult(List<Object[]> list) {
		List<User> users = new ArrayList<User>();
		for (Object[] row : list) {
			User user = new User();
			try {
				user.setId(Integer.parseInt(row[0].toString()));
				user.setName(row[1].toString());
				user.setRoleId(Integer.valueOf(row[2].toString()));
				user.setRole(row[3].toString());
				user.setRoleEntity(new Role(Integer.valueOf(row[2].toString()), row[3].toString()));
				if (row[4] != null && row[5] != null && row[6] != null) {
					user.setPhoneEntity(
							new Phone(Integer.valueOf(row[4].toString()), Integer.valueOf(row[5].toString()),
									Integer.valueOf(row[6].toString()), Integer.parseInt(row[0].toString())));
					user.setPhone(Integer.valueOf(row[5].toString()) + row[6].toString());
				} else {
					user.setPhone("Não possui");
				}
			} catch (NumberFormatException e) {
				System.out.println("Erro na iteracão com usuario:" + user.toString());
				e.printStackTrace();
			}
			users.add(user);

		}

		return users;
	}

	private List<User> mapResult(List<Object[]> list) {
		List<User> users = new ArrayList<User>();
		for (Object[] row : list) {
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

}
