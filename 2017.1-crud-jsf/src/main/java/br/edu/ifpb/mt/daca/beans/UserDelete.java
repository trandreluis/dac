package br.edu.ifpb.mt.daca.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.mt.daca.dao.UserDAO;
import br.edu.ifpb.mt.daca.entities.User;

@ViewScoped
@ManagedBean
public class UserDelete {
	private User user;

	private UserDAO userDAO = new UserDAO();

	public String delete() {
		userDAO.delete(user);
		return "index?faces-redirect=true";
	}

	public String cancel() {
		return "index?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
