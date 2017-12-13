package com.lakala.mini;

import com.lakala.mini.server.core.domain.User;
import com.lakala.mini.server.core.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LklMiniWebappApplication.class)
public class LklMiniWebappApplicationTests {

    @Autowired
    public IUserService userService;

    @Test
    public void testFindByUserName() {
        User user = userService.findByUserName("xiao_dingo");
        System.out.println(user);
    }

    @Test
    public void testFindByEmail() {
        User user = userService.findByEmail("1");
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setEmail("wwc0524@163.com");
        user.setPassword("ksdjfkjf");
        user.setUserName("xiao_dingo");
        user = userService.saveUser(user);
        System.out.println(user);
    }

    @Test
    public void testTest() {

        List<User> userList= userService.test("xiao_dingo");
        System.out.println(userList);
    }

}
