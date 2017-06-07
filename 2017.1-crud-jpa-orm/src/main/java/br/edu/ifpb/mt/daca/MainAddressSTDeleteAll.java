package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.AddressSTDAO;
import br.edu.ifpb.mt.daca.entities.st.AddressST;


public class MainAddressSTDeleteAll {

	public static void main(String[] args) throws DacaException {
		AddressSTDAO dao = new AddressSTDAO();
		try {
			List<AddressST> addresses = dao.getAll();
			for (AddressST address : addresses) {
				dao.delete(address);
			}
		} finally {
			dao.close();
		}
	}
	
}
