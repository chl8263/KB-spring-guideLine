package controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessageController {

	@RequestMapping(value = "/i18n.do", method = RequestMethod.GET) 
	public String i18n(Locale locale, HttpServletRequest request, Model model) {
	
		return "redirect:/paging/setDataTable?length=10&currentPage=1";
	}

		
}
