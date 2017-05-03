package br.edu.ifpb.mt.daca.util;

import java.util.Calendar;
import java.util.Date;

public class Util {

	public static Date getDate(int ano, int mes, int dia) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ano);
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.DAY_OF_MONTH, dia);

		return cal.getTime();
	}

}
