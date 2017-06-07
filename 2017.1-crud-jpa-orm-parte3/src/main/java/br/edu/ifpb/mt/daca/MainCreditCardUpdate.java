package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.CreditCardDAO;
import br.edu.ifpb.mt.daca.entities.enumeration.CreditCard;
import br.edu.ifpb.mt.daca.entities.enumeration.CreditCardType;
import br.edu.ifpb.mt.daca.util.Util;




public class MainCreditCardUpdate {
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

			System.out.println(creditCard);
			
			// Depois atualizar
			creditCard.setControlNumber(124);
			
			dao.update(creditCard);
			
			System.out.println(creditCard);
		} finally {
			dao.close();
		}
	}

}
