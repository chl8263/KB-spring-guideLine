package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import neo.common.data.DataMap;
import service.MemberService;

@Controller
@RequestMapping(value = "/admin")
public class MemberController {
	
	@Autowired
	public MemberService memberService;
	
	@RequestMapping(value = "/addMember" , method = RequestMethod.GET)
	public ModelAndView addMember() {
		
		return new ModelAndView("addMember");
	}
	
	@RequestMapping(value = "/addMember" , method = RequestMethod.POST)
	public String addMemberPost(HttpServletRequest request, Model model ) {
		
/*		if(result.hasErrors()) {
			System.out.println("Form data has some error");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "addProduct";
		}
*/		
		//productService.addProduct(product);

		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String position = request.getParameter("position");
		
		System.out.println(name + " " + sex + " " + age + " " + position + " ");
		
		DataMap map = new DataMap();
		map.put("name", name);
		map.put("sex", sex);
		map.put("age", age);
		map.put("position", position);

		memberService.addMember(map);
		
		return "redirect:/";
		
		
		//return new ModelAndView("addMember","map",map);
	}
	
	@RequestMapping(value = "/updateMember/{id}" , method = RequestMethod.GET)
	public ModelAndView updateMember(@PathVariable int id) {
		
		DataMap map = memberService.getMemberById(id);
		
		return new ModelAndView("updateMember","map",map);
	}
	
	@RequestMapping(value = "/updateMember" , method = RequestMethod.POST)
	public String updateMemberPOST(HttpServletRequest request, Model model) {
		
		int memberId = Integer.parseInt(request.getParameter("memberId"));
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String position = request.getParameter("position");
		
		DataMap map = new DataMap();
		map.put("memberId", memberId);
		map.put("name", name);
		map.put("sex", sex);
		map.put("age", age);
		map.put("position", position);
		
		System.out.println(map);
		
		memberService.updateMember(map);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/deleteMember/{id}", method = RequestMethod.GET)
	public String deleteMember(@PathVariable int id) {
		memberService.deleteMember(id);
		return "redirect:/";
	}
	
}
