package com.multi.contactsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.multi.contactsapp.domain.Contact;
import com.multi.contactsapp.service.ContactService;

@Controller
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("list.do")
	public ModelAndView getContactList() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("data",  contactService.getContactList());
		mav.setViewName("contact");
		return mav;
	}
	
	@RequestMapping(value="add.do", method=RequestMethod.POST)
	public String  insertContact(Contact c) {
		contactService.insertContact(c);
		return "redirect:list.do";
	}
	
	@RequestMapping(value="update.do", method=RequestMethod.POST)
	public ModelAndView  updateContact(Contact c) {
		contactService.updateContact(c);
		return new ModelAndView("redirect:list.do");
	}
}
