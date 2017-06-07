package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.AddressSTDAO;
import br.edu.ifpb.mt.daca.entities.st.AddressST;


public class MainAddressSTDelete {
	
	public static void main(String[] args) throws DacaException {
		AddressSTDAO dao = new AddressSTDAO();
		try {
			// Primeiro salvar
			AddressST address = new AddressST();

			address.setCity("city");
			address.setCountry("country");
			address.setState("state");
			address.setStreet1("street1");
			address.setStreet2("street2");
			address.setZipcode("zipcode");
			
			dao.save(address);

			System.out.println(dao.getAll().size());
			
			
			// Depois apagar
			
			dao.delete(address);
			
			System.out.println(dao.getAll().size());
		} finally {
			dao.close();
		}
	}

}
