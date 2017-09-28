package appdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


//客户端数据集合
public class AppData {
	
	//数据信息
	//设备名称
	public static String devicesName = "faf6d064";
	//设备操作平台
	public static String platformName = "Android";
//	public static String platformVersion = "";
	//apk包名
	public static String appPackage = "com.nesec.jxjy_phone";
	//apk主activity
	public static String appActivity = "com.nesec.jxjy.LoginActivity";
	//设备安卓版本
	public static String version = "4.4.4";
	public static String urlString = "http://127.0.0.1:4723/wd/hub";

	//设置日期格式
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//初始行和列
	public static int row = 1;
	public static int column = 0;
	
	
	
	//客服电话  //APP版本号
	public static String phoneNumber = "0755-25887803";//
	public static String APPVersion = "v1.1.9 Debug";
	public static String noticeContent = "为保障广大驾驶员朋友继续教育学习过程中的人身安全，请勿在开车、走路等有安全隐患情况下进行网络远程培训；一经发现，学时作废。";
	//省		//市
	public static String province = "河南省";
	public static String city = "河南省滑县";
	//继续教育类型：客运  货运  危运  
	public static String jxjyType = "客运"; 
	//是否需要单元测试
	public static boolean isdyexam = false;
	/* 一年一次第一年  0
	 * 一年一次第二年  1
	 * 两年一次             2
	 */
	public static int num = 2;
	public static int totalModel = 10;
	//课程学习章节列表
	public static List<String> groupNameList = new ArrayList<String>();
	public static List<String> childNameList = new ArrayList<String>();
	//模块名称
	public static String[] defaultModelList;
	public static String[] modelList0 = {"课程学习","重点回顾"};
	public static String[] modelList1 = {"课程学习","重点回顾"};
	public static String[] modelList2 = {"课程学习","重点回顾","交通标志","法律法规","驾驶技巧","心理调节","急救知识","安全教育","习题练习","模拟测试"};
	//账号	//密码
	public static String Id = "429004199909090914";
	public static String passwd = "222222";
	//用例路径
	public static String path = "D:\\Result\\继续教育";
	
}
