package com.uifuture.chapter17;

import com.uifuture.chapter17.service.IUsersService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class UsersServiceImplTest extends BaseTest {

    @Autowired
    private IUsersService usersService;

    @Test
    public void updateMoneyByUsername() throws IOException {
        usersService.updateMoneyByUsername(100, "a");
    }


}
