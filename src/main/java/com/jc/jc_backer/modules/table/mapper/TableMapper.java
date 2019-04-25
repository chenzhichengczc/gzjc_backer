package com.jc.jc_backer.modules.table.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jc.jc_backer.modules.table.entity.Table;

import java.util.List;

/**
 * @Author: sunjw
 * @Date 2019/4/8 11:47
 * @Version 1.0
 */
public interface TableMapper extends BaseMapper<Table> {

    public List<Table> selectByAudits();

    public List<Table> selectByAudit();

    public List<Table> selectByCategory(String category);

}
