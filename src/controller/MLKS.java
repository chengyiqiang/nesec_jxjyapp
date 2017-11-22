package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.StaticBucketMap;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;

import jxl.*;
import appdata.AppData;
import bsh.This;

import com.google.common.collect.Table.Cell;

import createxcel.CreateWritableSheet;

public class MLKS {

		public static Map<String, String> titleAndAnswerMap = new HashMap<String, String>();
		static{
			titleAndAnswerMap = getTitleAndAnswerList(2,3);
	}
		
	//根据题目选择答案
	public static void answerQuestions (){
		try {
			String title = getAPPTitle();
			String answer = getAnswer(title);
			//判断是单选还是多选
			if (answer.length() == 1 ){    //单选
				chooseSingleAnswer(answer);
			}else if (answer.length()>1){   //多选
				chooseMultipleAnswers(answer);
			}else{   //其他错误类型
				System.out.println("题目答案错误");
			}
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("根据题目选择答案异常");
		}
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
	public static void chooseMultipleAnswers(String answer){
		try {
			Thread.sleep(3000);
			if(answer.contains("A")){
				SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_work_attitude1")).click();
			}
			if(answer.contains("B")){
				SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_work_attitude2")).click();
			}
			if(answer.contains("C")){
				SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_work_attitude3")).click();
			}
			if(answer.contains("D")){
				SetApp.driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_work_attitude4")).click();
			}
			//点击确定
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("多选点击选项异常");
		}
	}
	
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
			System.out.println(titleString);
			titleString = transTitle(titleString);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取题目异常");
		}
		return titleString;
	}
	
	//题目处理1.去除题号； 2.将（）替换为____
	public static String transTitle (String title){
		String newtitle = "";
		String[] titles = title.split("、", 2);
		
		newtitle = titles[1].replaceAll("（）", "____");
		return newtitle;
	}
	
	
	//根据题目获取答案
	public static String getAnswer(String TitleString){

		String answerString = "";
		try {
			answerString  = titleAndAnswerMap.get(TitleString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answerString;
	}
	
	
	//根据当前培训类型获取所有题目2或者答案3,分别用list来承载
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
	
	//根据当前培训类型获取所有题目2和答案3,用map装载
	public static Map<String, String> getTitleAndAnswerList(int title,int answer){	
		Map<String, String> titleAndAnswerMap = new HashMap<String, String>();
		//根据培训类型获取对应题库
		if(AppData.jxjyType.equals("客运")){
			CreateWritableSheet.getSheet("客运题库");
		}else if (AppData.jxjyType.equals("货运")){
			CreateWritableSheet.getSheet("货运题库");
		}else if (AppData.jxjyType.equals("危运运")){
			CreateWritableSheet.getSheet("危运题库");
		}
		jxl.Cell[] columnTitleCells = CreateWritableSheet.sheet.getColumn(title);
		jxl.Cell[] columnAnswerCells = CreateWritableSheet.sheet.getColumn(answer);
		
		if(columnTitleCells.length == columnAnswerCells.length){
			for (int i = 1; i < columnTitleCells.length; i++) {
				titleAndAnswerMap.put(columnTitleCells[i].getContents(), columnAnswerCells[i].getContents());
			}
		}else{
			System.out.println("题库异常,答案与题目数量不匹配");
		}
		
		return titleAndAnswerMap;
	}
	
}
