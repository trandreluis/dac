package br.edu.ifpb.dac.beans;

import javax.faces.bean.ManagedBean;

import br.edu.ifpb.dac.dao.TarefaDAO;
import br.edu.ifpb.dac.entidades.Tarefa;

@ManagedBean
public class TarefaDelete {

	private Tarefa tarefa;
	
	private TarefaDAO daoTarefa = new TarefaDAO();
	
	public String deletar() {
		daoTarefa.deletar(this.tarefa);
		return "Tarefas?faces-redirect=true";
	}
	
	public String cancelar() {
		return "Tarefas?faces-redirect=true";
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
}
