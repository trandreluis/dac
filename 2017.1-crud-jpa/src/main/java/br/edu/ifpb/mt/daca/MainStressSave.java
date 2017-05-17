package br.edu.ifpb.mt.daca;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import br.edu.ifpb.mt.daca.dao.PersistenciaDacaException;
import br.edu.ifpb.mt.daca.dao.UserDAO;
import br.edu.ifpb.mt.daca.entities.User;

public class MainStressSave {

	private static int QUANTIDADE_DE_INSERÇÕES = 10000;
	
	private static int NUMERO_THREADS = 10;
	
	private static User getRandomUser() {
		User user = new User();
		long time = System.nanoTime();

		user.setBirthday(new Date());
		user.setEmail(time + "email@gmail.com");
		user.setFirstName("Sicrano " + time);
		user.setLastName("Silva " + time);

		return user;
	}
	
	private static class InsertThread implements Runnable {
		
		private UserDAO dao;
		
		private InsertThread(UserDAO dao) {
			this.dao = dao;
		}

		@Override
		public void run() {
			try {
				dao.save(getRandomUser());
			} catch (PersistenciaDacaException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		// Criar o DAO para ser usado nas inserções
		UserDAO dao = new UserDAO();
		
		// Criar um serviço para executar múltiplas threads coordenadamente
		ExecutorService exec = Executors.newFixedThreadPool(NUMERO_THREADS);
		List<Future<?>> futures = new ArrayList<Future<?>>();
		
		// Submeter as threads para fazer as inserções
		long begin = System.currentTimeMillis();
		for (int i = 0; i < QUANTIDADE_DE_INSERÇÕES; i++) {
			Future<?> future = exec.submit(new InsertThread(dao));
			futures.add(future);
		}
		
		// Esperar que todas as inserções sejam concluídas
		for (Future<?> future : futures) {
			future.get();
		}
		
		// Reportar tempo de execução
		long end = System.currentTimeMillis();
		System.out.println(String.format("Tempo passado (em ms): %d", end - begin));

		// Fechar o serviço de execução das threads e o DAO
		exec.shutdown();
		dao.close();
		
	}
	
}
