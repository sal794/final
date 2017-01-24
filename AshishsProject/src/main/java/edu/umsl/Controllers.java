package edu.umsl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Controllers {

	@RequestMapping(value = "/new_user", method = RequestMethod.GET)
	public ModelAndView viewLogin() {
		ModelAndView mav = new ModelAndView("create_new_page");

		return mav;
	}

	@RequestMapping(value = "/new_user", method = RequestMethod.POST)
	public ModelAndView checkLogin(@RequestParam("username") String user, @RequestParam("password") String pass) {
		ModelAndView mav = new ModelAndView("invalid");

		UserManager man = new UserManager();
		boolean correct = man.checkLogin(user, pass);
		if (correct) {
			mav = new ModelAndView("user_landing");
		} else {
			mav = new ModelAndView("invalid");
		}

		mav.addObject("name", user);
		return mav;
	}

	@RequestMapping(value = "/new_user_sucess", method = RequestMethod.POST)
	public ModelAndView viewLoginSuccess(@RequestParam("username") String name,
			@RequestParam("password") String password, @RequestParam("fname") String fName,
			@RequestParam("lname") String lName) {
		ModelAndView mav = new ModelAndView("new_user_success_page");

		UserManager man = new UserManager();
		man.addUser(name, password, fName, lName);

		mav.addObject("name", name);

		return mav;

	}

}
