package com.finance.controller;

import com.finance.model.pojo.FundOrderDO;
import com.finance.service.FundOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by zt on 2017/2/7.
 */
@Controller
@RequestMapping(value = "/fund/order")
public class FundOrderController {

    private static final Logger logger = LoggerFactory.getLogger(FundOrderController.class);

    @Resource
    private FundOrderService fundOrderService;

    @RequestMapping(value = "/insertpage", method = RequestMethod.POST)
    public String gotoInsertFundOrder() {
        return "insertFundOrder" ;
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map insertOrUpdateFundNetData(FundOrderDO fundOrder, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            fundOrder.setUserId((String) session.getAttribute("userId"));
            fundOrderService.insertFundOrder(fundOrder);
            map.put("flag", 1);
            return map;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("flag", 0);
        }
        return map;
    }
}
