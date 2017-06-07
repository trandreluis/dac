package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.CreditCardDAO;
import br.edu.ifpb.mt.daca.entities.enumeration.CreditCard;
import br.edu.ifpb.mt.daca.entities.enumeration.CreditCardType;
import br.edu.ifpb.mt.daca.util.Util;



public class MainCreditCardSave {

	public static void main(String[] args) throws DacaException {
		CreditCardDAO dao = new CreditCardDAO();
		try {
			CreditCard creditCard = new CreditCard();
			
			creditCard.setNumberCC("" + creditCard.getRandomCC());
			creditCard.setCreditCardType(CreditCardType.VISA);
			creditCard.setExpiryDate(Util.getDate(2016, 02, 10));
			creditCard.setControlNumber(123);
			
			System.out.println(creditCard);

			dao.save(creditCard);

			System.out.println(creditCard);
		} finally {
			dao.close();
		}
	}

}
