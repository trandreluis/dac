package br.edu.ifpb.dac.beans;

import java.util.ArrayList;

import br.edu.ifpb.dac.entidades.Tarefa;

public class TarefaDAO {

	private static ArrayList<Tarefa> tarefasCadatsradas = new ArrayList<>();
	
	public static void salvar(Tarefa tarefa) {
		tarefasCadatsradas.add(tarefa);
	}
	
	public static ArrayList<Tarefa> listar() {
		return tarefasCadatsradas;
	}
	
}