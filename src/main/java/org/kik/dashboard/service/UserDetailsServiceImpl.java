package org.kik.dashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.kik.dashboard.dao.UserRepository;
import org.kik.dashboard.model.security.LocalUser;
import org.kik.dashboard.model.security.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userdao;

	public UserDetailsServiceImpl() {
		// Empty constructor
	}

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {

		org.kik.dashboard.model.User userEntity = userdao
				.findFirstByUsername(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("username not found");
		}

		return buildUserFromUserEntity(userEntity);

	}

	private User buildUserFromUserEntity(
			final org.kik.dashboard.model.User userEntity) {

		// convert model user to spring security user
		long id = userEntity.getId();
		String username = userEntity.getUsername();
		String password = userEntity.getPassword();

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new UserAuthority(userEntity.getRole()));

		return new LocalUser(id, username, password, authorities);
	}
}