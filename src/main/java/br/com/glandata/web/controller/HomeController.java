package br.com.glandata.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.glandata.web.session.NavigationSession;

@Controller
@RequestMapping()
public class HomeController {

	@Autowired
	private NavigationSession navigationSession;
	
	private static String codigoPagina = "HS-001";

	@GetMapping()
	public ModelAndView start() {
		ModelAndView mav = new ModelAndView("home/pages-starter");
		mav.addObject("codigoPagina", codigoPagina);

		return mav;
	}

	@GetMapping("erro404")
	public ModelAndView erro404() {
		return new ModelAndView("home/pages-404");
	}

	@GetMapping("acessoNegado")
	public ModelAndView erro403() {
		return new ModelAndView("home/pages-403");
	}

	@GetMapping({ "login", "logout" })
	public ModelAndView login() {
		return new ModelAndView("home/login");
	}
	
	@GetMapping("darkMode")
	public ModelAndView darkMode() {
		navigationSession.setDarkMode(!navigationSession.getDarkMode());
		
		return new ModelAndView("redirect:/");
	}

	@GetMapping("menuHide")
	public ModelAndView menuHide() {
		navigationSession.setMenuHide(!navigationSession.getMenuHide());
		
		return new ModelAndView("redirect:/");
	}
	
}
