package com.srikanth.service;

import java.util.List;

import com.srikanth.pojo.Contact;

public interface ContactService {

	public boolean saveContact(Contact c);

	public List<Contact> getAllContacts();

	public Contact getContactById(Integer conId);

	public boolean deleteContact(Integer conId);
}