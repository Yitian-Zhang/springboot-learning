package cn.zyt.springbootlearning.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Alias(value = "user")
public class User implements Serializable {

    // 开启Spring Redis Cache时，加入序列化
    private static final long serialVersionUID = -4947062488310146862L;

    private Long id;

    @NotNull(message = "用户名不能为空")
    private String userName;

    @NotNull(message = "备注不能为空")
    private String note;

    @NotNull(message = "性别不能为空")
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
