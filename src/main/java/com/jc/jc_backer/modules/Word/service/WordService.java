package com.jc.jc_backer.modules.Word.service;

import com.baomidou.mybatisplus.service.IService;
import com.jc.jc_backer.modules.Word.entity.WordEntity;


public interface WordService extends IService<WordEntity> {

   Integer findWordMessage(Integer id) ;

}
