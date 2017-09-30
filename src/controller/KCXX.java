package controller;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import appdata.AppData;

import com.sun.jna.FromNativeContext;

import createxcel.CreateWritableSheet;

//课程学习用例
public class KCXX {
	
	// 获取课程学习章节并验证--只判断了具体章节是否正确，没有判断章节标题是否正确
	public static void checkChapterName() {
		try {
			// 将模块滑动到第一个章节可以显示的界面
			for (int i = 0; i < 6; i++) {
				SetApp.swipeSection("down", 2000);
			}
			// 获取章节标题和章节
			for (int i = 0; i < 7; i++) {
				AppData.groupNameList.addAll(SetApp.getElementsNames(By.id("com.nesec.jxjy_phone:id/tv_group_title")));
				AppData.childNameList.addAll(SetApp.getElementsNames(By.id("com.nesec.jxjy_phone:id/tv_child_title")));
				SetApp.swipeSection("up", 2000);
			}
			// 移除重复标题和章节
			AppData.groupNameList = SetApp.removeDuolicateWithOrder(AppData.groupNameList);
			AppData.childNameList = SetApp.removeDuolicateWithOrder(AppData.childNameList);
			// 对比章节信息是否正确
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
	
	
	
	
	//课程学习从开篇学习到最后一节.............有问题，问题很多，解决中
	public static void learnKCXX (){
		try {
			// 将模块滑动到第一个章节可以显示的界面
			for (int i = 0; i < 6; i++) {
				SetApp.swipeSection("down", 2000);
			}
			//开篇的学习
			videoSet.watchVideo(AppData.childNameList.get(0));
			Thread.sleep(videoSet.timeToInt(videoSet.getVideoTotalTime())+5000);
			//其他章节的学习
			for (int i = 1; i < AppData.childNameList.size(); i++) {
				//观看视频
				videoSet.watchVideo(AppData.childNameList.get(i));
				//验证人脸
				SetApp.checkFace();
				//等待视频时长+15s
				Thread.sleep(videoSet.timeToInt(videoSet.getVideoTotalTime())+15000);
				//验证人脸
				SetApp.checkFace();
				//课后练习内容
				if(AppData.isdyexam){
					for (int j = 0; j < 10; j++) {
						MLKS.chooseSingleAnswer(MLKS.getAnswer());
					}
					SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_commit")).click();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("课程学习从开篇学习到最后一节异常");
		}
		
		
	}
	

}
