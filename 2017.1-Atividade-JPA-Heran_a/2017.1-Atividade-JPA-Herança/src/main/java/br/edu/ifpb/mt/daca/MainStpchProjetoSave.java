package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.singleTablePerClassHierarchy.ProjetoDAO;
import br.edu.ifpb.mt.daca.entities.singleTablePerClassHierarchy.ProjetoGrande;
import br.edu.ifpb.mt.daca.entities.singleTablePerClassHierarchy.ProjetoPequeno;

public class MainStpchProjetoSave {

	public static void main(String[] args) {
		ProjetoDAO dao = new ProjetoDAO();
		try {
			ProjetoGrande projetoGrande = new ProjetoGrande();

			projetoGrande.setNome("Negócio Nordeste");
			projetoGrande.setOrcamento(500.0);

			System.out.println(projetoGrande);

			// Salvando projeto grande
			dao.save(projetoGrande);

			ProjetoPequeno projetoPequeno = new ProjetoPequeno();

			projetoPequeno.setNome("Negócio Nordeste");

			System.out.println(projetoPequeno);

			// Salvando projeto pequeno
			dao.save(projetoPequeno);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
	}

}