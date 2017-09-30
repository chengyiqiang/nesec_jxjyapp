package controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class videoSet {
	
	//点击视频并开始学习
		public static void watchVideo (String sectionName){
			try{
				SetApp.getElementByTextAndBy(By.id("com.nesec.jxjy_phone:id/tv_child_title"), sectionName).click();
				Thread.sleep(5000);
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("点击视频并学习异常");
			}
		}
		
		//获取视频播放过程中的元素
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
		
		//视频暂停或开始
		public static void pauseOrPlay() {
			try{
				getVideoElement(By.id("com.nesec.jxjy_phone:id/btn_play")).click();
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("视频暂停或播放异常");
			}
		}
		
		//获取视频已播放时间
		public static String getVideoHasPlayedTime() {
			String videoHasPlayedTime = "获取已播时长失败";
			try{
				videoHasPlayedTime = getVideoElement(By.id("com.nesec.jxjy_phone:id/tv_has_played_time")).getText();
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("获取已播时长异常");
			}
			return videoHasPlayedTime;
		}
		
		//获取视频时长
		public static String getVideoTotalTime() {
			String videoTotalTime = "获取视频总时长失败";
			try{
				videoTotalTime = getVideoElement(By.id("com.nesec.jxjy_phone:id/tv_duration_alltime")).getText();
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("获取视频总时长异常");
			}
			return videoTotalTime;
		}
		
		//视频内置返回键
		public static void videoBack(){
			try{
				getVideoElement(By.id("com.nesec.jxjy_phone:id/imgbtn_back")).click();
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("点击视频内置返回键异常");
			}
		}
		
		//获取视频标题
		public static String getVideoTitle(){
			String videoTitle = "获取视频标题失败";
			try{
				videoTitle = getVideoElement(By.id("com.nesec.jxjy_phone:id/tv_video_title")).getText();
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("获取视频标题异常");
			}
			return videoTitle;
		}
		
		//拖动进度条
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
					System.out.println("\"backOrFront\"参数必须为back或front");
				}			
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("拖动进度条异常");
			}
		}

	//将mm:ss字符串转换为毫秒数
	public static int timeToInt(String time){
		int result = -1;
		try {
			String[] timeString = time.split(":");
			result = (Integer.parseInt(timeString[0])*60 + Integer.parseInt(timeString[1]))*1000;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("将mm:ss字符串转换为毫秒数异常");
		}
		return result;
	}
}
