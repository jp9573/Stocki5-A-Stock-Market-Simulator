package com.csci5308.stocki5.user.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;

@Service
public class UserAuthentication implements AuthenticationProvider
{
	private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid Credentials.";

	private static AuthenticationProvider uniqueInstance = null;

	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	IUserDb iUserDb = userFactory.createUserDb();

	private UserAuthentication()
	{

	}

	public static AuthenticationProvider instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new UserAuthentication();
		}
		return uniqueInstance;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		if (null == username || null == password)
		{
			throw new BadCredentialsException(INVALID_CREDENTIALS_MESSAGE);
		}
		UserDetails userDetails = (User) iUserDb.getUser(username);
		IUser user = iUserDb.getUser(username);
		if (null == user.getUserCode())
		{
			throw new BadCredentialsException(INVALID_CREDENTIALS_MESSAGE);
		}
		if (password.equals(user.getPassword()))
		{
			Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
			return authenticationToken;
		} else
		{
			throw new BadCredentialsException(INVALID_CREDENTIALS_MESSAGE);
		}
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		return true;
	}
}
