package com.jc.jc_backer.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadUtils {

	public static String getPropertiesValue(String propertyName) {
		String value = PropertiesUtil.getInstance()
		.getValue("","");
	return value;
	}
	/**
	* 描述:返回一个根据当前时间生成的文件名
	* @param originName
	* @return
	*/
	public static String getCurrentTimeFileName(String originName){
		String timespan = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+1;// 当前时间转化为yyyymmddhhmmss;
		String ext = originName.substring(originName.lastIndexOf(".") + 1);
		String targetFileName =timespan + "."+ ext;
	return targetFileName;
	}
	/**
	* 返回上传目标文件夹的路径(绝对路径)
	* @return
	*/
	public static String getFilePathByPropertyName(String propertyName){
		return getPropertiesValue("imgServer.path")+getPropertiesValue(propertyName);
	}

	/**
	* 上传用户头像
	* @param file
	* @param
	* @return
	*/
	public static String uploadUserPhoto(MultipartFile file){
		String newFileName = UploadUtils.getCurrentTimeFileName(file.getOriginalFilename());
		String targetFilePath = getFilePathByPropertyName("userphoto.img.path");
		File folder = new File(targetFilePath);
		File targetFile = new File(targetFilePath+newFileName);
		if(!folder.exists()&&!folder.isDirectory()){
			folder.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			return getPropertiesValue("userphoto.img.path")+targetFile.getName();
		}

		
		/**
		* 获取图片路径
		* @param fileName 文件名字
		* @param upload 拼接的路径
		* @return 路径地址
		*/
		public static String getUploadPath(String fileName, String upload){
			//对文件名称散列计算
			String hash = Integer.toHexString(fileName.hashCode());
			while(hash.length()<8){
				hash += "0";
			}
			//拼接upload生成一个头,生成一个多级的文件路径
			//   /upload/4/d/5/3/d/a/1/c/
			for(int i = 0; i<hash.length(); i++){
				upload += "/" + hash.charAt(i);
			}
			return upload;
		}
	}
