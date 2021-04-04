package com.csci5308.stocki5.user.authentication;

import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthentication implements AuthenticationProvider {
    private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid Credentials.";
    UserAbstractFactory userFactory = UserAbstractFactory.instance();
    IUserDb userDb = userFactory.createUserDb();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        if (null == username || null == password) {
            throw new BadCredentialsException(INVALID_CREDENTIALS_MESSAGE);
        }
        User user = (User) userDb.getUser(username);
        if (null == user.getUserCode()) {
            throw new BadCredentialsException(INVALID_CREDENTIALS_MESSAGE);
        }
        if (password.equals(user.getPassword())) {
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
            return authenticationToken;
        } else {
            throw new BadCredentialsException(INVALID_CREDENTIALS_MESSAGE);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
