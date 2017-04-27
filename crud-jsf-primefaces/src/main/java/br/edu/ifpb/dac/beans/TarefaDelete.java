package br.edu.ifpb.dac.beans;

import javax.faces.bean.ManagedBean;

import br.edu.ifpb.dac.dao.TarefaDAO;
import br.edu.ifpb.dac.entidades.Tarefa;
import br.edu.ifpb.dac.util.Mensagens;

/**
 * 
 * email: tr.andreluis@gmail.com
 * 
 * @author <a href="https://github.com/trandreluis">André Luís</a>
 *
 */

@ManagedBean
public class TarefaDelete {

	private Tarefa tarefa;
	
	private TarefaDAO daoTarefa = new TarefaDAO();
	
	public String deletar() {
		daoTarefa.deletar(this.tarefa);
		Mensagens.MensagemSucesso("A Tarefa foi deletada");
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
