package cn.zyt.springbootlearning.domain.mybatis;

import lombok.Data;

import java.util.List;

@Data
public class Post {
    private Integer id;
    private String subject;
    private Author author;
    private List<Comment> comments;
    private List<Tag> tags;
    private Integer draft;
    private String content;

    public Post() {
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", author=" + author +
                ", comments=" + comments +
                ", tags=" + tags +
                ", draft=" + draft +
                ", content='" + content + '\'' +
                '}';
    }
}
