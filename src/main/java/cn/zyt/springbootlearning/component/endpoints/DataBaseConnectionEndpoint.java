package cn.zyt.springbootlearning.component.endpoints;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Spring HTTP监测端点
 * 用于测试数据库的连接是否正常
 *
 * @author yitian
 */
@Component
@Endpoint(id = "dbcheck", enableByDefault = true)
public class DataBaseConnectionEndpoint {

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    /**
     * 一个端点只能存在一个ReadOperation标注的方法，表示一个HTTP GET请求
     */
    @ReadOperation
    public Map<String, Object> check() {
        Connection connection = null;
        Map<String, Object> resultMap = new HashMap<>();

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(url, username, password);
            resultMap.put("success", true);
            resultMap.put("message", "数据库连接成功！");

        } catch (ClassNotFoundException | SQLException e) {
            resultMap.put("success", false);
            resultMap.put("message", e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultMap;
    }
}
