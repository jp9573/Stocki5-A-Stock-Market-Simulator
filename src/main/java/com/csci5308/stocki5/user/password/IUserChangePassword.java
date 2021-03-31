package com.csci5308.stocki5.user.password;

import com.csci5308.stocki5.user.IUserDb;
import com.csci5308.stocki5.user.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserChangePassword {
    public String getPasswordValidityMessage();

    public boolean validateCurrentPassword(User user, String currentPassword);

    public boolean changePassword(User user, String newPassword, String confirmNewPassword, IUserDb userDb);

}
