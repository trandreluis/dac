package br.edu.ifpb.dac.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import br.edu.ifpb.dac.entidades.Tarefa;

@ManagedBean
public class CadastroTarefaBean {

	private ArrayList<Tarefa> tarefas;

	
	
	public ArrayList<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(ArrayList<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
}
