package br.edu.ifpb.mt.daca.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.collection.internal.PersistentBag;


public class Util {

	public static Date getDate(int ano, int mes, int dia) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ano);
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.DAY_OF_MONTH, dia);
		
		return cal.getTime();
	}

	public static Date removeTime(Date date) {    
	    Calendar cal = Calendar.getInstance();  
	    cal.setTime(date);  
	    cal.set(Calendar.HOUR_OF_DAY, 0);  
	    cal.set(Calendar.MINUTE, 0);  
	    cal.set(Calendar.SECOND, 0);  
	    cal.set(Calendar.MILLISECOND, 0);  
	    return cal.getTime(); 
	}
	
	/*
	 * Explicação para a necessidade deste método auxiliar para comparar as listas:
	 * https://forum.hibernate.org/viewtopic.php?f=9&t=971056
	 * https://hibernate.atlassian.net/browse/HHH-5409
	 */
	public static <E> boolean equals(List<E> l1, List<E> l2) {
		l1 = getNotPersistentBagList(l1);
		l2 = getNotPersistentBagList(l2);
		return l1.equals(l2);
	}
	
	private static <E> List<E> getNotPersistentBagList(List<E> list) {
		return list instanceof PersistentBag ? new ArrayList<E>(list) : list;
	}

	public static String safeToStringLazyCollection(Collection<?> collection) {

		if (collection == null) {
			return null;
		}

		if (org.hibernate.Hibernate.isInitialized(collection)) {
			return collection.toString();
		}

		return "<not initialized>";
	}

}
