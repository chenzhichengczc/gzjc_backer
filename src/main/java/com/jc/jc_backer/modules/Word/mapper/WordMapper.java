package com.jc.jc_backer.modules.Word.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jc.jc_backer.modules.Word.entity.WordEntity;

import java.util.List;


public interface WordMapper extends BaseMapper<WordEntity> {

    List<WordEntity> findWordMessage(Integer id);

}
