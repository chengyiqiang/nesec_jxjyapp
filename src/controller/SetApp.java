package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.apache.commons.collections.functors.ForClosure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import appdata.AppData;
import appdata.KCXXInfo;

import com.gargoylesoftware.htmlunit.javascript.host.Element;
import com.sun.jna.FromNativeContext;

import createxcel.CreateWritableSheet;

public class SetApp {
	public static DesiredCapabilities dcp;
	public static AppiumDriver driver ;
	public static TouchAction touchAction;
	public static int hight;
	public static int width;
	
	public static void setApp(){
		dcp = new DesiredCapabilities();
		dcp.setCapability("deviceName",AppData.deviceName);
		dcp.setCapability("platformName",AppData.platformName);
		dcp.setCapability("platformVersion",AppData.version);
		dcp.setCapability("appPackage",AppData.appPackage);
		dcp.setCapability("appActivity",AppData.appActivity);
		
	    dcp.setCapability("unicodeKeyboard", "True");  
	    dcp.setCapability("resetKeyboard", "True"); 
	    dcp.setCapability("newCommandTimeout", "120");
	    
	    try{
		driver = new AppiumDriver(new URL(AppData.urlString), dcp);
		touchAction = new TouchAction(driver);
	    }catch (Exception e){
	    	System.out.println("appium启动失败"+e.getStackTrace());
	    }
	    //获取设备的分辨率（高，宽）
	    try{
	    	hight = driver.manage().window().getSize().height;
		    width = driver.manage().window().getSize().width;
	    }catch (Exception e){
	    	System.out.println("获取设备的分辨率（高，宽）失败"+e.getStackTrace());
	    }	    
		//根据num来设置当前年度的模块列表
	    if (0 == AppData.num){
	    	AppData.defaultModelList = AppData.modelList0;
	    }else if(1 == AppData.num){
	    	AppData.defaultModelList = AppData.modelList1;
	    }else if(2 == AppData.num){
	    	AppData.defaultModelList = AppData.modelList2;
	    }
	}
		
