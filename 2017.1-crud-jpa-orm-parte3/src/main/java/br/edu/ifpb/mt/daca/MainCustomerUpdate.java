package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.CustomerDAO;
import br.edu.ifpb.mt.daca.entities.Customer;
import br.edu.ifpb.mt.daca.entities.embedded.Address;
import br.edu.ifpb.mt.daca.util.Util;




public class MainCustomerUpdate {
	public static void main(String[] args) throws DacaException {
		CustomerDAO dao = new CustomerDAO();
		try {
			// Primeiro salvar
			Customer customer = new Customer();
			
			customer.setFirstName("João");
			customer.setLastName("Silva");
			customer.setPhoneNumber("(83) 1234 5678");
			customer.setEmail("joao.silva80@email.com");
			customer.setCreationDate(Util.getDate(2015, 2, 10));
			customer.setDateOfBirth(Util.getDate(1980, 3, 4));
			Address address = new Address();
			address.setStreet1("street1");
			address.setStreet2("street2");
			address.setZipcode("123456");
			address.setCity("city");
			address.setCountry("country");
			address.setState("state");
			customer.setAddress(address );

			dao.save(customer);

			System.out.println(customer);
			
			// Depois atualizar
			customer.setFirstName("Primeiro nome modificado");
			customer.getAddress().setStreet1("street100");
			
			dao.update(customer);
			
			System.out.println(customer);
		} finally {
			dao.close();
		}
	}

}
