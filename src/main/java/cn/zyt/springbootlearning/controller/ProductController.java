package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.dao.ProductMapper;
import cn.zyt.springbootlearning.domain.business.ProductPO;
import cn.zyt.springbootlearning.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @RequestMapping("/insert")
    @ResponseBody
    public CommonResult insertProduct(ProductPO product) {
        int result = productMapper.insertProduct(product);
        boolean success = result == 1 ? true : false;
        String msg = success ? "添加商品成功" : "添加商品失败";
        return new CommonResult(success, msg);
    }

    @GetMapping("/list")
    public ModelAndView productList(ModelAndView view) {
        List<ProductPO> productList = productMapper.getProductList();
        if (productList != null) {
            view.addObject("productList", productList);
        }
        view.setViewName("productList");
        return view;
    }

}
