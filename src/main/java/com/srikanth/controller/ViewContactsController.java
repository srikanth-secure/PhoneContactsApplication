package com.srikanth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.srikanth.pojo.Contact;
import com.srikanth.service.ContactService;

@Controller
public class ViewContactsController {

	private static final Logger logger = LoggerFactory.getLogger(ContactInfoController.class);

	@Autowired
	private ContactService contactService;

	@GetMapping("/editContact")
	public String editContact(@RequestParam("conId") Integer conId, Model model) {

		logger.info("   ***   editContact() method exceution is started***");

		Contact contact = contactService.getContactById(conId);
		model.addAttribute("contact", contact);

		logger.info("   ***   editContact() method exceution is finished***");

		return "index";
	}

	@GetMapping("/deleteContact")
	public String deleteContact(@RequestParam("conId") Integer conId, RedirectAttributes reModel) {

		logger.info("   ***   deleteContact() method exceution is started***");

		boolean isDeleted = contactService.deleteContact(conId);
		if (isDeleted) {

			logger.info("   ***   in deleteContact() method, contact deleted Successfully***");

			reModel.addFlashAttribute("succMsg", "Contact Deleted Successfully");
		} else {

			logger.info("   ***   in deleteContact() method, contact deletion failed***");

			reModel.addFlashAttribute("errMsg", "Failed to Delete Contact");
		}

		logger.info("   ***   deleteContact() method exceution is finished***");

		return "redirect:viewContacts";
	}
}