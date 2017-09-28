package appdata;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.functors.ForClosure;

import bsh.This;

//客货危三个类型课程学习章和节数量及对应关系
public class KCXXInfo {
	//客货危章和节的数量
	public static int kyChapter = 10;
	public static int[] kySection = {1,2,1,2,3,4,6,3,1,2};
	public static int hyChapter = 10;
	public static int[] hySection = {0,1,2,3,4,5,6,7,8,9};
	public static int wyChapter = 10;
	public static int[] wySection = {0,1,2,3,4,5,6,7,8,9};
	public static int chapter;
	public static int[] section = {};
	public static Map<Integer, Integer> chapterAndSection = new HashMap<Integer,Integer>();
	//定义集合
	public static Map<Integer, Integer> kyInfo = new HashMap<Integer,Integer>();
	public static Map<Integer, Integer> hyInfo = new HashMap<Integer,Integer>();
	public static Map<Integer, Integer> wyInfo = new HashMap<Integer,Integer>();
	
	//setMap方法
	static{
		for(int i = 0;i < kyChapter;i++){
			kyInfo.put(i, kySection[i]);
		}
		for(int i = 0;i < hyChapter;i++){
			hyInfo.put(i, hySection[i]);
		}
		for(int i = 0;i < kyChapter;i++){
			wyInfo.put(i, wySection[i]);
		}
	}
	
	static {
		if(AppData.jxjyType.equals("客运")){
			chapter = kyChapter;
			section = kySection;
			chapterAndSection = kyInfo;
		}else if(AppData.jxjyType.equals("货运")){
			chapter = hyChapter;
			section = hySection;
			chapterAndSection = hyInfo;
		}else if(AppData.jxjyType.equals("危运")){
			chapter = wyChapter;
			section = wySection;
			chapterAndSection = wyInfo;
		}
	}
}
