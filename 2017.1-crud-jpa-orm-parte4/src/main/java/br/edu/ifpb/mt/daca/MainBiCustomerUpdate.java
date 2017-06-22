package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.bidirecional.CustomerDAO;
import br.edu.ifpb.mt.daca.entities.bidirecional.Address;
import br.edu.ifpb.mt.daca.entities.bidirecional.Customer;

public class MainBiCustomerUpdate {

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
			address.setCustomer(customer);
			customer.setAddress(address);

			dao.save(customer);

			System.out.println(customer);

			// Depois atualizar
			customer.setFirstName("Primeiro nome modificado");
			customer.getAddress().setStreet1("outro endereço 1");

			dao.update(customer);

			System.out.println(customer);
		} finally {
			dao.close();
		}
	}

}
