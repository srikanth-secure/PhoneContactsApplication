package com.srikanth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "CONTACT_DETAILS")
public class ContactEntity {

	@Id
	@SequenceGenerator(sequenceName = "CONTACT_ID_SEQ", name = "C_ID_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "C_ID_SEQ", strategy = GenerationType.SEQUENCE)
	@Column(name = "CONTACT_ID")
	private Integer conId;

	@Column(length = 50, name = "CONTACT_NAME")
	private String conName;

	@Column(length = 20, name = "CONTACT_EMAIL")
	private String conEmail;

	@Column(length = 10, name = "CONTACT_NUMBER")
	private String conNumber;
}