package com.csci5308.stocki5.user.password;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import org.springframework.stereotype.Service;

@Service
public interface IUserChangePassword {
    public String getPasswordValidityMessage();

    public boolean validateCurrentPassword(IUser user, String currentPassword);

    public boolean changePassword(IUser user, String newPassword, String confirmNewPassword, IUserDb userDb);
}
