package controller;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import appdata.AppData;

import com.sun.jna.FromNativeContext;

import createxcel.CreateWritableSheet;

//�γ�ѧϰ����
public class KCXX {
	
	// ��ȡ�γ�ѧϰ�½ڲ���֤--ֻ�ж��˾����½��Ƿ���ȷ��û���ж��½ڱ����Ƿ���ȷ
	public static void checkChapterName() {
		try {
			// ��ģ�黬������һ���½ڿ�����ʾ�Ľ���
			for (int i = 0; i < 6; i++) {
				SetApp.swipeSection("down", 2000);
			}
			// ��ȡ�½ڱ�����½�
			for (int i = 0; i < 7; i++) {
				AppData.groupNameList.addAll(SetApp.getElementsNames(By.id("com.nesec.jxjy_phone:id/tv_group_title")));
				AppData.childNameList.addAll(SetApp.getElementsNames(By.id("com.nesec.jxjy_phone:id/tv_child_title")));
				SetApp.swipeSection("up", 2000);
			}
			// �Ƴ��ظ�������½�
			AppData.groupNameList = SetApp.removeDuolicateWithOrder(AppData.groupNameList);
			AppData.childNameList = SetApp.removeDuolicateWithOrder(AppData.childNameList);
			// �Ա��½���Ϣ�Ƿ���ȷ
			for (int row = 1, column = 1, i = 0; i < AppData.childNameList
					.size(); row++, i++) {
				String excelContent = CreateWritableSheet.readWritableSheet(column, row);
				String realContent = AppData.childNameList.get(i);
				if (excelContent.equals(realContent)) {
					CreateWritableSheet.addCell(AppData.row++, true, "Passed");
				} else {
					CreateWritableSheet.addCell(AppData.row++, false, "Failed");
					SetApp.screenShot("getModelName");
				}
			}
			System.out.println(AppData.groupNameList);
			System.out.println(AppData.childNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�����Ƶ����ʼѧϰ
	public static void watchVideo (String sectionName){
		try{
			SetApp.getElementByTextAndBy(By.id("com.nesec.jxjy_phone:id/tv_child_title"), sectionName).click();
			Thread.sleep(5000);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("�����Ƶ��ѧϰ�쳣");
		}
	}
	
	//��ȡ��Ƶ���Ź����е�Ԫ��
	public static WebElement getVideoElement (By by) {
		WebElement webElement = null;
		try {
			Thread.sleep(5000);
			SetApp.touchAction.press(SetApp.width/2, SetApp.hight/2).release().perform();
			Thread.sleep(1000);
			webElement  = SetApp.driver.findElement(by);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return webElement;
	}
	
	//��Ƶ��ͣ��ʼ
	public static void pauseOrPlay() {
		try{
			getVideoElement(By.id("com.nesec.jxjy_phone:id/btn_play")).click();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("��Ƶ��ͣ�򲥷��쳣");
		}
	}
	
	//��ȡ��Ƶ�Ѳ���ʱ��
	public static String getVideoHasPlayedTime() {
		String videoHasPlayedTime = "��ȡ�Ѳ�ʱ��ʧ��";
		try{
			videoHasPlayedTime = getVideoElement(By.id("com.nesec.jxjy_phone:id/tv_has_played_time")).getText();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("��ȡ�Ѳ�ʱ���쳣");
		}
		return videoHasPlayedTime;
	}
	
	//��ȡ��Ƶʱ��
	public static String getVideoTotalTime() {
		String videoTotalTime = "��ȡ��Ƶ��ʱ��ʧ��";
		try{
			videoTotalTime = getVideoElement(By.id("com.nesec.jxjy_phone:id/tv_duration_alltime")).getText();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("��ȡ��Ƶ��ʱ���쳣");
		}
		return videoTotalTime;
	}
	
	//��Ƶ���÷��ؼ�
	public static void videoBack(){
		try{
			getVideoElement(By.id("com.nesec.jxjy_phone:id/imgbtn_back")).click();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("�����Ƶ���÷��ؼ��쳣");
		}
	}
	
	//��ȡ��Ƶ����
	public static String getVideoTitle(){
		String videoTitle = "��ȡ��Ƶ����ʧ��";
		try{
			videoTitle = getVideoElement(By.id("com.nesec.jxjy_phone:id/tv_video_title")).getText();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("��ȡ��Ƶ�����쳣");
		}
		return videoTitle;
	}
	
	//�϶�������
	public static void dragSeekBar(String backOrFront){
		try{
			Thread.sleep(5000);
			SetApp.touchAction.press(SetApp.width/2, SetApp.hight/2).release().perform();
			Thread.sleep(1000);
			int hight = SetApp.getMidHight(SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/seekbar")));
			if(backOrFront.equals("front")){
				SetApp.driver.swipe(100, hight, 400, hight, 1500);
			}else if(backOrFront.equals("back")){
				SetApp.driver.swipe(400, hight, 100, hight, 1500);
			}else{
				System.out.println("\"backOrFront\"��������Ϊback��front");
			}			
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("�϶��������쳣");
		}
	}
	
	
	//�γ�ѧϰ�ӿ�ƪѧϰ�����һ��.............�����⣬����ܶ࣬�����
	public static void learnKCXX (){
		try {
			// ��ģ�黬������һ���½ڿ�����ʾ�Ľ���
			for (int i = 0; i < 6; i++) {
				SetApp.swipeSection("down", 2000);
			}
			//��ƪ��ѧϰ
			watchVideo(AppData.childNameList.get(0));
			Thread.sleep(SetApp.timeToInt(getVideoTotalTime())+5000);
			//�����½ڵ�ѧϰ
			for (int i = 1; i < AppData.childNameList.size(); i++) {
				//�ۿ���Ƶ
				watchVideo(AppData.childNameList.get(i));
				//��֤����
				SetApp.checkFace();
				//�ȴ���Ƶʱ��+15s
				Thread.sleep(SetApp.timeToInt(getVideoTotalTime())+15000);
				//��֤����
				SetApp.checkFace();
				//�κ���ϰ����
				if(AppData.isdyexam){
					for (int j = 0; j < 10; j++) {
						MLKS.chooseSingleAnswer(MLKS.getAnswer());
					}
					SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_commit")).click();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�γ�ѧϰ�ӿ�ƪѧϰ�����һ���쳣");
		}
		
		
	}
	

}
