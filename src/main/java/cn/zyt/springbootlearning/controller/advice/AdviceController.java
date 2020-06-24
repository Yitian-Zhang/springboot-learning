package cn.zyt.springbootlearning.controller.advice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 控制器通知测试类
 *
 * @author yitian
 */
@Controller
@RequestMapping("/advice")
public class AdviceController {

    @RequestMapping("/test")
    public String adviceTest(Date date, ModelMap modelMap) {
        if (date == null) {
            modelMap.addAttribute("exception_message", "请输出Date");
            return "exception";
        }

        // 从数据模型中获取数据
        System.out.println(modelMap.getAttribute("project_name"));

        // 打印日期参数
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dateFormat.format(date));

        // 抛出异常，测试控制器异常通知
        throw new RuntimeException("Exception Happened! Will redirect to the exception.jsp");
    }
}
