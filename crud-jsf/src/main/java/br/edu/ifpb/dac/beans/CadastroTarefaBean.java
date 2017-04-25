package br.edu.ifpb.dac.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.dac.dao.TarefaDAO;
import br.edu.ifpb.dac.entidades.Tarefa;

@ManagedBean
@ViewScoped
public class CadastroTarefaBean {
	
	private TarefaDAO daoTarefa = new TarefaDAO();
	
	private Tarefa tarefa;

	public void preRenderizacao() {
		if(this.tarefa == null) {
			this.tarefa = new Tarefa();
		}
	}
	
	public String cadastrar() {
		daoTarefa.salvar(tarefa);
		return "Tarefas?faces-redirect=true";
	}
	
	public String editar() {
		return null;
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
}
