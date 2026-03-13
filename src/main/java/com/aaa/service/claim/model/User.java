package com.aaa.service.claim.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class User {

	@NotEmpty
    private String userId;
    private String firstName;
    private String lastName;
	
}
