package cn.zyt.springbootlearning.domain.mybatis;

import lombok.Data;

@Data
public class DraftPost {
    private Integer id;
    private Integer status;

    public DraftPost() {
    }

    @Override
    public String toString() {
        return "DraftPost{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
