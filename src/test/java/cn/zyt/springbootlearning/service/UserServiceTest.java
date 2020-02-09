package cn.zyt.springbootlearning.service;

import cn.zyt.springbootlearning.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 * Spring Boot集成测试
 * @author yitian
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserByIdTest() {
        User user = userService.getUserById(1L);
        assertNotNull(user);
    }
}