package com.srikanth.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(ContactInfoController.class);

	@Autowired
	private ContactService contactService;

	@GetMapping(value = { "/", "loadForm" })
	public String index(Model model) {

		logger.debug("   ***   index() method execution is started for display contact form   ***");

		ContactEntity contactDtlsEntity = new ContactEntity();
		model.addAttribute("contact", contactDtlsEntity);

		logger.debug("   ***   index() method execution is ended for display contact form   ***");

		logger.info("   ***   index() method executed successfully for display contact form   ***");

		return "index";
	}

	@PostMapping("/saveContact")
	public String handleSubmitBtn(@ModelAttribute("contact") Contact c, RedirectAttributes reModel) {

		logger.info("   ***   handleSubmitBtn() method exceution is started***");

		boolean isSaved = contactService.saveContact(c);
		if (isSaved) {
			if (c.getConId() != null) {
				reModel.addFlashAttribute("succMsg", "Contact Updated Successfully");

				logger.info("   ***   in handleSubmitBtn() method contact updated***");

			} else {

				logger.info("   ***   handleSubmitBtn() method contact saved***");

				reModel.addFlashAttribute("succMsg", "Contact Saved Successfully");
			}
		} else {

			logger.info("   ***   handleSubmitBtn() method contact not saved***");

			reModel.addFlashAttribute("errMsg", "Failed to Save Contact");
		}

		logger.info("   ***   handleSubmitBtn() method exceution is finished***");

		return "redirect:/loadForm";
	}

	@GetMapping("/viewContacts")
	public String handleViewLink(Model model) {

		logger.info("   ***   handleViewLink() method exceution is started***");

		List<Contact> allContacts = contactService.getAllContacts();
		model.addAttribute("contacts", allContacts);

		logger.info("   ***   handleViewLink() method exceution is finished***");

		return "viewContacts";
	}
}