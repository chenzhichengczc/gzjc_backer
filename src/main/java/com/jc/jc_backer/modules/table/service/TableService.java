package com.jc.jc_backer.modules.table.service;

import com.jc.jc_backer.modules.table.entity.Table;

import java.util.List;

/**
 * @Author: sunjw
 * @Date 2019/4/8 14:17
 * @Version 1.0
 */
public interface TableService {

    public List<Table> findTable(Table table, String name);

    public List<Table> selectAll(Integer audit,Integer category);

    public void removeTable(Long tid);

    public void updateTable(Long tid,String title,
                            String category, String author,
                            String website, String name,Integer audit);

    public Table selectTable(Long tid);

}
