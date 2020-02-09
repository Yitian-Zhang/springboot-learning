package cn.zyt.springbootlearning.dao;

import cn.zyt.springbootlearning.domain.mybatis.Author;
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
public class AuthorMapperTest {

    @Autowired
    private AuthorMapper authorMapper;

    @Test
    public void getAuthorTest() {
        Author author = authorMapper.getAuthor(2);

        System.out.println(author);
        assertNotNull(author);
    }
}