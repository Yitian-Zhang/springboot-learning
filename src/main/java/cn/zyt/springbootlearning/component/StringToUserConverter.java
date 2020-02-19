package cn.zyt.springbootlearning.component;

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
        String userName = null;
        Integer sex = null;
        String note = null;

        String[] strArr = userStr.split("-");
        if (strArr.length == 3) {
            userName = strArr[0];
            if (!strArr[1].equals("")) {
                sex = Integer.parseInt(strArr[1]);
            }
            note = strArr[2];
        } else if (strArr.length == 2) {
            userName = strArr[0];
            if (!strArr[1].equals("")) {
                sex = Integer.parseInt(strArr[1]);
            }
        }
        User user = new User(userName, sex, note);
        return user;
    }
}
