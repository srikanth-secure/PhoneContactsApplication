package com.srikanth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.srikanth.entity.ContactEntity;
import com.srikanth.pojo.Contact;
import com.srikanth.service.ContactService;

@Controller
public class ContactInfoController {

	@Autowired
	private ContactService contactService;

	@GetMapping(value = { "/", "loadForm" })
	public String index(Model model) {
		ContactEntity contactDtlsEntity = new ContactEntity();
		model.addAttribute("contact", contactDtlsEntity);
		return "index";
	}

	@PostMapping("/saveContact")
	public String handleSubmitBtn(@ModelAttribute("contact") Contact c, RedirectAttributes reModel) {
		boolean isSaved = contactService.saveContact(c);
		if (isSaved) {
			if (c.getConId() != null) {
				reModel.addFlashAttribute("succMsg", "Contact Updated Successfully");
			} else {
				reModel.addFlashAttribute("succMsg", "Contact Saved Successfully");
			}
		} else {
			reModel.addFlashAttribute("errMsg", "Failed to Save Contact");
		}
		return "redirect:/loadForm";
	}

	@GetMapping("/viewContacts")
	public String handleViewLink(Model model) {
		List<Contact> allContacts = contactService.getAllContacts();
		model.addAttribute("contacts", allContacts);
		return "viewContacts";
	}
}