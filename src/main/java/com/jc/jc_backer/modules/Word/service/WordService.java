package com.jc.jc_backer.modules.Word.service;

import com.baomidou.mybatisplus.service.IService;
import com.jc.jc_backer.modules.Word.entity.WordEntity;

import java.text.ParseException;
import java.util.List;


public interface WordService extends IService<WordEntity> {

   List<WordEntity> findWordMessage(Integer id) ;

   void insertWordMessage(WordEntity wordEntity) throws ParseException;

}
