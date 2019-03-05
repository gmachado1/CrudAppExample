package br.com.metraton.prova.service;

import java.util.ArrayList;
import java.util.List;

import br.com.metraton.prova.dao.UserDao;
import br.com.metraton.prova.dao.UserDaoImpl;
import br.com.metraton.prova.model.User;

public class UserServiceImpl implements UserService {

	private static List<User> userList = new ArrayList<User>();
	
	private UserDao userDao= new UserDaoImpl();

	public void add(User u) {
		//userList.add(u);
		userDao.add(u);
	}

	public List<User> getAllUsers() {
//		ArrayList toReturn = new ArrayList();
//		toReturn.addAll(userList);
//		User u = new User();
//		u.setName("Alberto");
//		u.setPhone("12345");
//		u.setRole("Programador");
//		toReturn.add(u);
//		u = new User();
//		u.setName("Carlos");
//		u.setPhone("678");
//		u.setRole("Programador");
//		toReturn.add(u);
//		u = new User();
//		u.setName("joão");
//		u.setPhone("1357");
//		u.setRole("Programador Front");
//		toReturn.add(u);
//		u = new User();
//		u.setName("Gabriel");
//		u.setPhone("268");
//		u.setRole("DBA");
//		toReturn.add(u);

//		return toReturn;
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
		userDao.edit(u, indice);;
	}

	@Override
	public List<User> getSpecificUsers(User user) {
		 userList.addAll(userDao.getSpecificUsers(user));
		 return userList;
	}

}
