package br.edu.ifpb.dac.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
@ViewScoped
public class CadastroTarefaBean {

	private Tarefa tarefa;

	private TarefaDAO daoTarefa = new TarefaDAO();

	public void preRenderizacao() {
		if (this.tarefa == null) {
			this.tarefa = new Tarefa();
		}
	}

	public String cadastrar() {
		if (this.tarefa.getId() == null) {
			daoTarefa.salvar(this.tarefa);
			Mensagens.MensagemSucesso("A Tarefa foi cadastrada");
		} else {
			daoTarefa.atualizar(this.tarefa);
			Mensagens.MensagemSucesso("A Tarefa foi atualizada");
		}
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
