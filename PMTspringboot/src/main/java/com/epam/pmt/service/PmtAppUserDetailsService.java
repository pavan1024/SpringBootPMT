package com.epam.pmt.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.pmt.entities.User;
import com.epam.pmt.repo.AuthGroupRepository;
import com.epam.pmt.repo.UserRepository;
import com.epam.pmt.dto.UserPrincipal;
import com.epam.pmt.entities.AuthGroup;

@Service
public class PmtAppUserDetailsService implements UserDetailsService {

	private final UserRepository repository;

	private final AuthGroupRepository authGroupRepository;

	public PmtAppUserDetailsService(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
		super();
		this.repository = userRepository;
		this.authGroupRepository = authGroupRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Cannot find username : " + username);
		}

		List<AuthGroup> authGroups = authGroupRepository.findByUsername(username);

		return new UserPrincipal(user, authGroups);
	}

}
