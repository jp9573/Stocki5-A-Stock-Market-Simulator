package com.csci5308.stocki5.user.funds;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.DoubleSummaryStatistics;
import java.util.Properties;

@Service
public class UserFunds implements IUserFunds {
    private static final String PROPERTIES_FILE = "config.properties";

    private int resetFundAmount;

    public UserFunds(){
        readProperties();
    }

    private void readProperties()
    {
        InputStream inputStream = null;
        try
        {
            Properties prop = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
            if (inputStream == null)
            {
                throw new FileNotFoundException();
            } else
            {
                prop.load(inputStream);
            }
            this.resetFundAmount = Integer.parseInt(prop.getProperty("user.resetfundamount"));
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                inputStream.close();
            } catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }

    @Override
    public void setResetFundAmount(int resetFundAmount) {
        this.resetFundAmount = resetFundAmount;
    }

    @Override
    public boolean resetFunds(IUser user, IUserDb userDb) {
        if (user.getFunds() < resetFundAmount) {
            user.setFunds(resetFundAmount);
            return userDb.updateUserFunds(user.getUserCode(), resetFundAmount);
        } else {
            return false;
        }
    }
}
