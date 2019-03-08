package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import neo.common.data.DataMap;
import service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	public MemberService memberService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {

		testOracle();

		List<DataMap> list = memberService.findAllMember();

		System.out.println(list);

		return new ModelAndView("home","list",list);
	}

	private void testOracle() {
		String DRIVER = "oracle.jdbc.driver.OracleDriver";

		String URL = "jdbc:oracle:thin:@localhost:1521/orcl";

		String USER = "spring";

		String PW = "930324";

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try (Connection con = (Connection) DriverManager.getConnection(URL, USER, PW)) {

			System.out.println("success access to DB");
			System.out.println(con);

		} catch (Exception e) {
			System.out.println("holy shit DB");
			System.err.println(e);

		}
	}

}
