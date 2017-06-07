package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.CreditCardDAO;
import br.edu.ifpb.mt.daca.entities.enumeration.CreditCard;


public class MainCreditCardDeleteAll {

	public static void main(String[] args) throws DacaException {
		CreditCardDAO dao = new CreditCardDAO();
		try {
			List<CreditCard> creditCards = dao.getAll();
			for (CreditCard creditCard : creditCards) {
				dao.delete(creditCard);
			}
		} finally {
			dao.close();
		}
	}
	
}
