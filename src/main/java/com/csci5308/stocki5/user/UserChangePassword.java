package com.csci5308.stocki5.user;

import org.springframework.stereotype.Service;

import javax.swing.table.TableRowSorter;

@Service
public class UserChangePassword {
    private User user;

    public boolean validateCurrentPassword(String userCode,
                                           String currentPassword,
                                           UserDbInterface userDb){
        this.user = userDb.getUser(userCode);
        if(currentPassword.equals(user.getPassword())){
            return true;
        }
        return false;
    }

    public String changePassword(String newPassword,
                                  String confirmNewPassword,
                                  UserDbInterface userDb){
        this.user.setPassword(newPassword);
        this.user.setConfirmPassword(confirmNewPassword);
        String result = user.validate();
        return result;
    }
}
