package com.jc.jc_backer.modules.Word.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jc.jc_backer.modules.Word.entity.WordEntity;
import com.jc.jc_backer.modules.Word.mapper.WordMapper;
import com.jc.jc_backer.modules.Word.service.WordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, WordEntity> implements WordService {

    @Resource
    private WordMapper wordMapper;

    public Integer findWordMessage(Integer id) {
        return wordMapper.findWordMessage(id);
    }
}
