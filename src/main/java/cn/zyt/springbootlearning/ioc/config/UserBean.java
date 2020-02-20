package cn.zyt.springbootlearning.ioc.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component("UserBean")
public class UserBean {

    @Value("1")
    private Long id;

    @Value("yitian")
    private String userName;

    @Value("1")
    private Integer sex;

    @Value("none")
    private String note;

    public UserBean() {

    }

    public UserBean(Long id, String userName, Integer sex, String note) {
        this.id = id;
        this.userName = userName;
        this.sex = sex;
        this.note = note;
    }
}
