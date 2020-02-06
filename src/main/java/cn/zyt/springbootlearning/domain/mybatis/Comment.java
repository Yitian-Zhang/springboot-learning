package cn.zyt.springbootlearning.domain.mybatis;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private String content;

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
