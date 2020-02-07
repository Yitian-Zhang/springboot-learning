package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.dao.AuthorMapper;
import cn.zyt.springbootlearning.dao.BlogMapper;
import cn.zyt.springbootlearning.dao.VehicleMapper;
import cn.zyt.springbootlearning.domain.mybatis.Author;
import cn.zyt.springbootlearning.domain.mybatis.Blog;
import cn.zyt.springbootlearning.domain.mybatis.discriminator.Vehicle;
import cn.zyt.springbootlearning.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 详细使用MyBatis中的resultMap功能，测试Controller
 *
 * @author yitian
 */
@Controller
@RequestMapping("/resultmap")
public class ResultMapTestController {

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private VehicleMapper vehicleMapper;

    /**
     * 查找Author，使用resultmap进行映射
     */
    @GetMapping("/getAuthor")
    @ResponseBody
    public CommonResult getAuthor(Integer id) {
        Author author = authorMapper.getAuthorProvider(id);
        return new CommonResult(true, "获取成功", author);
    }

    /**
     * 查找复杂数据类型Blog，使用ResultMap实现映射
     */
    @GetMapping("/getBlog")
    @ResponseBody
    public CommonResult getBlog(Integer id) {
        Blog blog = blogMapper.getBlog(id);
        System.out.println("Blog: " + blog.toString());
        return new CommonResult(true, "获取成功", blog);
    }

    @GetMapping("/getVehicle")
    @ResponseBody
    public CommonResult getVehicle(Integer id) {
        Vehicle vehicle = vehicleMapper.getVehicle(id);
        System.out.println(vehicle);
        return new CommonResult(true, "获取成功", vehicle);
    }

    @GetMapping("/getVehicleList")
    @ResponseBody
    public CommonResult getVehicleList() {
        List<Vehicle> vehicleList = vehicleMapper.getVehicleList();
        System.out.println(vehicleList);
        return new CommonResult(vehicleList != null, "获取成功", vehicleList);
    }
}
