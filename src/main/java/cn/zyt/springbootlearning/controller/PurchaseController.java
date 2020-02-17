package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.service.PurchaseService;
import cn.zyt.springbootlearning.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/page")
    public String page() {
        return "purchase";
    }

    @PostMapping("/start")
    @ResponseBody
    public CommonResult start(Long userId, Long productId, Integer quantity) {
        boolean success = purchaseService.purchase(userId, productId, quantity);
        String msg = success ? "抢购成功" : "抢购失败";
        return new CommonResult(success, msg);
    }

    @PostMapping("/startCAS")
    @ResponseBody
    public CommonResult startCAS(Long userId, Long productId, Integer quantity) {
        boolean success = purchaseService.purchaseCAS(userId, productId, quantity);
        String msg = success ? "抢购成功" : "抢购失败";
        return new CommonResult(success, msg);
    }

    @PostMapping("/startCASWithTime")
    @ResponseBody
    public CommonResult startCASWithTime(Long userId, Long productId, Integer quantity) {
        boolean success = purchaseService.purchaseCASWithTime(userId, productId, quantity);
        String msg = success ? "抢购成功" : "抢购失败";
        return new CommonResult(success, msg);
    }

    @PostMapping("/startCASWithCount")
    @ResponseBody
    public CommonResult startCASWithCount(Long userId, Long productId, Integer quantity) {
        boolean success = purchaseService.purchaseCASWithCount(userId, productId, quantity);
        String msg = success ? "抢购成功" : "抢购失败";
        return new CommonResult(success, msg);
    }

    @RequestMapping("/startWithRedis")
    @ResponseBody
    public CommonResult startWithRedis(Long userId, Long productId, Integer quantity) {
        boolean success = purchaseService.purchaseRedis(userId, productId, quantity);
        String msg = success ? "抢购成功" : "抢购失败";
        return new CommonResult(success, msg);
    }
}
