package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.AddressSTDAO;
import br.edu.ifpb.mt.daca.entities.st.AddressST;

public class MainAddressSTGetAll {

	public static void main(String[] args) throws DacaException {

		AddressSTDAO dao = new AddressSTDAO();
		try {
			List<AddressST> usuarios = dao.getAll();

			for (AddressST AddressST : usuarios) {
				System.out.println(AddressST);
			}

		} finally {
			dao.close();
		}
	}

}
