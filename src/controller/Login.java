package controller;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.cglib.core.CollectionUtils;

import org.apache.bcel.generic.RETURN;
import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner.noneDSA;
import org.openqa.selenium.By;

import appdata.AppData;
import createxcel.CreateWritableSheet;
//登录过程相关测试用例
public class Login {
	//选择地市信息
	public static void switchCity(String province,String city) throws Exception {
		
		try {
			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/tv_city_zone")).click();
			Thread.sleep(5000);
			SetApp.driver.findElement(By.name(province)).click();
			Thread.sleep(1000);
			SetApp.driver.findElement(By.name(city)).click();		
		}catch (Exception e){
			e.printStackTrace();
			SetApp.screenShot("switchCity");
		}
	}
	
	//获取版本号以及客服电话
	public static void getLoginPageInfo () {
		try {
			Thread.sleep(3000);
			String telephone = SetApp.getElementText(By.id("com.nesec.jxjy_phone:id/txt_company_telephoneNum"));
			String copyright = SetApp.getElementText(By.id("com.nesec.jxjy_phone:id/txt_copy_right"));
			System.out.printf("%s\n%s",telephone,copyright);
			if(telephone.contains(AppData.phoneNumber) && copyright.contains(AppData.APPVersion)){
				CreateWritableSheet.addCell(AppData.row++, true, "Passed");
			}else{
				CreateWritableSheet.addCell(AppData.row++, false, "Failed");
				SetApp.screenShot("getLoginPageInfo");
			}			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	//登录
	public static boolean doLogin (String Id,String passwd) {
		boolean result = false;
		try{
			//点击并输入身份证
//			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/et_user_name")).click();
//			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/login_olduser_no")).click();
			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/et_user_name")).sendKeys(Id);
			//输入密码
//			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/et_user_pass")).click();
//			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/login_oldpwd_no")).click();
			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/et_user_pass")).sendKeys(passwd);	
			//点击登录
			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_login")).click();
			//判断是否登录成功
			Thread.sleep(2000);
			if (SetApp.waitForElement(By.id("com.nesec.jxjy_phone:id/btn_check_face"))){
				result = true;
				System.out.println("登录成功");
			}else{
				SetApp.screenShot("doLogin");
				System.out.println("登录失败");
			}
		}catch (Exception e){
			System.out.println("登录异常");
			e.printStackTrace();
		}
		return result;

	}
	
	//验证登录人脸
	public static boolean checkLoginFace () {
		boolean result = false;
		try {
			//点击验证人脸
			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_check_face")).click();
			Thread.sleep(5000);
			if (SetApp.waitForElement(By.id("com.nesec.jxjy_phone:id/dialog_yes"))){
				result = true;
				System.out.println("验证人脸成功");
			}else {
				SetApp.screenShot("checkLoginFace");
				System.out.println("验证人脸失败");
			}			
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	//获取温馨提示内容并点击确定
	public static boolean getAndClickNotice (){
		boolean result = false;
		try{
			Thread.sleep(4000);
			//获取温馨提示内容
			String noticeContentString = SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/dialog_text")).getText();
			System.out.println(noticeContentString);
			if(noticeContentString.equals(AppData.noticeContent)){
				result = true;
			}else{
				SetApp.screenShot("getAndClickNotice");
				System.out.println("温馨提示内容错误");
			}
			//点击温馨提示“确认”
			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/dialog_yes")).click();;
		}catch (Exception e){
			e.printStackTrace();
		}	
		return result;
	}
	
	//验证模块数量和名称是否正确
	public static boolean checkModel (){
		boolean result = false;
		try{
			//获取APP应用内实际的所有模块名称
			List<String> modelList = new ArrayList<String>();
			for (int i = 0;i < 3;i++){
				modelList.addAll(SetApp.getElementsNames(By.xpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView")));
				SetApp.swipeModel("right", 2000);
			}
			for (int i = 0;i < 3;i++){
				modelList.addAll(SetApp.getElementsNames(By.xpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView")));
				SetApp.swipeModel("left", 2000);
			}
			//去除重复获取的实际的模块名称
			Set<String> modelSet = new HashSet<String>(modelList);
			//获取要求的模块名称并与实际模块做对比
			Set<String> defaultModelSet = new HashSet<String>(Arrays.asList(AppData.defaultModelList));

			//对比
			if(defaultModelSet.containsAll(modelSet)){
				result = true;
				System.out.println("模块匹配成功");
			}else{
				SetApp.screenShot("checkModel");
				System.out.println("模块匹配失败");
			}			
			System.out.println(modelSet);			
		}catch (Exception e){
			System.out.println("check模块名称出错");
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}


