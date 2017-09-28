package starttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import appdata.AppData;
import appdata.KCXXInfo;
import controller.KCXX;
import controller.Login;
import controller.MLKS;
import controller.SetApp;
import jxl.Sheet;
import jxl.write.Label;
import jxl.write.WritableSheet;
import createxcel.CreateWritableExcel;
import createxcel.CreateWritableSheet;

public class StartCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//�������Խ��excel
		CreateWritableExcel.createExcel();
				
		//��APP����¼��ز���
		try{
			//��ȡ��¼����
			CreateWritableSheet.getWritableSheet("��¼");
			SetApp.setApp();
			
			Login.getLoginPageInfo();
			Login.switchCity(AppData.province, AppData.city);
			Login.doLogin(AppData.Id, AppData.passwd);
			Login.checkLoginFace();
			Login.getAndClickNotice();
			Login.checkModel();
			
			//���ݵ�ǰģ��ѡ���������
			switch (SetApp.getCurrentModelName()) {
			case "�γ�ѧϰ":
			{
				try{
					//��ȡ�½���Ϣ�������½���Ϣ
					CreateWritableSheet.getWritableSheet(AppData.jxjyType);
					//��֤�½���Ϣ�Ƿ�����
					KCXX.checkChapterName();
					// ��ģ�黬������һ���½ڿ�����ʾ�Ľ���
					for (int i = 0; i < 6; i++) {
						SetApp.swipeSection("down", 2000);
					}
					//��Ƶѧϰ�γ�ѧϰ
					KCXX.watchVideo(AppData.childNameList.get(0));
					System.out.println(KCXX.getVideoHasPlayedTime() + "/" + KCXX.getVideoTotalTime());					
				    KCXX.pauseOrPlay();			
				    KCXX.pauseOrPlay();
				    KCXX.dragSeekBar("front");
				    KCXX.dragSeekBar("back");
//					KCXX.learnKCXX();
				    
				}catch (Exception e){
					e.printStackTrace();
				}
		
			}
				break;
//			case "�γ�ѧϰ":
//			{
//				KCXX
//			}
//				break;
//			case "�γ�ѧϰ":
//			{
//				KCXX
//			}
//				break;
//			case "�γ�ѧϰ":
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
