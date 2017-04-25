package br.edu.ifpb.dac.dao;

import java.util.ArrayList;

import br.edu.ifpb.dac.entidades.Tarefa;

public class TarefaDAO {

	private static ArrayList<Tarefa> tarefasCadatsradas = new ArrayList<>();
	
	public void salvar(Tarefa tarefa) {
		tarefa.setId(tarefasCadatsradas.size()+1);
		tarefasCadatsradas.add(tarefa);
	}
	
	public ArrayList<Tarefa> listar() {
		return tarefasCadatsradas;
	}
	
}