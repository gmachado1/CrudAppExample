package br.com.metraton.prova;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.metraton.prova.model.User;
import br.com.metraton.prova.util.HibernateUtil;

public class App {
	public static void main(String[] args) {

		User student = new User("Gustavo", "Arquiteto", "884588122");
		User student1 = new User("Luciana", "Desinger", "261822040");
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student objects
			session.save(student);
			session.save(student1);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<User> students = session.createQuery("from User", User.class).list();
			students.forEach(s -> {
				System.out.println("Print student name id : " + s.getName());
			});
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
