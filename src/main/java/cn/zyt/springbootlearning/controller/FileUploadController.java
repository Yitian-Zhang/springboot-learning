package cn.zyt.springbootlearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传控制器
 *
 * @author yitian
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @RequestMapping("/upload/page")
    public String updatePage() {
        return "upload";
    }

    /**
     * 使用HttpServletRequest实现文件上传
     */
    @RequestMapping("/upload/request")
    @ResponseBody
    public Map<String, Object> updateRequest(HttpServletRequest request) {
        MultipartHttpServletRequest servletRequest = null;
        // 强制转换为MultipartHttpServletRequest接口对象
        if (request instanceof MultipartHttpServletRequest) {
            servletRequest = (MultipartHttpServletRequest) request;
        } else {
            return dealResultMap(false, "上传失败");
        }

        // 获取MultipartFile文件信息
        MultipartFile multipartFile = servletRequest.getFile("file");
        // 获取源文件名称
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(fileName);

        try {
            // 保存文件到application.properties中设置的上传路径
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }

        return dealResultMap(true, "上传成功");
    }

    /**
     * 使用Spring MVC的MultipartFile实现文件上传
     * 使用该方法是，参数需要与JSP页面中的设置保持一致：<input type="file" name="file" value="请选择上传文件" />
     */
    @RequestMapping("/upload/multipart")
    @ResponseBody
    public Map<String, Object> uploadMultipartFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        File dest = new File(fileName);

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }

    /**
     * 使用Part作为参数完成文件上传
     * 使用该方法是，参数需要与JSP页面中的设置保持一致：<input type="file" name="file" value="请选择上传文件" />
     */
    @RequestMapping("/upload/part")
    @ResponseBody
    public Map<String, Object> uploadPart(Part file) {
        // 获取提交文件名称
        String fileName = file.getSubmittedFileName();
        try {
            // 写入文件
            file.write(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }

    private Map<String, Object> dealResultMap(boolean success, String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("msg", msg);
        return result;
    }

}
