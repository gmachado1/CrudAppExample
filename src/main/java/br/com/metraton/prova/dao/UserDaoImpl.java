package br.com.metraton.prova.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;

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
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			FullTextSession fullTextSession = Search.getFullTextSession(session);
			
			fullTextSession.beginTransaction();
			
			//teste
			//fullTextSession.createIndexer().startAndWait();
			QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(User.class).get();
			
			org.apache.lucene.search.Query query = queryBuilder.keyword().wildcard().onField("name").matching(user.getName()+"*").createQuery();
//, "role", "phone"
			//javax.persistence.Query hibernateQuery  =fullTextSession.createFullTextQuery(query, User.class);
			 
			FullTextQuery jpaquery = fullTextSession.createFullTextQuery(query, User.class);
			
			users = jpaquery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
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
