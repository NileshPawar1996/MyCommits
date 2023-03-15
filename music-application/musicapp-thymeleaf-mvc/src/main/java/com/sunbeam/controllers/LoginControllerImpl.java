package com.sunbeam.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sunbeam.dtos.CredentialsDTO;
import com.sunbeam.entities.User;
import com.sunbeam.services.UserServiceImpl;

@Controller
public class LoginControllerImpl {
	/*
	@RequestMapping("/authenticate") // url = /authenticate
	public String login() {
		return "login"; // viewName = login
	}
	*/
	
	/*
	@RequestMapping({"/login", "/authenticate"}) // url = /authenticate or /login // req method = all req method
	public String login() {
		return "login"; // viewName = login
	}
	*/
	
	/*
	@RequestMapping(value="/authenticate", method = RequestMethod.GET) // url = /authenticate
	public String login(Model model) {
		CredentialsDTO cred = new CredentialsDTO();
		model.addAttribute("cred", cred);
		model.addAttribute("curTime", new Date());
		return "login"; // viewName = login
	}
	*/
	
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping("/authenticate")
	public ModelAndView login(Model model) {
		CredentialsDTO cred = new CredentialsDTO();
		model.addAttribute("curTime", new Date());
		return new ModelAndView("login", "cred", cred); // arg1:viewName, arg2:modelName, arg3:model/dto
	}
	
	@PostMapping("/authenticate")
	public String login(CredentialsDTO cred, Model model, HttpSession session) {
		User user = userService.findUserByEmailAndPassword(cred.getEmail(), cred.getPassword());
		if(user == null)
			return "failed"; // viewName = failed
		session.setAttribute("user", user);
		// request -- HttpServletRequest -- arg
		//request.setAttribute("loginTime", new Date());
		if(user.getName().equalsIgnoreCase("admin"))
			return "forward:manage"; // forward request to url /manage
		return "redirect:explore"; // redirect request to url /explore
	}
}




