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
	
	
	
	
	//�γ�ѧϰ�ӿ�ƪѧϰ�����һ��.............�����⣬����ܶ࣬�����
	public static void learnKCXX (){
		try {
			// ��ģ�黬������һ���½ڿ�����ʾ�Ľ���
			for (int i = 0; i < 6; i++) {
				SetApp.swipeSection("down", 2000);
			}
			//��ƪ��ѧϰ
			videoSet.watchVideo(AppData.childNameList.get(0));
			Thread.sleep(videoSet.timeToInt(videoSet.getVideoTotalTime())+5000);
			//�����½ڵ�ѧϰ
			for (int i = 1; i < AppData.childNameList.size(); i++) {
				//�ۿ���Ƶ
				videoSet.watchVideo(AppData.childNameList.get(i));
				//��֤����
				SetApp.checkFace();
				//�ȴ���Ƶʱ��+15s
				Thread.sleep(videoSet.timeToInt(videoSet.getVideoTotalTime())+15000);
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
