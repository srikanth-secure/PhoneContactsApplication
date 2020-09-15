package com.srikanth.controller;

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

	@Autowired
	private ContactService contactService;

	public String handleAddContactHyperLink(Model model) {
		return null;
	}

	@GetMapping("/editContact")
	public String editContact(@RequestParam("conId") Integer conId, Model model) {
		Contact contact = contactService.getContactById(conId);
		model.addAttribute("contact", contact);
		return "index";
	}

	@GetMapping("/deleteContact")
	public String deleteContact(@RequestParam("conId") Integer conId, RedirectAttributes reModel) {
		boolean isDeleted = contactService.deleteContact(conId);
		if (isDeleted) {
			reModel.addFlashAttribute("succMsg", "Contact Deleted Successfully");
		} else {
			reModel.addFlashAttribute("errMsg", "Failed to Delete Contact");
		}
		return "redirect:viewContacts";
	}
}