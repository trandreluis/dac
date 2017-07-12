package br.edu.ifpb.mt.daca.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.mt.daca.dao.PersistenciaDacaException;
import br.edu.ifpb.mt.daca.dao.UserDAO;
import br.edu.ifpb.mt.daca.entities.User;

@ViewScoped
@ManagedBean
public class UserEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9011877400978413929L;

	private User user;

	private UserDAO userDAO = new UserDAO();

	public void preRenderView() {
		if (user == null) {
			user = new User();
		}
	}

	public String saveUser() {
		try {
			if (isEdicaoDeUsuario()) {
				userDAO.update(user);
				reportarMensagemDeSucesso("Usuário '" + user.getFirstName() + "' atualizado com sucesso!");
			} else {
				userDAO.save(user);
				reportarMensagemDeSucesso("Usuário '" + user.getFirstName() + "' criado com sucesso!");
			}
		} catch (PersistenciaDacaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return EnderecoPaginas.PAGINA_PRINCIPAL;
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
