package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/ex")
public class LayoutController {

	@RequestMapping(value = "/buttons")
	public ModelAndView buttons() {
		return new ModelAndView("ex_buttons");
		
	}
	@RequestMapping(value = "/cards")
	public ModelAndView cards() {
		return new ModelAndView("ex_cards");
		
	}
	@RequestMapping(value = "/colors")
	public ModelAndView colors() {
		return new ModelAndView("ex_colors");
		
	}
	@RequestMapping(value = "/borders")
	public ModelAndView borders() {
		return new ModelAndView("ex_borders");
		
	}
	@RequestMapping(value = "/animations")
	public ModelAndView animations() {
		return new ModelAndView("ex_animations");
		
	}
	@RequestMapping(value = "/other")
	public ModelAndView other() {
		return new ModelAndView("ex_other");
		
	}
	@RequestMapping(value = "/login")
	public ModelAndView login() {
		return new ModelAndView("ex_login");
		
	}
	@RequestMapping(value = "/register")
	public ModelAndView register() {
		return new ModelAndView("ex_register");
		
	}
	@RequestMapping(value = "/forgotPassword")
	public ModelAndView forgotPassword() {
		return new ModelAndView("ex_forgotPassword");
		
	}
	
	@RequestMapping(value = "/blankPage")
	public ModelAndView blankPage() {
		return new ModelAndView("ex_blankPage");
		
	}
	@RequestMapping(value = "/charts")
	public ModelAndView charts() {
		return new ModelAndView("ex_charts");
		
	}
	@RequestMapping(value = "/tables")
	public ModelAndView tables() {
		return new ModelAndView("ex_tables");
		
	}
	
	
	
}
