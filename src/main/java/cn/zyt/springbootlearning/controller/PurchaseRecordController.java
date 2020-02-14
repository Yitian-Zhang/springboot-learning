package cn.zyt.springbootlearning.controller;

import cn.zyt.springbootlearning.dao.PurchaseRecordMapper;
import cn.zyt.springbootlearning.domain.business.PurchaseRecordPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/record")
public class PurchaseRecordController {

    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper;

    @GetMapping("/list")
    @ResponseBody
    public ModelAndView recordList(ModelAndView view) {
        List<PurchaseRecordPO> recordList = purchaseRecordMapper.getPurchaseRecordAll();
        if (recordList != null) {
            view.addObject("recordList", recordList);
        }
        view.setViewName("/recordList");
        return view;
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<PurchaseRecordPO> searchRecords(@RequestParam(value = "userId", required = false) Long userId,
                                                @RequestParam(value = "productId", required = false) Long productId) {
        return purchaseRecordMapper.searchRecords(userId, productId);
    }
}
