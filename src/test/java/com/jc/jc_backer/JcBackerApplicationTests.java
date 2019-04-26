package com.jc.jc_backer;

import com.jc.jc_backer.config.FileProperties;
import com.jc.jc_backer.modules.admin.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JcBackerApplicationTests {

    @Autowired
    private AdminService adminService;


    @Test
    public void contextLoads() {
    }



}
