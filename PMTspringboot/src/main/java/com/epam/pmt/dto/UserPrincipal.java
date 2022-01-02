package com.epam.pmt.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.epam.pmt.entities.AuthGroup;
import com.epam.pmt.entities.User;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;
	private List<AuthGroup> authGroups;

	public UserPrincipal(User user, List<AuthGroup> authGroups) {
		super();
		this.user = user;
		this.authGroups = authGroups;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<AuthGroup> getAuthGroups() {
		return authGroups;
	}

	public void setAuthGroups(List<AuthGroup> authGroups) {
		this.authGroups = authGroups;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Optional<List<AuthGroup>> optionalAuthGroups = Optional.of(authGroups);

		Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

		if (optionalAuthGroups.isPresent()) {
			authGroups.forEach(group -> {
				grantedAuthorities.add(new SimpleGrantedAuthority(group.getAuthGroup()));
			});
		}
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
