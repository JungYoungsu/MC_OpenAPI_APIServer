package com.multi.contactsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thinker.openapi.ApiKeyProcessor;
import org.thinker.openapi.ApiKeyVO;

@Controller
public class KeyProcessController {
	
	@Autowired
	private ApiKeyProcessor keyProcessor;
	
	@RequestMapping(method=RequestMethod.GET, value="/key/request")
	public String getForm(){		
		return "keygen";
	}
	
	@RequestMapping(value="/key/makeKey", method=RequestMethod.POST)
	public ModelAndView makeApiKey(ApiKeyVO apiKeyVO)throws Exception{

		String keyValue = keyProcessor.requestNewAPIKey(apiKeyVO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("apikey", keyValue);
		mav.setViewName("keyresult");
		
		return mav;
	}
}
