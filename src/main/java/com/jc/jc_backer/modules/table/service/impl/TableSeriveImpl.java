package com.jc.jc_backer.modules.table.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jc.jc_backer.common.exception.JcException;
import com.jc.jc_backer.modules.table.entity.Table;
import com.jc.jc_backer.modules.table.mapper.TableMapper;
import com.jc.jc_backer.modules.table.service.TableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Service
public class TableSeriveImpl implements TableService {

    @Resource
    private TableMapper tableMapper;

    @Override
    public List<Table> findTable(@NotNull Table table,String name) {
        if("".equals(table.getTitle())){
            throw new JcException("添加失败！标题不能为空！");
        }
        table.setCreateBy(name);
        insertTable(table);
        List<Table> tables=selectTable();
        return tables;
    }

    @Override
    public List<Table> selectAll(Integer audit,Integer category) {
        List<Table> tables;
        if (audit == 0) {
            tables = tableMapper.selectByAudits();
            return tables;
        } else if (audit == 1) {
            tables = tableMapper.selectByAudit();
            return tables;
        } else if (audit == 3) {
            if(category==1){
                tables = selectTable();
                return tables;
            }else if(category==2){
                tables=tableMapper.selectByCategory("微信");
                return tables;
            }else if(category==3){
                tables=tableMapper.selectByCategory("web");
                return tables;
            }
            return null;
        }
        return null;
    }

    @Override
    public void removeTable(Long tid) {
        Table table=tableMapper.selectById(tid);
        table.setRemove(0);
        updateById(table);
    }

    @Override
    public void updateTable(Long tid,String title,
                            String category,String author,
                            String website,String name,Integer audit) {
//        if(table.getRemove()==0){
//            throw new JcException("修改失败！尝试访问的信息不存在！");
//        }
        Table table=selectTable(tid);
        table.setTitle(title);
        table.setCategory(category);
        table.setAuthor(author);
        table.setWebsite(website);
        table.setAudit(audit);
        Date time= new Date();
        table.setProUpdateBy(name);
        table.setProUpdateTime(time);
        updateById(table);
    }

    @Override
    public Table selectTable(Long tid) {
        return tableMapper.selectById(tid);
    }

    private Integer updateById(Table table){
        Integer rows=tableMapper.updateById(table);
        if(rows!=1){
            throw new JcException("删除失败！未知错误！");
        }
        return rows;
    }

    private List<Table> selectTable(){
        Wrapper<Table> wrapper=new Wrapper<Table>() {
            @Override
            public String getSqlSegment() {
                return null;
            }
        };
        List<Table> tableList=tableMapper.selectList(wrapper);
        for (int i=tableList.size()-1;i>0;i--) {
            Table table=tableList.get(i);
            if(table.getRemove()==0){
                tableList.remove(i);
            }
        }
        return tableList;
    }

    private Integer insertTable(Table table){
        Integer rows=tableMapper.insert(table);
        if(rows!=1){
            throw new JcException("添加失败！未知错误！");
        }
        return rows;
    }
}
