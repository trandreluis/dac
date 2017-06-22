package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.unidirecional.CustomerDAO;
import br.edu.ifpb.mt.daca.entities.unidirecional.Address;
import br.edu.ifpb.mt.daca.entities.unidirecional.Customer;

public class MainUniCustomerGetByID {

	public static void main(String[] args) throws DacaException {
		CustomerDAO dao = new CustomerDAO();
		try {
			// Primeiro salvar
			Customer customer = new Customer();

			customer.setFirstName("João");
			customer.setLastName("Silva");
			customer.setPhoneNumber("(83) 1234 5678");
			customer.setEmail("joao.silva80@email.com");
			Address address = new Address();
			address.setStreet1("street1");
			address.setStreet2("street2");
			address.setZipcode("123456");
			address.setCity("city");
			address.setCountry("country");
			address.setState("state");
			customer.setAddress(address);

			dao.save(customer);

			// Depois recuperar pelo identificador
			Customer resultado = dao.getByID(customer.getId());

			System.out.println(customer.equals(resultado));
		} finally {
			dao.close();
		}
	}

}
