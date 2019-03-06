package br.com.metraton.prova.dao;

import java.util.Collection;
import java.util.List;

import br.com.metraton.prova.model.User;

public interface UserDao {

	public void add(User u);
	
	public List<User> getAllUsers();

	public void remove(User u);

	public User getUser(int index);

	public void edit(User user,int indice);

	public List<User> getSpecificUsers(User user);
	
	public List<User> getAllUsersMapped();
}
