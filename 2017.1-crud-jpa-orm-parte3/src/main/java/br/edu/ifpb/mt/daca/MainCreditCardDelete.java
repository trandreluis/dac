package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.CreditCardDAO;
import br.edu.ifpb.mt.daca.entities.enumeration.CreditCard;
import br.edu.ifpb.mt.daca.entities.enumeration.CreditCardType;
import br.edu.ifpb.mt.daca.util.Util;

public class MainCreditCardDelete {

	public static void main(String[] args) throws DacaException {
		CreditCardDAO dao = new CreditCardDAO();
		try {
			// Primeiro salvar
			CreditCard creditCard = new CreditCard();

			creditCard.setNumberCC("" + creditCard.getRandomCC());
			creditCard.setCreditCardType(CreditCardType.VISA);
			creditCard.setExpiryDate(Util.getDate(2016, 02, 10));
			creditCard.setControlNumber(123);

			dao.save(creditCard);

			System.out.println(dao.getAll().size());

			// Depois apagar

			dao.delete(creditCard);

			System.out.println(dao.getAll().size());
		} finally {
			dao.close();
		}
	}

}
