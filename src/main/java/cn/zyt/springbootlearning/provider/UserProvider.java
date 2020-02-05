package cn.zyt.springbootlearning.provider;

import cn.zyt.springbootlearning.domain.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * User类的查询Provider
 *
 * @author yitian
 */
public class UserProvider {

    private final static String TABLE_NAME = "t_user";

    public String getUser(Long id) {
        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
                if (id != null) {
                    WHERE("id=#{id}");
                }
                ORDER_BY("id");
            }
        }.toString();
    }

    public String getUserList() {
        SQL sql = new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
            }
        };
        System.out.println("getUserList sql: " + sql.toString());
        return sql.toString();
    }

    /**
     * 使用Provider的方式插入User对象，这里并没用传递相应的useName，sex和note参数，mybatis会从User对象中根据对应的名称进行解析
     * 所以该方法要不要参数其实都可以
     */
    public String insertUser() {
        return new SQL() {
            {
                INSERT_INTO(TABLE_NAME)
                        .INTO_COLUMNS("user_name", "sex", "note")
                        .INTO_VALUES("#{userName}", "#{sex}", "#{note}");
            }
        }.toString();
    }

    /**
     * Update方法与上述的insert方法类似，但如果其中需要对传递的User对象进行验证，
     * 则需要带上User对象作为参数
     */
    public String updateUser(User user) {
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                if (user.getUserName() != null) {
                    SET("user_name=#{userName}");
                }
                if (user.getSex() != null) {
                    SET("sex=#{sex}");
                }
                if (user.getNote() != null) {
                    SET("note=#{note}");
                }
                WHERE("id=#{id}");
            }
        }.toString();
    }

    public String deleteUser(Long id) {
        return new SQL() {
            {
                DELETE_FROM(TABLE_NAME)
                        .WHERE("id=#{id}");
            }
        }.toString();
    }

}
