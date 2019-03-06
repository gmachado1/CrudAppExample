package br.com.metraton.prova.service;

import java.util.ArrayList;
import java.util.List;

import br.com.metraton.prova.model.User;

public interface UserService {

	public void add(User u);
	
	public List<User> getAllUsers();

	public void excluir(int index);

	public User getUser(int index);

	public void edit(User user,int indice);

	public List<User> getSpecificUsers(User user);
	
	public List<User> getAllUsersMapped();
}
