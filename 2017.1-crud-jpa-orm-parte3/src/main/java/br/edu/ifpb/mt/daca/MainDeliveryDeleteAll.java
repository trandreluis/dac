package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.DeliveryDAO;
import br.edu.ifpb.mt.daca.entities.Delivery;


public class MainDeliveryDeleteAll {

	public static void main(String[] args) throws DacaException {
		DeliveryDAO dao = new DeliveryDAO();
		try {
			List<Delivery> deliveries = dao.getAll();
			for (Delivery delivery : deliveries) {
				dao.delete(delivery);
			}
		} finally {
			dao.close();
		}
	}
	
}
