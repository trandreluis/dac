package br.edu.ifpb.mt.daca;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/hello", "/"})
public class HelloWorldController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getHorario(ModelMap model) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String horario = sdf.format(new Date());
		model.addAttribute("horario", horario);
		
		return "hello";
	}

}
