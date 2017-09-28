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
	
	//单选选择A/B/C/D对应1234
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
				System.out.println("answer参数必须为1234");
				break;
			}
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("单选点击选项异常");
		}
		
	}
	//多选并点击确定
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
//			//点击确定
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("多选点击选项异常");
//		}
//	}
	//点击上一题/下一题     通过next/last
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
				System.out.println("NextOrLast参数必须为next或last");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("点击上一题/下一题异常");
		}
	}
	//获取倒计时
	public static String getTimeCount (){
		System.out.println(SetApp.getElementText(By.id("com.nesec.jxjy_phone:id/tv_count_down")));
		return SetApp.getElementText(By.id("com.nesec.jxjy_phone:id/tv_count_down"));
	}
	//点击提交
	public static void commit(){
		try {
			SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_commit")).click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("点击提交异常");
		}
	}
	//获取题目
	public static String getAPPTitle(){
		String titleString = "获取题目失败";
		try {
			Thread.sleep(2000);
			titleString = SetApp.getElementText(By.id("com.nesec.jxjy_phone:id/tv_question_body"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取题目异常");
		}
		return titleString;
	}
	//根据题目获取答案
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
	
	
	//根据当前培训类型获取所有题目2或者答案3
	public static List<String> getTitleOrAnswerList(int TitleOrAnswer){	
		//根据培训类型获取对应题库
		if(AppData.jxjyType.equals("客运")){
			CreateWritableSheet.getSheet("客运题库");
		}else if (AppData.jxjyType.equals("货运")){
			CreateWritableSheet.getSheet("货运题库");
		}else if (AppData.jxjyType.equals("危运运")){
			CreateWritableSheet.getSheet("危运题库");
		}
		jxl.Cell[] columnTitleCells = CreateWritableSheet.sheet.getColumn(TitleOrAnswer);
		List<String> columnTitleList = new ArrayList<String>();
		for (int i = 1; i < columnTitleCells.length; i++) {
			columnTitleList.add(columnTitleCells[i].getContents());
		}
		return columnTitleList;
	}
	
}
