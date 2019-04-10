package com.jc.jc_backer.modules.admin.controller;

import com.jc.jc_backer.common.exception.JcException;
import com.jc.jc_backer.common.utils.ResponseUtil;
import com.jc.jc_backer.modules.admin.entity.Admin;
import com.jc.jc_backer.modules.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/17 16:15
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminController{
    //上传的文件类型的集合
    private static final List<String> UPLOAD_CONTENT_TYPES =new ArrayList<>() ;
    //添加允许上传的文件类型
    static {
        UPLOAD_CONTENT_TYPES.add("image/jpeg");
        UPLOAD_CONTENT_TYPES.add("image/png");
        UPLOAD_CONTENT_TYPES.add("image/gif");
        UPLOAD_CONTENT_TYPES.add("image/bmp");
    }

    /**
     * 存储上传的文件的文件夹名
     */
    public static final String UPLOAD_DIR="upload";

    /**
     * 上传文件时允许上传的最大大小
     */
    public static final long UPLOAD_MAX_SIZE =1*1024*1024;

    @Autowired
    private AdminService adminService;

    /**
     * 注册功能
     */
    @RequestMapping("/registerAdmin")
    public ResponseUtil registerAdmin(Admin admin){
        return adminService.registerAdmin(admin) ? ResponseUtil.success() : ResponseUtil.error();
    }

    /**
     * 登录功能
     */
    @RequestMapping("/loginAdmin")
    public ResponseUtil loginAdmin(Admin admin){
        return !ObjectUtils.isEmpty(adminService.loginAdmin(admin)) ? ResponseUtil.success() : ResponseUtil.error();
    }

    /**
     * 修改个人资料
     * @param admin
     * @param session
     * @return
     */
    @PostMapping("/updateAdmin")
    public ResponseUtil updateAdmin(Admin admin, HttpSession session){
        Admin ad=(Admin) session.getAttribute("admin");
        admin.setId(ad.getId());
        adminService.updateAdmin(admin);
        return ResponseUtil.success();
    }

    /**
     * 显示个人信息
     * @param session
     * @return
     */
    @GetMapping("/findByAdmin")
    public ResponseUtil findByAdmin(HttpSession session){
        Admin ad=(Admin) session.getAttribute("admin");
        Admin admin=adminService.findByAdmin(ad.getId());
        return ResponseUtil.success(admin);
    }

    /**
     * 头像上传
     * @param session
     * @param request
     * @param avatar 图片文件
     * @return
     */
    @PostMapping("/changeAvatar")
    public ResponseUtil changeAvatar(HttpSession session,
                                     HttpServletRequest request,
                                     @RequestParam("avatar")MultipartFile avatar) throws MalformedURLException {
        //判断上传文件是否为空isEmpty();
        if(avatar.isEmpty()) {
            //是：抛出文件不存在或为空异常
            throw new JcException("上传文件失败！没有选择文件，或者上传文件为空！");
        }
        //判断文件类型匹不匹配
        String contentype=avatar.getContentType();
        //是：抛出文件类型不匹配异常
        if(!UPLOAD_CONTENT_TYPES.contains(contentype)) {
            throw new JcException("上传文件失败！文件类型不支持"+contentype);
        }

        //判断文件大小是否超出
        long Size=avatar.getSize();
        if(Size > UPLOAD_MAX_SIZE) {
            //是：抛出文件大小超出异常
            throw new JcException("上传文件失败！尝试上传的文件大小超出了"+UPLOAD_MAX_SIZE/1024/1024+"M!");
        }

        //标准上传流程
        //创建文件夹
//        String path=request.getServletContext().getRealPath("../resources/static/"+UPLOAD_DIR);
        //String path = request.getServletContext().getResource("static").getPath();
        File parent = new File(ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/"+UPLOAD_DIR);
        if(!parent.exists()) {
            parent.mkdirs();
        }

        //获取原文件名
        String originalFilename=avatar.getOriginalFilename();
        String suffix="";
        int index=originalFilename.lastIndexOf(".");
        if(index!=-1) {
            suffix=originalFilename.substring(index);
        }

        //上传文件名
        String filename = UUID.randomUUID()+suffix;

        //保存上传的文件
        File dest = new File(parent,filename);
        try {
            avatar.transferTo(dest);
        }catch (IOException e) {
            e.printStackTrace();
            throw new JcException("上传文件失败！出现读写错误，请联系系统管理员！");
        }
        Admin ad=(Admin) session.getAttribute("admin");
        Long id=ad.getId();
        String avatarUrl = "/"+UPLOAD_DIR+"/"+filename;
        adminService.changeAvatar(id,avatarUrl);

        return ResponseUtil.success("修改成功",avatarUrl);
    }

}
