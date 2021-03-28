package com.csci5308.stocki5.user.authentication;

import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthentication implements AuthenticationProvider
{

	@Autowired
    UserDb userDb;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		if (null == username || null == password)
		{
			throw new BadCredentialsException("Invalid Credentials.");
		}
		User user = userDb.getUser(username);
		if (null == user.getUserCode())
		{
			throw new BadCredentialsException("Invalid Credentials.");
		}
		if (!password.equals(user.getPassword()))
		{
			throw new BadCredentialsException("Invalid Credentials.");
		}
		Authentication auth = new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
		return auth;

	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		return true;
	}

}
