package br.edu.ifpb.mt.daca.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.mt.daca.dao.UserDAO;
import br.edu.ifpb.mt.daca.entities.User;
import br.edu.ifpb.mt.daca.util.Messages;

@ViewScoped
@ManagedBean
public class UserEdit {

	private User user;

	private UserDAO userDAO = new UserDAO();

	public void preRenderView() {
		if (user == null) {
			user = new User();
		}
	}

	public String saveUser() {
		if (isEdicaoDeUsuario()) {
			userDAO.update(user);
		} else {
			userDAO.save(user);
		}

		Messages.addFlashMessage("User '" + user.getFirstName() + "' saved");

		return "index.xhtml?faces-redirect=true";
	}

	public boolean isEdicaoDeUsuario() {
		return user.getId() != null;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
