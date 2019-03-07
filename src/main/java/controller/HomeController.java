package controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		testOracle();
		
		return "home";
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
