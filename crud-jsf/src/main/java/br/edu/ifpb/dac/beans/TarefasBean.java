package br.edu.ifpb.dac.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.dac.dao.TarefaDAO;
import br.edu.ifpb.dac.entidades.Tarefa;

@ManagedBean
@ViewScoped
public class TarefasBean {

	private TarefaDAO daoTarefa = new TarefaDAO();
	
	public ArrayList<Tarefa> listar() {
		return daoTarefa.listar();		
	}
	
	public void filtar() {
		
	}
	
}
