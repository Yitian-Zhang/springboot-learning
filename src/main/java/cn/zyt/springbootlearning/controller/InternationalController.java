package cn.zyt.springbootlearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@RequestMapping("/international")
public class InternationalController {

    /**
     * 注入国际化消息接口对象
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * 获取国际化信息和打开国际化视图
     */
    @RequestMapping("/page")
    public String page(HttpServletRequest request) {
        // 后台获取国际化区域
        Locale locale = LocaleContextHolder.getLocale();
        // 获取国际化配置文件中设置的国际化消息，使用msg标志进行寻找
        String msg = messageSource.getMessage("msg", null, locale);
        System.out.println("msg=" + msg);
        // 返回国际化视图
        return "international";
    }
}
