package com.srikanth.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srikanth.entity.ContactEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Serializable> {

}