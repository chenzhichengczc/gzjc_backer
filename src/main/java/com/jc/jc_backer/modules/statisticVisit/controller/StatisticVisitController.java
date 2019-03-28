package com.jc.jc_backer.modules.statisticVisit.controller;

import com.jc.jc_backer.common.utils.ResponseUtil;
import com.jc.jc_backer.modules.statisticVisit.service.StatisticVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/27 16:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/statistic")
public class StatisticVisitController {

    @Autowired
    private StatisticVisitService statisticVisitService;

    @GetMapping("/getStatisticVisitCount")
    public ResponseUtil getStatisticVisitCount(){
        //统计浏览器访问数量
        Map<String,Integer> countMap = statisticVisitService.getStatisticVisitCount();
        return ResponseUtil.success(countMap);
    }

}
