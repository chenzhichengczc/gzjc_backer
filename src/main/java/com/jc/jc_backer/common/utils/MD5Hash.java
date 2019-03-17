package com.jc.jc_backer.common.utils;

import com.jc.jc_backer.modules.admin.entity.Admin;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/17 17:04
 * @Version 1.0
 */
public class MD5Hash {

    //算法 固定MD5
    private static final String MD5 = "MD5";

    /**
     * MD5加密算法 - Shiro
     */
    public static String getMD5Hash(Admin admin){
        SimpleHash simpleHash = new SimpleHash(MD5, admin.getPassword(), admin.getUsername() + admin.getPassword(), 3);
        return simpleHash.toString();
    }

}
