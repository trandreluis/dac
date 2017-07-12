package br.edu.ifpb.mt.daca.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpb.mt.daca.dao.PersistenciaDacaException;
import br.edu.ifpb.mt.daca.dao.UserDAO;
import br.edu.ifpb.mt.daca.entities.User;

@RequestScoped
@ManagedBean
public class Index extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 773395912600598323L;

	private List<User> users;

	private UserDAO userDAO = new UserDAO();

	private String firstNameFiltro;

	public List<User> getUsers() {
		return users;
	}

	public String getFirstNameFiltro() {
		return firstNameFiltro;
	}

	public void setFirstNameFiltro(String firstNameFiltro) {
		this.firstNameFiltro = firstNameFiltro;
	}

	@PostConstruct
	public void init() {
		filtrar();
	}

	public void filtrar() {
		try {
			users = userDAO.findUserByFirstName(firstNameFiltro);
		} catch (PersistenciaDacaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public void limpar() {
		firstNameFiltro = null;
	}

}
