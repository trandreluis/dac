package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.unidirecional.OrderDAO;
import br.edu.ifpb.mt.daca.entities.unidirecional.Order;

public class MainUniOrderDeleteAll {

	public static void main(String[] args) throws DacaException {
		OrderDAO dao = new OrderDAO();
		try {
			List<Order> orders = dao.getAll();
			for (Order order : orders) {
				dao.delete(order);
			}
		} finally {
			dao.close();
		}
	}

}
