package br.edu.ifpb.mt.dac.entidades.f.bidirecional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.edu.ifpb.mt.dac.dao.f.bidirecional.ColaboradorDAO;
import br.edu.ifpb.mt.dac.dao.f.bidirecional.ProjetoDAO;

public class MainBiSave {

	public static void main(String[] args) {
		ProjetoDAO daoProjeto = new ProjetoDAO();
		ColaboradorDAO daoColcaborador = new ColaboradorDAO();
		try {
			Projeto projeto = new Projeto();
			projeto.setDuracao(10);
			projeto.setNome("Negócio Nordeste");
			projeto.setIntegrantes(new ArrayList<Colaborador>());

			Colaborador colaborador = new Colaborador();
			colaborador.setNome("Andre");
			colaborador.setSobreNome("Luis");
			colaborador.setSalario(new BigDecimal(10000));
			colaborador.setDataNascimento(new Date());
			colaborador.setProjetos(new ArrayList<Projeto>());

			// Colaborador colaborador2 = new Colaborador();
			// colaborador2.setNome("Fulano");
			// colaborador2.setSobreNome("Xavier");
			// colaborador2.setSalario(new BigDecimal(1000));
			// colaborador2.setDataNascimento(new Date());
			//
			// Colaborador colaborador3 = new Colaborador();
			// colaborador3.setNome("Sicrano");
			// colaborador3.setSobreNome("Sousa");
			// colaborador3.setSalario(new BigDecimal(100));
			// colaborador3.setDataNascimento(new Date());

			List<Colaborador> colcaboradores = Arrays.asList(colaborador);
			List<Projeto> projetos = Arrays.asList(projeto);
			
			System.out.println(projeto);
			System.out.println(colaborador);

			daoProjeto.save(projeto);
			daoColcaborador.save(colaborador);

			System.out.println(projeto);
			System.out.println(colaborador);

			projeto.setIntegrantes(colcaboradores);
			colaborador.setProjetos(projetos);

			daoProjeto.update(projeto);
			System.out.println(projeto);
			System.out.println(colaborador);
		} finally {
			daoProjeto.close();
		}
	}

}