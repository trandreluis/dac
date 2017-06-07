package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.AddressSTDAO;
import br.edu.ifpb.mt.daca.entities.st.AddressST;

public class MainAddressSTSave {

	public static void main(String[] args) throws DacaException {
		AddressSTDAO dao = new AddressSTDAO();
		try {
			AddressST address = new AddressST();
			
			address.setCity("Monteiro");
			address.setCountry("Brasil");
			address.setState("Paraíba");
			address.setStreet1("Rua do Centro");
			address.setStreet2("Rua da Igreja");
			address.setZipcode("123456");
			
			System.out.println(address);
			
			dao.save(address);

			System.out.println(address);
		} finally {
			dao.close();
		}
	}

}
