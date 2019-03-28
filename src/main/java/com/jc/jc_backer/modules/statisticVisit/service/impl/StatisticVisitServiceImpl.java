package com.jc.jc_backer.modules.statisticVisit.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jc.jc_backer.modules.statisticVisit.entity.Statistic;
import com.jc.jc_backer.modules.statisticVisit.mapper.StatisticVisitMapper;
import com.jc.jc_backer.modules.statisticVisit.service.StatisticVisitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/27 16:52
 * @Version 1.0
 */
@Service
public class StatisticVisitServiceImpl implements StatisticVisitService {

    private Integer opraCount;

    private Integer fireFoxCount;

    private Integer chromeCount;

    private Integer safariCount;

    private Integer ieCount;

    private Integer edgeCount;

    @Resource
    private StatisticVisitMapper statisticVisitMapper;

    @Override
    public Map<String, Integer> getStatisticVisitCount() {

        List<Statistic> statisticList = statisticVisitMapper.selectList(new EntityWrapper<Statistic>());

        Map<String, Integer> map = new HashMap<String, Integer>();

        opraCount = fireFoxCount = chromeCount = safariCount = ieCount = edgeCount = 0;

        for(int i =0; i<statisticList.size(); i++) {
            String addressUserAgent = statisticList.get(i).getAddressUserAgent().toUpperCase();
            if (addressUserAgent.indexOf("OPERA") > -1) {
                opraCount++;
            } else if (addressUserAgent.indexOf("MSIE") > -1 ) {
                ieCount++;
            } else if (addressUserAgent.indexOf("TRIDENT") > -1 || addressUserAgent.indexOf("EDGE") > -1 ) {
                edgeCount++;
            } else if (addressUserAgent.indexOf("FIREFOX") > -1) {
                fireFoxCount++;
            } else if (addressUserAgent.indexOf("SAFARI") > -1 && addressUserAgent.indexOf("CHROME") < -1) {
                safariCount++;
            } else if (addressUserAgent.indexOf("CHROME") > -1) {
                chromeCount++;
            }  else{
                continue;
            }
        }

        map.put("Opera",opraCount);
        map.put("Firefox",fireFoxCount);
        map.put("Chrome",chromeCount);
        map.put("Safari",safariCount);
        map.put("IE",ieCount);
        map.put("Trident",edgeCount);

        return map;
    }

}
