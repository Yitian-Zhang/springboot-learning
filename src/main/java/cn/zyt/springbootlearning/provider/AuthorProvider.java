package cn.zyt.springbootlearning.provider;

import org.apache.ibatis.jdbc.SQL;

public class AuthorProvider {

    public String getAuthor(Integer id) {
        return new SQL() {
            {
                SELECT("*");
                FROM("tb_author");
                WHERE("author_id=#{id}");
            }
        }.toString();
    }
}
