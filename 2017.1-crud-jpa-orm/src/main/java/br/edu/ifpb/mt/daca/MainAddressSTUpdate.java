package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.AddressSTDAO;
import br.edu.ifpb.mt.daca.entities.st.AddressST;


public class MainAddressSTUpdate {
	
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

			System.out.println(address);
			
			// Depois atualizar
			address.setStreet1("Outra rua");
			
			dao.update(address);
			
			System.out.println(address);
		} finally {
			dao.close();
		}
	}

}
