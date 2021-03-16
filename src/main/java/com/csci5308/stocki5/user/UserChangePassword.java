package com.csci5308.stocki5.user;

import org.springframework.stereotype.Service;

import javax.swing.table.TableRowSorter;

@Service
public class UserChangePassword {

    public boolean validateCurrentPassword(User user, String currentPassword){
        if(currentPassword.equals(user.getPassword())){
            return true;
        }
        return false;
    }

    public String changePassword(User user, String newPassword, String confirmNewPassword, UserDbInterface userDb){
        user.setPassword(newPassword);
        user.setConfirmPassword(confirmNewPassword);
        String result = user.validate();
        if(result.equals("valid")){
            userDb.updateUserPassword(user);
        }
        return result;
    }
}
