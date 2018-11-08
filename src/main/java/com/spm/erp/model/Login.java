package com.spm.erp.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class Login {

	@NotBlank
	@Size(min = 3, max = 15)
	private String username;

	@NotBlank
	@Size(min = 6, max = 20)
	private String password;
}
