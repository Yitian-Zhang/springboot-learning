package cn.zyt.springbootlearning.domain.mybatis;

import lombok.Data;

@Data
public class Tag {
    private Integer id;
    private String content;

    public Tag() {
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
