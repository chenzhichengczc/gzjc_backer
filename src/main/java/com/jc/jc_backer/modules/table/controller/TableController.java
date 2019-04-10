package com.jc.jc_backer.modules.table.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.jc_backer.common.utils.ResponseUtil;
import com.jc.jc_backer.modules.admin.entity.Admin;
import com.jc.jc_backer.modules.table.entity.Table;
import com.jc.jc_backer.modules.table.service.TableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/table")
public class TableController {

    @Resource
    private TableService tableService;

    @RequestMapping("/findTable")
    public ResponseUtil findTable(HttpSession session, Table table){
        Admin admin = (Admin) session.getAttribute("admin");
        List<Table> tables=tableService.findTable(table, admin.getName());
        return ResponseUtil.success("添加成功！",tables);
    }

    @RequestMapping("/selectAll")
    public ResponseUtil selectAll(Integer audit,Integer category,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Table> tables=tableService.selectAll(audit,category);
        PageInfo<Table> pageInfo = new PageInfo<>(tables);
        return ResponseUtil.success(pageInfo);
    }

    @RequestMapping("/removeTable")
    public ResponseUtil removeTable(Long tid){
        tableService.removeTable(tid);
        return ResponseUtil.success();
    }

    @RequestMapping("/updateTable")
    public ResponseUtil updateTable(Long tid,String title,String category,String author,String website,Integer audit,HttpSession session){
        Admin admin=(Admin) session.getAttribute("admin");
        tableService.updateTable(tid,title,category,author,website,admin.getName(),audit);
        return ResponseUtil.success();
    }

    @RequestMapping("/selectTable")
    public ResponseUtil selectTable(Long tid){
        Table table=tableService.selectTable(tid);
        return ResponseUtil.success(table);
    }
}
