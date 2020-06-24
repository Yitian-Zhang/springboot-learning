package cn.zyt.springbootlearning.controller.advice;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义控制器通知类
 * Spring MVC允许给控制器增加通知，来增强Controller的功能，可以在控制器方法的前后和异常发生时去执行不同的处理
 * 这里涉及四个注解：
 * /@ControllerAdvice：标注当前类是一个控制器通知类，该注解也标注了Controller，可以被装配到Spring IOC容器中自动扫描。
 * /@InitBinder：是一个在控制器参数转换前被执行的代码，其中WebDataBinder参数对象是Spring MVC自动生成的。
 * /@ExceptionHanlder：是一个数据模型的注解，在执行控制器方法之前被执行。
 * /@ModelAttribute：配置项为Exception，可以拦截所有控制器发生的异常，这里将所有的异常统一返回exception.jsp视图
 *
 * @author yitian
 */
@ControllerAdvice(
        // 指定拦截的包路径
        basePackages = {"cn.zyt.springbootlearning.controller.advice.*"},
        // 限定被标注为@Controller的类才被拦截
        annotations = Controller.class)
public class MyControllerAdvice {

    /**
     * 绑定格式化、参数转换规则和增加验证器等
     */
    public void initDataBinder(WebDataBinder binder) {
        // 自定义日期编辑器，限定格式为yyyy-MM-dd，且参数不能为空
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false);
        // 注册自定义日期编辑器
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    /**
     * 在执行控制器之前先执行，可以初始化数据模型
     */
    @ModelAttribute
    public void projectModel(Model model) {
        model.addAttribute("project_name", "springboot-learning");
    }

    /**
     * 异常处理，使得被拦截的控制器方法发生异常时，都能使用相同的视图响应
     */
    @ExceptionHandler(value = Exception.class)
    public String exception(Model model, Exception ex) {
        // 给数据模型增加异常消息
        model.addAttribute("exception_message", ex.getMessage());
        // 返回异常视图
        return "exception";
    }

}
