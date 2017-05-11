package br.edu.ifpb.dac.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class Mensagens {
	
	public static void MensagemSucesso(String mensagem) {

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		flash.setRedirect(true);

		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", mensagem);
		context.addMessage(null, facesMensagem);

	}

}
