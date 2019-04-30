package com.jc.jc_backer.modules.Word.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jc.jc_backer.modules.Word.entity.WordEntity;
import com.jc.jc_backer.modules.Word.mapper.WordMapper;
import com.jc.jc_backer.modules.Word.service.WordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, WordEntity> implements WordService {

    @Resource
    private WordMapper wordMapper;

    @Override
    public List<WordEntity> findWordMessage(Integer id) {
        return wordMapper.findWordMessage(id);
    }

    @Override
    public void insertWordMessage(WordEntity wordEntity) throws ParseException {
        wordMapper.insert(wordEntity);
    }

}
