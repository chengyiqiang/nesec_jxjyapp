package controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.StaticBucketMap;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;

import jxl.*;
import appdata.AppData;
import bsh.This;

import com.google.common.collect.Table.Cell;

import createxcel.CreateWritableSheet;

public class MLKS {

		public static List<String> titleList = new ArrayList<String>();
		public static List<String> answerList = new ArrayList<String>();
		static{
		titleList = getTitleOrAnswerList(2);
		answerList = getTitleOrAnswerList(3);
	}
	
	//��ѡѡ��A/B/C/D��Ӧ1234
	public static void chooseSingleAnswer(String answer){
		try {
			Thread.sleep(3000);
			switch (answer) {
			case "A":
				SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_work_attitude1")).click();
				break;
			case "B":
				SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_work_attitude2")).click();
				break;
			case "C":
				SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_work_attitude3")).click();
				break;
			case "D":
				SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_work_attitude4")).click();
				break;
			default:
				System.out.println("answer��������Ϊ1234");
				break;
			}
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��ѡ���ѡ���쳣");
		}
		
	}
	//��ѡ�����ȷ��
//	public static void chooseMultipleAnswers(String answer){
//		try {
//			Thread.sleep(3000);
//			if(answer.contains("A")){
//				SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_work_attitude1")).click();
//			}
//			if(answer.contains("B")){
//				SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_work_attitude2")).click();
//			}
//			if(answer.contains("C")){
//				SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_work_attitude3")).click();
//			}
//			if(answer.contains("D")){
//				SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_work_attitude4")).click();
//			}
//			//���ȷ��
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("��ѡ���ѡ���쳣");
//		}
//	}
	//�����һ��/��һ��     ͨ��next/last
	public static void getNextOrLastQuestion(String NextOrLast){
		try {
			Thread.sleep(3000);
			switch (NextOrLast) {
			case "next":
				SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/tv_next_question")).click();
				break;
			case "last":
				SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/tv_last_question")).click();
				break;
			default:
				System.out.println("NextOrLast��������Ϊnext��last");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�����һ��/��һ���쳣");
		}
	}
	//��ȡ����ʱ
	public static String getTimeCount (){
		System.out.println(SetApp.getElementText(By.id("com.nesec.jxjy_phone:id/tv_count_down")));
		return SetApp.getElementText(By.id("com.nesec.jxjy_phone:id/tv_count_down"));
	}
	//����ύ
	public static void commit(){
		try {
			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_commit")).click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("����ύ�쳣");
		}
	}
	//��ȡ��Ŀ
	public static String getAPPTitle(){
		String titleString = "��ȡ��Ŀʧ��";
		try {
			Thread.sleep(2000);
			titleString = SetApp.getElementText(By.id("com.nesec.jxjy_phone:id/tv_question_body"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��ȡ��Ŀ�쳣");
		}
		return titleString;
	}
	//������Ŀ��ȡ��
	public static String getAnswer(){
		String APPTitleString = getAPPTitle();
		String answerString = "";
		try {
			for (int i = 0; i < titleList.size(); i++) {
				if(APPTitleString.contains(titleList.get(i))){
					answerString = answerList.get(i);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answerString;
	}
	
	
	//���ݵ�ǰ��ѵ���ͻ�ȡ������Ŀ2���ߴ�3
	public static List<String> getTitleOrAnswerList(int TitleOrAnswer){	
		//������ѵ���ͻ�ȡ��Ӧ���
		if(AppData.jxjyType.equals("����")){
			CreateWritableSheet.getSheet("�������");
		}else if (AppData.jxjyType.equals("����")){
			CreateWritableSheet.getSheet("�������");
		}else if (AppData.jxjyType.equals("Σ����")){
			CreateWritableSheet.getSheet("Σ�����");
		}
		jxl.Cell[] columnTitleCells = CreateWritableSheet.sheet.getColumn(TitleOrAnswer);
		List<String> columnTitleList = new ArrayList<String>();
		for (int i = 1; i < columnTitleCells.length; i++) {
			columnTitleList.add(columnTitleCells[i].getContents());
		}
		return columnTitleList;
	}
	
}
