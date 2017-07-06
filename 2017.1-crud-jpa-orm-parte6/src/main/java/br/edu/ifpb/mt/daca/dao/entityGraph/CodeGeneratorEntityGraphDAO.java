package br.edu.ifpb.mt.daca.dao.entityGraph;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import br.edu.ifpb.mt.daca.dao.DAO;
import br.edu.ifpb.mt.daca.entities.entityGraph.Class;
import br.edu.ifpb.mt.daca.entities.entityGraph.Discipline;
import br.edu.ifpb.mt.daca.entities.entityGraph.Specialty;
import br.edu.ifpb.mt.daca.entities.entityGraph.Student;
import br.edu.ifpb.mt.daca.entities.entityGraph.Teacher;

public class CodeGeneratorEntityGraphDAO extends DAO {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	private Date getDate(String dateStr) {
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Formato de data errado (dd/MM/yyyy HH:mm): " + dateStr);
		}
	}

	private Teacher getTeacher(String name) {
		Teacher teacher = new Teacher();
		teacher.setName(name);
		teacher.setDisciplines(new HashSet<Discipline>());
		teacher.setSpecialties(new HashSet<Specialty>());
		return teacher;
	}

	private Discipline getDiscipline(String name, Teacher teacher) {
		Discipline discipline = new Discipline();

		discipline.setName(name);
		discipline.setStudents(new HashSet<Student>());
		discipline.setClasses(new HashSet<Class>());

		discipline.setTeacher(teacher);
		teacher.getDisciplines().add(discipline);

		return discipline;
	}

	private Student createAndAssociateStudent(String name, Discipline discipline) {
		Student student = new Student();

		student.setName(name);
		student.setDisciplines(new HashSet<Discipline>());

		student.getDisciplines().add(discipline);
		discipline.getStudents().add(student);

		return student;
	}

	private Class createAndAssociateClass(Date date, Discipline discipline) {
		Class clazz = new Class();

		clazz.setDate(date);

		clazz.setDiscipline(discipline);
		discipline.getClasses().add(clazz);

		return clazz;
	}

	private Specialty createAndAssociateSpecialty(String name, Teacher teacher) {
		Specialty specialty = new Specialty();

		specialty.setName(name);

		specialty.setTeacher(teacher);
		teacher.getSpecialties().add(specialty);

		return specialty;
	}

	public void generateData() {

		Teacher teacher1 = getTeacher("Teacher " + System.nanoTime());

		Discipline discipline1Teacher1 = getDiscipline("Discipline " + System.nanoTime(), teacher1);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline1Teacher1);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline1Teacher1);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline1Teacher1);
		createAndAssociateClass(getDate("30/03/2016 16:30"), discipline1Teacher1);
		createAndAssociateClass(getDate("30/03/2016 17:30"), discipline1Teacher1);
		createAndAssociateClass(getDate("30/03/2016 18:30"), discipline1Teacher1);

		Discipline discipline2Teacher1 = getDiscipline("Discipline " + System.nanoTime(), teacher1);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline2Teacher1);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline2Teacher1);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline2Teacher1);
		createAndAssociateClass(getDate("30/03/2016 16:30"), discipline2Teacher1);
		createAndAssociateClass(getDate("30/03/2016 17:30"), discipline2Teacher1);
		createAndAssociateClass(getDate("30/03/2016 18:30"), discipline2Teacher1);

		Discipline discipline3Teacher1 = getDiscipline("Discipline " + System.nanoTime(), teacher1);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline3Teacher1);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline3Teacher1);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline3Teacher1);
		createAndAssociateClass(getDate("30/03/2016 16:30"), discipline3Teacher1);
		createAndAssociateClass(getDate("30/03/2016 17:30"), discipline3Teacher1);
		createAndAssociateClass(getDate("30/03/2016 18:30"), discipline3Teacher1);

		createAndAssociateSpecialty("Specialty " + System.nanoTime(), teacher1);
		createAndAssociateSpecialty("Specialty " + System.nanoTime(), teacher1);
		createAndAssociateSpecialty("Specialty " + System.nanoTime(), teacher1);

		Teacher teacher2 = getTeacher("Teacher " + System.nanoTime());

		Discipline discipline1Teacher2 = getDiscipline("Discipline " + System.nanoTime(), teacher2);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline1Teacher2);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline1Teacher2);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline1Teacher2);
		createAndAssociateClass(getDate("30/03/2016 16:30"), discipline1Teacher2);
		createAndAssociateClass(getDate("30/03/2016 17:30"), discipline1Teacher2);
		createAndAssociateClass(getDate("30/03/2016 18:30"), discipline1Teacher2);

		Discipline discipline2Teacher2 = getDiscipline("Discipline " + System.nanoTime(), teacher2);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline2Teacher2);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline2Teacher2);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline2Teacher2);
		createAndAssociateClass(getDate("30/03/2016 16:30"), discipline2Teacher2);
		createAndAssociateClass(getDate("30/03/2016 17:30"), discipline2Teacher2);
		createAndAssociateClass(getDate("30/03/2016 18:30"), discipline2Teacher2);

		Discipline discipline3Teacher2 = getDiscipline("Discipline " + System.nanoTime(), teacher2);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline3Teacher2);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline3Teacher2);
		createAndAssociateStudent("Student " + System.nanoTime(), discipline3Teacher2);
		createAndAssociateClass(getDate("30/03/2016 16:30"), discipline3Teacher2);
		createAndAssociateClass(getDate("30/03/2016 17:30"), discipline3Teacher2);
		createAndAssociateClass(getDate("30/03/2016 18:30"), discipline3Teacher2);

		createAndAssociateSpecialty("Specialty " + System.nanoTime(), teacher2);
		createAndAssociateSpecialty("Specialty " + System.nanoTime(), teacher2);
		createAndAssociateSpecialty("Specialty " + System.nanoTime(), teacher2);

		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		try {
			// Basta salvar o Teacher porque fizemos cascade de todas as operações em todas as associações. Fizemos isto
			// para facilitar nossa vida ao mostrar esse exemplo.
			em.persist(teacher1);
			em.persist(teacher2);

			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}

	}
}
