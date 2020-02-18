package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.component.UserValidator;
import cn.zyt.springbootlearning.domain.User;
import cn.zyt.springbootlearning.domain.ValidatorPojo;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/param")
public class ParamController {

    /**
     * 默认参数传递
     */
    @RequestMapping("/no/annotation")
    @ResponseBody
    public Map<String, Object> noAnnotation(Integer intVal, Long longVal, String str) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("inVal", intVal);
        paramsMap.put("longVal", longVal);
        paramsMap.put("str", str);
        return paramsMap;
    }

    /**
     * 参数对应关系设置
     */
    @RequestMapping("/annotation")
    @ResponseBody
    public Map<String, Object> requestParam(@RequestParam(value = "int_val", required = false) Integer intVal,
                                            @RequestParam(value = "long_val", required = false) Long longVal,
                                            @RequestParam(value = "str_val", required = false) String str) {
        Map<String, Object> params = new HashMap<>();
        params.put("intVal", intVal);
        params.put("longVal", longVal);
        params.put("str", str);
        return params;
    }

    /**
     * 数组参数传递
     */
    @RequestMapping("/requestArray")
    @ResponseBody
    public Map<String, Object> requestArray(int[] intArr, Long[] longArr, String[] strArr) {
        Map<String, Object> params = new HashMap<>();
        params.put("intArr", intArr);
        params.put("longArr", longArr);
        params.put("strArr", strArr);
        return params;
    }

    /**
     * URL路径参数传递
     */
    @RequestMapping("/{id}")
    @ResponseBody
    public Long get(@PathVariable("id") Long id) {
        return id;
    }

    /**
     * 格式化参数传递页面
     */
    @RequestMapping("/formatter")
    public String formatter() {
        return "formatter";
    }

    /**
     * 日期和数字格式化传递
     */
    @RequestMapping("/formatter/commit")
    @ResponseBody
    public Map<String, Object> formatCommit(@DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date date,
                                            @NumberFormat(pattern = "#,###,###.##") Double number) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("date", date);
        dataMap.put("number", number);
        System.out.println(dataMap);
        return dataMap;
    }

    /**
     * 自定义参数格式传递：cn.zyt.springbootlearning.component.StringToUserConverter
     */
    @GetMapping("/converter")
    @ResponseBody
    public User getUserByConverter(User user) {
        return user;
    }

    /**
     * 借助自定义参数传递，进行批量自定义参数传递
     * 实现GenericConverter数组转化器
     */
    @GetMapping("/list")
    @ResponseBody
    public List<User> getUserListByConverter(List<User> userList) {
        return userList;
    }

    /**
     * 参数验证页面
     */
    @GetMapping("/valid/page")
    public String validPage() {
        return "validator";
    }

    /**
     * POST请求验证参数，返回验证信息
     */
    @RequestMapping("/valid/validate")
    @ResponseBody
    public Map<String, Object> validate(@Valid @RequestBody ValidatorPojo validatorPojo, Errors errors) {
        Map<String, Object> errMap = new HashMap<>();
        // 获取错误信息列表
        List<ObjectError> oes = errors.getAllErrors();
        for (ObjectError oe : oes) {
            String key = null;
            String msg = null;
            // 字段错误
            if (oe instanceof FieldError) {
                FieldError fe = (FieldError) oe;
                key = fe.getField(); // 获取错误验证字段名
            } else {
                // 非字段错误，获取验证对象名称
                key = oe.getObjectName();
            }
            // 错误信息
            msg = oe.getDefaultMessage();
            errMap.put(key, msg);
        }
        System.out.println(errMap);
        return errMap;
    }

    /**
     * 自定义用户验证器
     * 该方法会在调用控制器之前执行
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 绑定config package中定义的用户验证器
        binder.setValidator(new UserValidator());
        // 定义日期参数格式，参数不在需要注解@DateTimeFormat, boolean参数表示是否允许为空
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
    }

    /**
     * 使用自定义用户验证器，不在进行DATE格式的验证
     *
     * @param user User对象使用StringToUserConverter（自定义的转换器）进行自动转换
     * @param errors 验证器返回的错误信息
     * @param date 因为WebDataBinder已经绑定的了Date的格式，因此这里不在需要@DateTimeFormat注解
     * @return 验证错误信息
     */
    @RequestMapping(value = "/valid/user-validator", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> validator(@Valid User user, Errors errors, Date date) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("date", date);

        if (errors.hasErrors()) {
            List<ObjectError> oes = errors.getAllErrors();
            for (ObjectError error : oes) {
                if (error instanceof FieldError) {
                    FieldError fe = (FieldError) error;
                    map.put(fe.getField(), fe.getDefaultMessage());
                } else {
                    map.put(error.getObjectName(), error.getDefaultMessage());
                }
            }
        }
        return map;
    }
}
