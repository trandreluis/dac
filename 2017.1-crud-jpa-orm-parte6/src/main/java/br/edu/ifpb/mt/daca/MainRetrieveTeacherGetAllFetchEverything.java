package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.entityGraph.TeacherDAO;
import br.edu.ifpb.mt.daca.entities.entityGraph.Teacher;

public class MainRetrieveTeacherGetAllFetchEverything {

	public static void main(String[] args) throws DacaException {
		
		TeacherDAO teacherDAO = new TeacherDAO();
		
		try {
			
			List<Teacher> teachers = teacherDAO.getAllFetchEverything();
			for (Teacher teacher : teachers) {
				System.out.println(teacher);
			}
			
		} finally {
			teacherDAO.close();
		}
		
	}
	
}
