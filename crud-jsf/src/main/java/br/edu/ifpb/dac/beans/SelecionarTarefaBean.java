package br.edu.ifpb.dac.beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpb.dac.dao.TarefaDAO;
import br.edu.ifpb.dac.entidades.Tarefa;

@ManagedBean
@RequestScoped
public class SelecionarTarefaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private ArrayList<Tarefa> tarefas;

	private Tarefa tarefaSelecionada;

	private TarefaDAO daoTarefas = new TarefaDAO();

	public SelecionarTarefaBean() {
		this.tarefas = daoTarefas.listar();
	}

	public ArrayList<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(ArrayList<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Tarefa getTarefaSelecionada() {
		return tarefaSelecionada;
	}

	public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
		this.tarefaSelecionada = tarefaSelecionada;
	}

}
