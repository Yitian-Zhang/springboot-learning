package cn.zyt.springbootlearning.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias(value = "user")
public class User {
    private Long id;
    private String userName;
    private String note;
    private SexEnum sex;

    public User() {
    }

    public User(Long id, String userName, int sex, String note) {
        this.id = id;
        this.userName = userName;
        this.sex = SexEnum.getEnumById(sex);
        this.note = note;
    }

    public User(String userName, int sex, String note) {
        this.userName = userName;
        this.sex = SexEnum.getEnumById(sex);
        this.note = note;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", note='" + note + '\'' +
                ", sex=" + sex +
                '}';
    }
}
