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
	    	System.out.println("appium����ʧ��"+e.getStackTrace());
	    }
	    //��ȡ�豸�ķֱ��ʣ��ߣ���
	    try{
	    	hight = driver.manage().window().getSize().height;
		    width = driver.manage().window().getSize().width;
	    }catch (Exception e){
	    	System.out.println("��ȡ�豸�ķֱ��ʣ��ߣ���ʧ��"+e.getStackTrace());
	    }	    
		//����num�����õ�ǰ��ȵ�ģ���б�
	    if (0 == AppData.num){
	    	AppData.defaultModelList = AppData.modelList0;
	    }else if(1 == AppData.num){
	    	AppData.defaultModelList = AppData.modelList1;
	    }else if(2 == AppData.num){
	    	AppData.defaultModelList = AppData.modelList2;
	    }
	}
		
	//�ȴ�10s�ж�һ��Ԫ���Ƿ����
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
	
	//��ͼ������
	public static void screenShot (String caseName) throws Exception{
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String name = caseName + sdf.format(now) + ".jpg";
		//System.getProperty("user.dir") ��ȡ��ǰ��Ŀ��Ŀ¼
		String path = System.getProperty("user.dir") + "/screenShot/";//�ļ�Ŀ¼
		
		File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(image, new File(path + name));		
	}
	
	//��ȡԪ�ص��ı���Ϣ
	public static String getElementText(By by){
		String text = "";
		try{
			text = driver.findElement(by).getText();	
		}catch (Exception e){
			System.out.println("��ȡԪ���ı��쳣");
			e.printStackTrace();
		}
		return text;
	}
	
	//��ȡ���������е�Ԫ������Ӧ�������ı���Ϣ
	public static List<String> getElementsNames(By by){
		//��ȡ�����Ե�����Ԫ��
		List<WebElement> elementlist = driver.findElements(by);
		List<String> elementsnames = new ArrayList<String>();
		//��ȡ�������Ե������ı���Ϣ
		for(int i = 0; i < elementlist.size(); i++){
			elementsnames.add(elementlist.get(i).getText());
		}		
		return elementsnames;
	}
	
	//�Ƴ�list�����е��ظ�Ԫ��
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
	
	//������Ļ    direction��ʾ����,during��ʾ����ʱ��
	public static void swipeSection (String direction,int during){
		switch (direction) {
		case "left"://���󻬶�
			try{
				driver.swipe(width*3/4, hight/2, width/4, hight/2, during);
			}catch (Exception e){
				driver.swipe(width*3/4, hight/2, width/4, hight/2, during);
			}	
			break;
		case "right"://���һ���
			try{
				driver.swipe(width/4, hight/2, width*3/4, hight/2, during);
			}catch (Exception e){
				driver.swipe(width/4, hight/2, width*3/4, hight/2, during);
			}	
			break;
		case "up"://���ϻ���
			try{
				driver.swipe(width/2, hight*3/4, width/2, hight/4, during);
			}catch (Exception e){
				driver.swipe(width/2, hight*3/4, width/2, hight/4, during);
			}
			break;
		case "down"://���»���
			try{
				driver.swipe(width/2, hight/4, width/2, hight*3/4, during);
			}catch (Exception e){
				driver.swipe(width/2, hight/4, width/2, hight*3/4, during);
			}
			break;

		default:
			System.out.println("diretion��������Ϊ��left,right,up,down��");
			break;
		}
	}
	
	//����ģ��   direction��ʾ����,during��ʾ����ʱ��
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
			System.out.println("diretion��������Ϊ��left,right��");
		}
	}
	
	//ͨ��By��Ԫ��text��ȡԪ��
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
			System.out.println("ͨ��By��Ԫ��text��ȡԪ���쳣");
		}
		return webElement;
	}
	
	//��ȡ��ǰ��ѧģ��
	public static String getCurrentModelName() {
		String currentModelName = "";
		try {
			// ��ģ�黬������һ��ģ�������ʾ�Ľ���
			for (int i = 0; i < 3; i++) {
				swipeModel("right", 2000);
			}
			// ��ȡ��ǰ��ѧģ��
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
			System.out.println("��ȡ��ǰ��ѧģ��ʧ��");
			e.printStackTrace();
		}
		System.out.println("��ǰ��ѧģ��Ϊ" + currentModelName);
		return currentModelName;
	}
	
	//��Ƶ��ʼ�������С�����ʱ���������֤
	public static void checkFace (){
		try{
			Thread.sleep(2000);
			driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_headcheck")).click();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("���������֤�쳣");
		}
	}
	
	//���̵������
	public static void back(){
		try{
			Thread.sleep(1000);
			SetApp.driver.sendKeyEvent(4);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("��������쳣");
		}
	}
	
	//���̵��home
	public static void home(){
		try {
			Thread.sleep(1000);
			SetApp.driver.sendKeyEvent(3);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���home�쳣");
		}
	}
	

	
	//��ȡԪ�ص�����   ��ȡԪ�غ����������y��hight��ֵ
	public static int getMidHight (WebElement WE){
		int hight = -1;
		try {
			hight = WE.getLocation().y + WE.getSize().height/2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hight;
	}
	
	//��ȡ��ǰ��ѧ�γ�ѧϰ���½�(���������⣬������...........)
	public static String getCurrentChapterName() {
		String currentChapterName = "";
		int chapter = KCXXInfo.chapter;
		int[] section = KCXXInfo.section;
		try {
			// ��ģ�黬������һ���½ڿ�����ʾ�Ľ���
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
			System.out.println("��ȡ��ǰ��ѧ�½�ʧ��");
			e.printStackTrace();
		}
		System.out.println("��ǰ��ѧ�γ�ѧϰ�½�Ϊ" + currentChapterName);
		return currentChapterName;
	}
	

}

