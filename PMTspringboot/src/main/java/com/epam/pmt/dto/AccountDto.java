package com.epam.pmt.dto;

import com.epam.pmt.entities.Master;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class AccountDto {

	@Getter
	@Setter
	private String url;

	@Getter
	@Setter
	private String username;

	@Getter
	@Setter
	private String password;

	@Getter
	@Setter
	private String groupname;

	@Getter
	@Setter
	private Master master;

}
