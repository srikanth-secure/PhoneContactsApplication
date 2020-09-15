package com.srikanth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srikanth.entity.ContactEntity;
import com.srikanth.pojo.Contact;
import com.srikanth.repo.ContactRepository;
import com.srikanth.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepo;

	@Override
	public boolean saveContact(Contact c) {
		ContactEntity entity = new ContactEntity();
		BeanUtils.copyProperties(c, entity);
		ContactEntity savedEntity = contactRepo.save(entity);
		return savedEntity.getConId() != null;
	}

	@Override
	public List<Contact> getAllContacts() {
		List<Contact> contactList = new ArrayList<Contact>();
		List<ContactEntity> entitiesList = contactRepo.findAll();
		entitiesList.forEach(entity -> {
			Contact c = new Contact();
			BeanUtils.copyProperties(entity, c);
			contactList.add(c);
		});
		return contactList;
	}

	@Override
	public Contact getContactById(Integer conId) {
		Optional<ContactEntity> optional = contactRepo.findById(conId);
		if (optional.isPresent()) {
			ContactEntity entity = optional.get();
			Contact con = new Contact();
			BeanUtils.copyProperties(entity, con);
			return con;
		}
		return null;
	}

	@Override
	public boolean deleteContact(Integer conId) {
		contactRepo.deleteById(conId);
		return true;
	}
}