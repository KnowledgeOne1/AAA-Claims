package com.aaa.service.claim.model;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Claim {

	@Size(max = 40, message = "Claim id too long")
	private String claimId;
	
	@Size(max = 40, message = "Policy id too long")
	private String policyId;

	@Range(min=1, max=3, message = "Claim type must be between 1 and 3")
	private Integer type = 1;

	@Range(min=1, max=3, message = "Claim status must be between 1 and 3")
	private Integer status = 1;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateOpen;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateClosed;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateLoss;
	
	private Boolean flag;
	
	@Valid
	private Set<Document> documents;
	
	@Valid
	private Set<User> users;
	
}
