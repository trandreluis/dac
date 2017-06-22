package br.edu.ifpb.mt.daca;

import java.util.ArrayList;

import br.edu.ifpb.mt.daca.dao.bidirecional.OrderDAO;
import br.edu.ifpb.mt.daca.entities.bidirecional.Order;
import br.edu.ifpb.mt.daca.entities.bidirecional.OrderLine;
import br.edu.ifpb.mt.daca.util.Util;

public class MainBiOrderSave {

	public static void main(String[] args) throws DacaException {
		OrderDAO dao = new OrderDAO();
		try {
			Order order = new Order();
			order.setCreationDate(Util.getDate(2015, 02, 18));

			OrderLine orderLine1 = new OrderLine();
			orderLine1.setItem("item 1");
			orderLine1.setQuantity(2);
			orderLine1.setUnitPrice(10.0);

			OrderLine orderLine2 = new OrderLine();
			orderLine2.setItem("item 2");
			orderLine2.setQuantity(3);
			orderLine2.setUnitPrice(20.0);

			orderLine1.setOrder(order);
			orderLine2.setOrder(order);
			order.setOrderLines(new ArrayList<OrderLine>());
			order.getOrderLines().add(orderLine1);
			order.getOrderLines().add(orderLine2);

			System.out.println(order);
			System.out.println(orderLine1);
			System.out.println(orderLine2);

			dao.save(order);

			System.out.println(order);
			System.out.println(orderLine1);
			System.out.println(orderLine2);
		} finally {
			dao.close();
		}
	}

}
