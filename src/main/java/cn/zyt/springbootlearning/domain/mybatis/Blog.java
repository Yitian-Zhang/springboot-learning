package cn.zyt.springbootlearning.domain.mybatis;

import lombok.Data;

import java.util.List;

@Data
public class Blog {
    private Integer id;
    private String title;
    private Author author;
    private List<Post> posts;

    public Blog() {
    }

    public Blog(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", posts=" + posts +
                '}';
    }
}
