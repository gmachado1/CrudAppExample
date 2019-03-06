package br.com.metraton.prova.service;

import java.util.ArrayList;
import java.util.List;

import br.com.metraton.prova.dao.UserDao;
import br.com.metraton.prova.dao.UserDaoImpl;
import br.com.metraton.prova.model.User;

public class UserServiceImpl implements UserService {

	private static List<User> userList = new ArrayList<User>();

	private UserDao userDao = new UserDaoImpl();

	public void add(User u) {
		// userList.add(u);
		userDao.add(u);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public void excluir(int index) {
		User u = userDao.getUser(index);
		userDao.remove(u);

	}

	@Override
	public User getUser(int index) {
		return userDao.getUser(index);
	}

	@Override
	public void edit(User u, int indice) {
		userDao.edit(u, indice);
		;
	}

	@Override
	public List<User> getSpecificUsers(User user) {
		return (List<User>) userDao.getSpecificUsers(user);
	}

	@Override
	public List<User> getAllUsersMapped() {
		return userDao.getAllUsersMapped();
	}

}
