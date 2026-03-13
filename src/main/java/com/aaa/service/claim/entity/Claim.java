package com.aaa.service.claim.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "findClaimById", query = "select c from Claim c where claimId = :id"),
	@NamedQuery(name = "findClaimsByUserId", query = "SELECT DISTINCT c FROM Claim c JOIN c.users u WHERE u.userId = :userId"),
	@NamedQuery(name = "findDuplicateClaims", query = "SELECT c FROM Claim c JOIN c.users u WHERE (c.flag is NULL OR c.flag = false) AND u.userId in "
			+ "(SELECT u.userId FROM User u GROUP BY u.userId HAVING COUNT(u) > 2)")})
@Table(name = "claim")
public class Claim implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "claim_id", unique = true, nullable = false)
	private String claimId;

	@Column(name = "policy_id")
	private String policyId;

	@Column(name = "claim_type")
	private Integer type;

	@Column(name = "status")
	private Integer status;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "open_date", columnDefinition = "DATETIME")
	private LocalDateTime dateOpen;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "closed_date", columnDefinition = "DATETIME")
	private LocalDateTime dateClosed;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "date_of_loss", columnDefinition = "DATETIME")
	private LocalDateTime dateLoss;

	@Column(name = "flag")
	private Boolean flag;

	@Column(name = "created_date", columnDefinition = "varchar(100) default '01-JAN-2026'")
	private String createdDate = "2001-01-01";

	@Column(name = "modified_date", columnDefinition = "varchar(100) default '01-JAN-2026'")
	private String modifiedDate = "2001-01-01";

	@Column(name = "created_by", nullable = false, columnDefinition = "varchar(100) default 'One'")
	private String createdBy = "";

	@Column(name = "modified_by", columnDefinition = "varchar(100) default 'One'")
	private String modifiedBy = "";

	@OneToMany(mappedBy = "claim", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private Set<Document> documents;

	@JsonManagedReference // Helps Jackson manage bidirectional fields
	@ManyToMany(mappedBy = "claim", cascade = { CascadeType.ALL }) // Refers to the 'claims' field in your User entity
	private Set<User> users = new HashSet<>();

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getDateOpen() {
		return dateOpen;
	}

	public void setDateOpen(LocalDateTime dateOpen) {
		this.dateOpen = dateOpen;
	}

	public LocalDateTime getDateClosed() {
		return dateClosed;
	}

	public void setDateClosed(LocalDateTime dateClosed) {
		this.dateClosed = dateClosed;
	}

	public LocalDateTime getDateLoss() {
		return dateLoss;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public void setDateLoss(LocalDateTime dateLoss) {
		this.dateLoss = dateLoss;
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

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
