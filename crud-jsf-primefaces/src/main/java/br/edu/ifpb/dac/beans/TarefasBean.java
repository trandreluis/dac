package br.edu.ifpb.dac.beans;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.component.inputtext.InputText;

import br.edu.ifpb.dac.dao.TarefaDAO;
import br.edu.ifpb.dac.entidades.Tarefa;

/**
 * 
 * email: tr.andreluis@gmail.com
 * 
 * @author <a href="https://github.com/trandreluis">André Luís</a>
 *
 */

@ManagedBean
@RequestScoped
public class TarefasBean {

	private ArrayList<Tarefa> tarefas;
	
	private String tituloBuscado;
	
	private InputText inputTituloTarefa;
	
	private TarefaDAO daoTarefas = new TarefaDAO();
	
	@PostConstruct
	public void iniciar() {
		buscar();
	}

	public void buscar() {		
		tarefas = daoTarefas.buscarPorTitulo(tituloBuscado);
	}
	
	public void limpar() {
		this.tituloBuscado = "";
		buscar();
	}
	
	public ArrayList<Tarefa> getTarefas() {
		return tarefas;
	}


	public void setTarefas(ArrayList<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
	public String getTituloBuscado() {
		return tituloBuscado;
	}

	public void setTituloBuscado(String tituloBuscado) {
		this.tituloBuscado = tituloBuscado;
	}

	public InputText getInputTituloTarefa() {
		return inputTituloTarefa;
	}

	public void setInputTituloTarefa(InputText tituloTarefa) {
		this.inputTituloTarefa = tituloTarefa;
	}
	
}
