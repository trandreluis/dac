package br.edu.ifpb.mt.daca.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.mt.daca.dao.PersistenciaDacaException;
import br.edu.ifpb.mt.daca.dao.UserDAO;
import br.edu.ifpb.mt.daca.entities.User;

@ViewScoped
@ManagedBean
public class UserDelete extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4189421120388504165L;

	private User user;

	private UserDAO userDAO = new UserDAO();

	public String delete() {
		try {
			userDAO.delete(user);
			reportarMensagemDeSucesso("Usuário '" + user.getFirstName() + "'removido com sucesso!");
		} catch (PersistenciaDacaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return EnderecoPaginas.PAGINA_PRINCIPAL;
	}

	public String cancel() {
		return EnderecoPaginas.PAGINA_PRINCIPAL;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