	//等待10s判断一个元素是否出现
	public static boolean waitForElement(By by) throws Exception {
		boolean result = false;
		for (int i = 1; i < 10; i++) {
			try {
				driver.findElement(by);				
				result = true;
				break;
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		return result;
	}
	
	//截图并保存
	public static void screenShot (String caseName) throws Exception{
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String name = caseName + sdf.format(now) + ".jpg";
		//System.getProperty("user.dir") 获取当前项目的目录
		String path = System.getProperty("user.dir") + "/screenShot/";//文件目录
		
		File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(image, new File(path + name));		
	}
	
	//获取元素的文本信息
	public static String getElementText(By by){
		String text = "";
		try{
			text = driver.findElement(by).getText();	
		}catch (Exception e){
			System.out.println("获取元素文本异常");
			e.printStackTrace();
		}
		return text;
	}
	
	//获取该属性所有的元素所对应的所有文本信息
	public static List<String> getElementsNames(By by){
		//获取该属性的所有元素
		List<WebElement> elementlist = driver.findElements(by);
		List<String> elementsnames = new ArrayList<String>();
		//获取所有属性的所有文本信息
		for(int i = 0; i < elementlist.size(); i++){
			elementsnames.add(elementlist.get(i).getText());
		}		
		return elementsnames;
	}
	
	//移除list集合中的重复元素
	public static List removeDuolicateWithOrder(List list){
		Set set = new HashSet();
		List newlist = new ArrayList();
		for (Iterator iter = list.iterator();iter.hasNext();){
			Object element = iter.next();
			if (set.add(element))newlist.add(element);
		}
		list.clear();
		list.addAll(newlist);
		return newlist;
	}
	
	//滑动屏幕    direction表示方向,during表示滑动时间
	public static void swipeSection (String direction,int during){
		switch (direction) {
		case "left"://向左滑动
			try{
				driver.swipe(width*3/4, hight/2, width/4, hight/2, during);
			}catch (Exception e){
				driver.swipe(width*3/4, hight/2, width/4, hight/2, during);
			}	
			break;
		case "right"://向右滑动
			try{
				driver.swipe(width/4, hight/2, width*3/4, hight/2, during);
			}catch (Exception e){
				driver.swipe(width/4, hight/2, width*3/4, hight/2, during);
			}	
			break;
		case "up"://向上滑动
			try{
				driver.swipe(width/2, hight*3/4, width/2, hight/4, during);
			}catch (Exception e){
				driver.swipe(width/2, hight*3/4, width/2, hight/4, during);
			}
			break;
		case "down"://向下滑动
			try{
				driver.swipe(width/2, hight/4, width/2, hight*3/4, during);
			}catch (Exception e){
				driver.swipe(width/2, hight/4, width/2, hight*3/4, during);
			}
			break;

		default:
			System.out.println("diretion参数必须为“left,right,up,down”");
			break;
		}
	}
	
	//滑动模块   direction表示方向,during表示滑动时间
	public static void swipeModel (String direction,int during){
		int hight = getMidHight(driver.findElement(By.id("com.nesec.jxjy_phone:id/indicator")));
		if (direction.equals("left")){
			try{
				driver.swipe(500, hight, 90, hight, during);
			}catch (Exception e){
				driver.swipe(500, hight, 90, hight, during);
			}	
		}else if(direction.equals("right")){
			try{
				driver.swipe(90, hight, 500, hight, during);
			}catch (Exception e){
				driver.swipe(90, hight, 500, hight, during);
			}	
		}else{
			System.out.println("diretion参数必须为“left,right”");
		}
	}
	
	//通过By和元素text获取元素
	public static WebElement getElementByTextAndBy (By by,String elementtext){
		WebElement webElement = null;
		try{
			Thread.sleep(3000);
			List<WebElement> elements = driver.findElements(by);
			for(int i = 0;i < elements.size();i++){
				if (elements.get(i).getText().equals(elementtext)){
					webElement = elements.get(i);
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("通过By和元素text获取元素异常");
		}
		return webElement;
	}
	
	//获取当前所学模块
	public static String getCurrentModelName() {
		String currentModelName = "";
		try {
			// 将模块滑动到第一个模块可以显示的界面
			for (int i = 0; i < 3; i++) {
				swipeModel("right", 2000);
			}
			// 获取当前所学模块
			for (int i = 0; i < AppData.defaultModelList.length; i++) {
				Thread.sleep(2000);
				WebElement webElement = getElementByTextAndBy(
						By.xpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView"),
						AppData.defaultModelList[i]);
				if (webElement.isEnabled()) {
					webElement.click();				
					currentModelName = AppData.defaultModelList[i];
				}else {
					currentModelName = AppData.defaultModelList[i-1];
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("获取当前所学模块失败");
			e.printStackTrace();
		}
		System.out.println("当前所学模块为" + currentModelName);
		return currentModelName;
	}
	
	//视频开始、过程中、结束时点击人脸验证
	public static void checkFace (){
		try{
			Thread.sleep(2000);
			driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_headcheck")).click();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("点击人脸验证异常");
		}
	}
	
	//键盘点击返回
	public static void back(){
		try{
			Thread.sleep(1000);
			SetApp.driver.sendKeyEvent(4);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("点击回退异常");
		}
	}
	
	//键盘点击home
	public static void home(){
		try {
			Thread.sleep(1000);
			SetApp.driver.sendKeyEvent(3);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("点击home异常");
		}
	}
	

	
	//获取元素的坐标   获取元素横向中心轴的y（hight）值
	public static int getMidHight (WebElement WE){
		int hight = -1;
		try {
			hight = WE.getLocation().y + WE.getSize().height/2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hight;
	}
	
	//获取当前所学课程学习的章节(方法有问题，完善中...........)
	public static String getCurrentChapterName() {
		String currentChapterName = "";
		int chapter = KCXXInfo.chapter;
		int[] section = KCXXInfo.section;
		try {
			// 将模块滑动到第一个章节可以显示的界面
			for (int i = 0; i < 6; i++) {
				swipeSection("down", 2000);
			}

			for (int i = 0; i < chapter; i++) {
				for (int j = 0; j < section[i]; j++) {
					int k = 0;
					for (int m = 0; m < i; m++) {
						k = k + section[m];
					}
					if (getElementByTextAndBy(By.id("com.nesec.jxjy_phone:id/tv_child_title"),AppData.childNameList.get(k + j)).isEnabled()) {
						currentChapterName = AppData.childNameList.get(k + j);
						System.out.println(k+j);//
					} else {
						return currentChapterName;
					}
				}
				Thread.sleep(2000);
				getElementByTextAndBy(By.id("com.nesec.jxjy_phone:id/tv_group_title"),AppData.groupNameList.get(i)).click();
			}
		} catch (Exception e) {
			System.out.println("获取当前所学章节失败");
			e.printStackTrace();
		}
		System.out.println("当前所学课程学习章节为" + currentChapterName);
		return currentChapterName;
	}
	

}

