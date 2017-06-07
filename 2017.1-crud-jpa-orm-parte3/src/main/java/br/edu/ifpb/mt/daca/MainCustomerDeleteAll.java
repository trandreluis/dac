package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.CustomerDAO;
import br.edu.ifpb.mt.daca.entities.Customer;


public class MainCustomerDeleteAll {

	public static void main(String[] args) throws DacaException {
		CustomerDAO dao = new CustomerDAO();
		try {
			List<Customer> customers = dao.getAll();
			for (Customer customer : customers) {
				dao.delete(customer);
			}
		} finally {
			dao.close();
		}
	}
	
}
