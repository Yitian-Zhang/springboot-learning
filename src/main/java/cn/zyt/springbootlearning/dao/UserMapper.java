package cn.zyt.springbootlearning.dao;

import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User getUserById(Long id);

    void addUser(User user);

    int insertUser(User user);

    List<User> getUserList();

    List<User> searchUser(Long userId, String userName);

    List<User> findUsers(String userName, Integer start, Integer limit);

    int updateUserById(User user);

    int deleteUser(Long id);

    @Insert("insert into t_user (user_name, sex, note) values (#{userName}, #{sex}, #{note})")
    int insertUserAnnotation(User user);

    @InsertProvider(type = UserProvider.class, method = "insertUser")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUserProvider(User user);

    @Select("select * from t_user where id = #{id}")
    User getUserByIdAnnotation(Long id);

    @Select("select * from t_user where id = #{id} and user_name = #{userName}")
    User searchUserAnnotation(@Param("id") Long userId, @Param("userName") String userName);

    @SelectProvider(type = UserProvider.class, method = "getUser")
    User getUserUsingProvider(Long id);

    @SelectProvider(type = UserProvider.class, method = "getUserList")
    List<User> getUserListAnnotation();

    @Update("update t_user set user_name=#{userName}, sex=#{sex}, note=#{note} where id=#{id}")
    int updateUserAnnotation(User user);

    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    int updateUserProvider(User user);

    @Delete("delete from t_user where id=#{id}")
    int deleteUserAnnotation(Long id);

    @DeleteProvider(type = UserProvider.class, method = "deleteUser")
    int deleteUserProvider(Long id);

    @Select("select * from t_user where user_name = #{userName}")
    List<User> getUsersByName(@Param("userName") String userName);
}
