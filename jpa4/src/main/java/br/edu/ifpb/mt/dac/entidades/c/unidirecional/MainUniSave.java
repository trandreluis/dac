package br.edu.ifpb.mt.dac.entidades.c.unidirecional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.edu.ifpb.mt.dac.dao.c.unidirecional.CelularDAO;

public class MainUniSave {

	public static void main(String[] args) {
		CelularDAO dao = new CelularDAO();
		try {
			Celular celular = new Celular();

			celular.setCodigoPais(55);
			celular.setDdd(87);
			celular.setNumero(123456789);

			Chamada chamada = new Chamada();

			chamada.setDuracao(10);
			chamada.setHoraInicio(new Date());

			Chamada chamada2 = new Chamada();

			chamada2.setDuracao(10);
			chamada2.setHoraInicio(new Date());

			Chamada chamada3 = new Chamada();

			chamada3.setDuracao(10);
			chamada3.setHoraInicio(new Date());

			List<Chamada> chamadas = Arrays.asList(chamada, chamada2, chamada3);

			celular.setChamadas(chamadas);

			System.out.println(celular);
			System.out.println(chamadas);

			dao.save(celular);

			System.out.println(celular);
			System.out.println(chamadas);

		} finally {
			dao.close();
		}
	}

}