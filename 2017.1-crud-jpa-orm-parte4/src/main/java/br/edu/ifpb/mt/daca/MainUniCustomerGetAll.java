package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.unidirecional.CustomerDAO;
import br.edu.ifpb.mt.daca.entities.unidirecional.Customer;

public class MainUniCustomerGetAll {

	public static void main(String[] args) throws DacaException {

		CustomerDAO dao = new CustomerDAO();
		try {
			List<Customer> customers = dao.getAll();

			for (Customer customer : customers) {
				System.out.println(customer);
			}

		} finally {
			dao.close();
		}
	}

}
