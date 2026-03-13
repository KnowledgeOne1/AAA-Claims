package com.aaa.service.claim.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="claim_type_ref")
public class ClaimTypeRef implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "type", unique = true, nullable = false)
	private Integer type;

	@Column(name = "value", unique = true, nullable = false)
	private String value;

	@Column(name = "created_date", columnDefinition = "varchar(100) default '15-JUL-2014'")
	private String createdDate = "2001-01-01";

	@Column(name = "modified_date", columnDefinition = "varchar(100) default '15-JUL-2014'")
	private String modifiedDate = "2001-01-01";

	@Column(name = "created_by", nullable = false, columnDefinition = "varchar(100) default 'One'")
	private String createdBy = "";

	@Column(name = "modified_by", columnDefinition = "varchar(100) default 'One'")
	private String modifiedBy = "";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
