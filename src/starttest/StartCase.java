package starttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import appdata.AppData;
import appdata.KCXXInfo;
import controller.KCXX;
import controller.Login;
import controller.MLKS;
import controller.SetApp;
import controller.videoSet;
import jxl.Sheet;
import jxl.write.Label;
import jxl.write.WritableSheet;
import createxcel.CreateWritableExcel;
import createxcel.CreateWritableSheet;

public class StartCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建测试结果excel
		CreateWritableExcel.createExcel();
				
		//打开APP，登录相关操作
		try{
			//获取登录用例
			CreateWritableSheet.getWritableSheet("登录");
			SetApp.setApp();
			
			Login.getLoginPageInfo();
			Login.switchCity(AppData.province, AppData.city);
			Login.doLogin(AppData.Id, AppData.passwd);
			Login.checkLoginFace();
			Login.getAndClickNotice();
			Login.checkModel();
			
			//根据当前模块选择测试用例
			switch (SetApp.getCurrentModelName()) {
			case "课程学习":
			{
				try{
					//获取章节信息用例，章节信息
					CreateWritableSheet.getWritableSheet(AppData.jxjyType);
					//验证章节信息是否有误
					KCXX.checkChapterName();
					// 将模块滑动到第一个章节可以显示的界面
					for (int i = 0; i < 6; i++) {
						SetApp.swipeSection("down", 2000);
					}
					//视频学习课程学习
					videoSet.watchVideo(AppData.childNameList.get(0));
					System.out.println(videoSet.getVideoHasPlayedTime() + "/" + videoSet.getVideoTotalTime());					
					videoSet.pauseOrPlay();			
					videoSet.pauseOrPlay();
					videoSet.dragSeekBar("front");
					videoSet.dragSeekBar("back");
//					KCXX.learnKCXX();
				    
				}catch (Exception e){
					e.printStackTrace();
				}
		
			}
				break;
			case "习题练习":
			{
				SetApp.getElementByTextAndBy(By.id("com.nesec.jxjy_phone:id/tv_common_title"), "随机练习").click();
				SetApp.checkFace();
				//做10题习题练习
				for (int i = 0; i < 10; i++) {
					MLKS.answerQuestions();
				}
			}
				break;
//			case "课程学习":
//			{
//				KCXX
//			}
//				break;
//			case "课程学习":
//			{
//				KCXX
//			}
//				break;

			default:
				break;
			}
									
			CreateWritableExcel.wwb.write();
			CreateWritableExcel.wwb.close();
			
//			SetApp.driver.closeApp();
//			SetApp.driver.launchApp();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}
