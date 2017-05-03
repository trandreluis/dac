package br.edu.ifpb.mt.daca.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.mt.daca.dao.UserDAO;
import br.edu.ifpb.mt.daca.entities.User;

@ViewScoped
@ManagedBean
public class UserConverterBean {

	private List<User> users;
	
	private User selectedUser;

	private UserDAO userDAO = new UserDAO();

	@PostConstruct
	public void init() {
		users = userDAO.getAll();
	}

	public List<User> getUsers() {
		return users;
	}
	
	public User getSelectedUser() {
		return selectedUser;
	}
	
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

}
