package createxcel;

import java.io.File;

import appdata.AppData;
import jxl.Workbook;
import jxl.write.WritableWorkbook;

public class CreateWritableExcel {
	//获取已有的测试用例Excel
	public static Workbook wb = null;
	//创建可编辑的Excel并将测试用例导入
	public static WritableWorkbook wwb = null;
	
	public static void createExcel(){
		try {
			wb = Workbook.getWorkbook(new File(AppData.path+".xls"));
			wwb = Workbook.createWorkbook(new File(AppData.path+"_Result.xls"), wb);
		} catch (Exception e) {
			System.out.println("获取测试用例Excel错误！");
			e.printStackTrace();
		}
	}
}
