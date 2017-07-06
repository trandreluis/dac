package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.entityGraph.TeacherDAO;
import br.edu.ifpb.mt.daca.entities.entityGraph.Teacher;

public class MainDeleteAllEntityGraph {

	public static void main(String[] args) throws DacaException {
		TeacherDAO dao = new TeacherDAO();
		try {
			List<Teacher> teachers = dao.getAll();
			for (Teacher teacher : teachers) {
				dao.delete(teacher);
			}
		} finally {
			dao.close();
		}
		
	}
	
}
