package cn.zyt.springbootlearning.config;

import cn.zyt.springbootlearning.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 自定义参数转换器，将HTTP输入参数userName-sex-note参数转化为
 * User(userName, sex, note) POJO实体对象
 *
 * @author yitian
 */
@Component
public class StringToUserConverter implements Converter<String, User> {
    @Override
    public User convert(String userStr) {
        String[] strArr = userStr.split("-");
        String userName = strArr[0];
        Integer sex = Integer.parseInt(strArr[1]);
        String note = strArr[2];

        User user = new User(userName, sex, note);
        return user;
    }
}
