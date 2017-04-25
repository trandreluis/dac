package br.edu.ifpb.dac.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.dac.entidades.Tarefa;

@ManagedBean
@ViewScoped
public class CadastroTarefaBean {
	
	private Tarefa tarefa;
	
	public String cadastrar() {
		TarefaDAO.salvar(tarefa);
		return "Tarefas?faces-redirect=true";
	}
	
	public void preRenderizacao() {
		if(this.tarefa == null) {
			this.tarefa = new Tarefa();
		}
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
}
