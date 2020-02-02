package cn.zyt.springbootlearning.component;

import cn.zyt.springbootlearning.domain.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author yitian
 */
public class UserValidator implements Validator {
    /**
     * 这里的自定义验证器仅针对User对象进行验证
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    /**
     * 验证逻辑
     */
    @Override
    public void validate(Object o, Errors errors) {
        if (o == null) {
            errors.rejectValue("", null, "用户不能为空");
            return;
        }
        User user = (User) o;
        if (StringUtils.isEmpty(user.getUserName())) {
            errors.rejectValue("userName", null, "用户名不能为空");
        }
    }
}
