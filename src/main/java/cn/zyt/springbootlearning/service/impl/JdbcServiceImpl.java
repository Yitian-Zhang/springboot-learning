package cn.zyt.springbootlearning.service.impl;

import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class JdbcServiceImpl implements JdbcService {

    @Autowired
    private DataSource dataSource;

    @Override
    public int insertUser(User user) {
        Connection connection = null;
        int result = 0;

        try {
            // 创建数据库连接
            connection = dataSource.getConnection();
            // 设置是否自动提交
            connection.setAutoCommit(false);
            // 开启事务并设置隔离级别
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            PreparedStatement statement = connection.prepareStatement(
                    "insert into t_user (user_name, sex, note) values (?, ?, ?)");
            statement.setString(1, user.getUserName());
            statement.setInt(2, user.getSex().getId());
            statement.setString(3, user.getNote());

            // 执行SQL
            result = statement.executeUpdate();
            // 提交事务
            connection.commit();

        } catch (SQLException e) {
            // 发生异常时回滚事务
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // 关闭数据库连接
            try {
                if (connection != null || !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
