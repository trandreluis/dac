package br.edu.ifpb.mt.daca;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.mt.daca.dao.bidirecional.OrderDAO;
import br.edu.ifpb.mt.daca.entities.bidirecional.Order;
import br.edu.ifpb.mt.daca.entities.bidirecional.OrderLine;
import br.edu.ifpb.mt.daca.util.Util;

public class MainBiOrderGetByID {

	public static void main(String[] args) throws DacaException {
		OrderDAO dao = new OrderDAO();
		try {
			// Primeiro salvar
			Order order = new Order();

			order.setCreationDate(Util.getDate(2015, 02, 18));
			List<OrderLine> orderLines = new ArrayList<OrderLine>();

			OrderLine orderLine1 = new OrderLine();
			orderLine1.setItem("item 1");
			orderLine1.setQuantity(2);
			orderLine1.setUnitPrice(10.0);
			orderLine1.setOrder(order);
			orderLines.add(orderLine1);

			OrderLine orderLine2 = new OrderLine();
			orderLine2.setItem("item 2");
			orderLine2.setQuantity(3);
			orderLine2.setUnitPrice(20.0);
			orderLine2.setOrder(order);
			orderLines.add(orderLine2);

			order.setOrderLines(orderLines);
			
			dao.save(order);

			// Depois recuperar pelo identificador
			Order resultado = dao.getByID(order.getId());

			System.out.println(order.equals(resultado));
		} finally {
			dao.close();
		}
	}

}
