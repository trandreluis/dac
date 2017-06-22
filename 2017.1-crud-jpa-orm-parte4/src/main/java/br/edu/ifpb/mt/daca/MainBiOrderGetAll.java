package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.bidirecional.OrderDAO;
import br.edu.ifpb.mt.daca.entities.bidirecional.Order;

public class MainBiOrderGetAll {

	public static void main(String[] args) throws DacaException {

		OrderDAO dao = new OrderDAO();
		try {
			List<Order> orders = dao.getAll();

			for (Order order : orders) {
				System.out.println(order);
			}

		} finally {
			dao.close();
		}
	}

}
