package cn.zyt.springbootlearning.domain.mybatis;

import lombok.Data;

@Data
public class Author {
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String bio;
    private String favouriteSection;

    public Author() {

    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", favouriteSection='" + favouriteSection + '\'' +
                '}';
    }
}
