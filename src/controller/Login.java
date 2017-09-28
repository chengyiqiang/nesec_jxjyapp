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
//��¼������ز�������
public class Login {
	//ѡ�������Ϣ
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
	
	//��ȡ�汾���Լ��ͷ��绰
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
	
	//��¼
	public static boolean doLogin (String Id,String passwd) {
		boolean result = false;
		try{
			//������������֤
//			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/et_user_name")).click();
//			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/login_olduser_no")).click();
			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/et_user_name")).sendKeys(Id);
			//��������
//			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/et_user_pass")).click();
//			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/login_oldpwd_no")).click();
			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/et_user_pass")).sendKeys(passwd);	
			//�����¼
			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_login")).click();
			//�ж��Ƿ��¼�ɹ�
			Thread.sleep(2000);
			if (SetApp.waitForElement(By.id("com.nesec.jxjy_phone:id/btn_check_face"))){
				result = true;
				System.out.println("��¼�ɹ�");
			}else{
				SetApp.screenShot("doLogin");
				System.out.println("��¼ʧ��");
			}
		}catch (Exception e){
			System.out.println("��¼�쳣");
			e.printStackTrace();
		}
		return result;

	}
	
	//��֤��¼����
	public static boolean checkLoginFace () {
		boolean result = false;
		try {
			//�����֤����
			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_check_face")).click();
			Thread.sleep(5000);
			if (SetApp.waitForElement(By.id("com.nesec.jxjy_phone:id/dialog_yes"))){
				result = true;
				System.out.println("��֤�����ɹ�");
			}else {
				SetApp.screenShot("checkLoginFace");
				System.out.println("��֤����ʧ��");
			}			
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	//��ȡ��ܰ��ʾ���ݲ����ȷ��
	public static boolean getAndClickNotice (){
		boolean result = false;
		try{
			Thread.sleep(4000);
			//��ȡ��ܰ��ʾ����
			String noticeContentString = SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/dialog_text")).getText();
			System.out.println(noticeContentString);
			if(noticeContentString.equals(AppData.noticeContent)){
				result = true;
			}else{
				SetApp.screenShot("getAndClickNotice");
				System.out.println("��ܰ��ʾ���ݴ���");
			}
			//�����ܰ��ʾ��ȷ�ϡ�
			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/dialog_yes")).click();;
		}catch (Exception e){
			e.printStackTrace();
		}	
		return result;
	}
	
	//��֤ģ�������������Ƿ���ȷ
	public static boolean checkModel (){
		boolean result = false;
		try{
			//��ȡAPPӦ����ʵ�ʵ�����ģ������
			List<String> modelList = new ArrayList<String>();
			for (int i = 0;i < 3;i++){
				modelList.addAll(SetApp.getElementsNames(By.xpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView")));
				SetApp.swipeModel("right", 2000);
			}
			for (int i = 0;i < 3;i++){
				modelList.addAll(SetApp.getElementsNames(By.xpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView")));
				SetApp.swipeModel("left", 2000);
			}
			//ȥ���ظ���ȡ��ʵ�ʵ�ģ������
			Set<String> modelSet = new HashSet<String>(modelList);
			//��ȡҪ���ģ�����Ʋ���ʵ��ģ�����Ա�
			Set<String> defaultModelSet = new HashSet<String>(Arrays.asList(AppData.defaultModelList));

			//�Ա�
			if(defaultModelSet.containsAll(modelSet)){
				result = true;
				System.out.println("ģ��ƥ��ɹ�");
			}else{
				SetApp.screenShot("checkModel");
				System.out.println("ģ��ƥ��ʧ��");
			}			
			System.out.println(modelSet);			
		}catch (Exception e){
			System.out.println("checkģ�����Ƴ���");
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}


