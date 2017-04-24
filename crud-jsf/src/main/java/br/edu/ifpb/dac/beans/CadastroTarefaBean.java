package br.edu.ifpb.dac.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.dac.entidades.Tarefa;

@ManagedBean
@ViewScoped
public class CadastroTarefaBean {

	private ArrayList<Tarefa> tarefas;

	private Tarefa tarefa;
	
	public void cadastrar() {
		System.out.println(this.tarefa);
	}
	
	public void preRenderizacao() {
		
		if(this.tarefa == null) {
			this.tarefa = new Tarefa();
		}
		
	}
	
	public ArrayList<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(ArrayList<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
}
