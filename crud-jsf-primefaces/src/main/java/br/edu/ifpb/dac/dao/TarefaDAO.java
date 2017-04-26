package br.edu.ifpb.dac.dao;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import br.edu.ifpb.dac.entidades.Tarefa;

public class TarefaDAO {

	private static ArrayList<Tarefa> tarefasCadatsradas = new ArrayList<>();
	private static AtomicInteger id = new AtomicInteger();
	
	public void salvar(Tarefa tarefa) {
		tarefa.setId(id.getAndIncrement());
		tarefasCadatsradas.add(tarefa);
	}
	
	public void atualizar(Tarefa tarefa) {
		
		int cont = 0;
		
		for(Tarefa t : tarefasCadatsradas) {
			if(t.getId() == tarefa.getId()) {
				break;
			}
			cont++;
		}
		
		tarefasCadatsradas.remove(cont);
		tarefasCadatsradas.add(cont, tarefa);
		
	}
	
	public void deletar(Tarefa tarefa) {
		tarefasCadatsradas.remove(tarefa);
	}
	
	public Tarefa buscarPorID(Integer id) {
		int cont = 0;
		for(Tarefa t : tarefasCadatsradas) {
			if(id == t.getId()) {
				return tarefasCadatsradas.get(cont);
			}
			cont++;
		}
		return null;
	}
	
	public ArrayList<Tarefa> buscarPorTitulo(String titulo) {
		if(titulo == null || titulo.trim().equals("") || tarefasCadatsradas.size() == 0) {
			return tarefasCadatsradas;
		}
		
		ArrayList<Tarefa> tarefasFiltradas = new ArrayList<>();
		
		for (Tarefa t : tarefasCadatsradas) {
			if(t.getTitulo().equalsIgnoreCase(titulo)) {
				tarefasFiltradas.add(t);
			}
		}
		
		return tarefasFiltradas;
		
	}
	
	public ArrayList<Tarefa> listar() {
		return tarefasCadatsradas;
	}

}