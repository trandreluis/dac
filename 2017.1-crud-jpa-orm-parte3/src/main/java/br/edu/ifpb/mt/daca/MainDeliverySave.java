package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.DeliveryDAO;
import br.edu.ifpb.mt.daca.entities.Delivery;
import br.edu.ifpb.mt.daca.entities.embedded.Address;
import br.edu.ifpb.mt.daca.util.Util;

public class MainDeliverySave {

	public static void main(String[] args) throws DacaException {

		DeliveryDAO dao = new DeliveryDAO();

		try {

			Delivery delivery = new Delivery();

			delivery.setPrazo(Util.getDate(2016, 07, 22));

			Address address = new Address();
			address.setStreet1("street1");
			address.setStreet2("street2");
			address.setZipcode("123456");
			address.setCity("city");
			address.setCountry("country");
			address.setState("state");

			delivery.setAddress(address);

			System.out.println(delivery);

			dao.save(delivery);

			System.out.println(delivery);
		} finally {
			dao.close();
		}

	}

}
