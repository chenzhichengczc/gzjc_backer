package com.jc.jc_backer.modules.Word.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jc.jc_backer.modules.Word.entity.WordEntity;


public interface WordMapper extends BaseMapper<WordEntity> {

    Integer findWordMessage(Integer id);
}
