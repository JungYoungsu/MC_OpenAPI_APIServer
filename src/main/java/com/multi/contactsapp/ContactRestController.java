package com.multi.contactsapp;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thinker.openapi.ApiKeyProcessor;

import com.multi.contactsapp.domain.ContactList;
import com.multi.contactsapp.service.ContactService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "contacts")
public class ContactRestController {
	@Autowired
	private ContactService contactService;
	@Autowired
	private ApiKeyProcessor keyProcessor;

	@RequestMapping(method = RequestMethod.GET)
	public ContactList getContactList(@RequestParam(value = "key", required = false) String apikey,
			HttpServletRequest request) throws UnsupportedEncodingException {
		String origin = (String) request.getHeader("Origin");
		keyProcessor.checkApiKey(origin, apikey);
		return contactService.getContactList();
	}
}
