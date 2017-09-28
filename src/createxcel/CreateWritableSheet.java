package createxcel;

import java.util.Date;

import appdata.AppData;
import jxl.Sheet;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;


public class CreateWritableSheet {
	//sheet
	public static WritableSheet writableSheet = null;
	public static WritableFont wf = null;
	public static WritableCellFormat wcf = null;
	public static Sheet sheet = null;
	
	//获取sheet,并重置row值,通过这个填写用例执行结果
	public static void getWritableSheet(String sheetname){
		writableSheet = CreateWritableExcel.wwb.getSheet(sheetname);
		AppData.row = 1;
	}

	//获取sheet,并重置row值,通过这个填写用例执行结果
	public static void getSheet(String sheetname){
		sheet = CreateWritableExcel.wb.getSheet(sheetname);
	}
	
	//读取当前sheet中指定单元格内容
	public static String readWritableSheet (int column,int row){
			String content = "";
			try{
				content = writableSheet.getCell(column, row).getContents();
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("读取当前sheet中指定单元格内容异常");
			}
			return content;
		}
		
	//读取当前sheet中指定单元格内容
	public static String readSheet (int column,int row){
		String content = "";
		try{
			content = sheet.getCell(column, row).getContents();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("读取当前sheet中指定单元格内容异常");
		}
		return content;
	}
		
	//设置单元格格式
	public static WritableCellFormat setFormat (String color) {
		//通过color判断写入的字体颜色
		if (color.equals("RED")) {
		// 设置单元格格式为：13号字体，没有斜体，红色字体
			wf = new WritableFont(WritableFont.TIMES, 13, WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED);
			wcf = new WritableCellFormat(wf);
		} else if (color.equals("BLUE")) {
			wf = new WritableFont(WritableFont.TIMES, 13, WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE);
			wcf = new WritableCellFormat(wf);
		} else if (color.equals("BLACK")) {
			wf = new WritableFont(WritableFont.TIMES, 13, WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			wcf = new WritableCellFormat(wf);
		}
		return wcf;				
	}
		
	// 添加单元写入数据--写入测试结果
	public static void addCell(int row, boolean bool, String statue) {
		//用例通过true设置为蓝色，失败false设置为红色
		if (!bool) {
			try {
				writableSheet.addCell(new Label(3, row, bool + "", setFormat("RED")));
			} catch (Exception e) {
				System.out.println("Excel表添加单元格失败！");
			}
		} else {
			try {
				writableSheet.addCell(new Label(3, row, bool + "", setFormat("BLUE")));
			} catch (Exception e) {
				System.out.println("Excel表添加单元格失败！");
			}
		}

		if (statue.equals("Passed")) {
			try {
				writableSheet.addCell(new Label(5, row, statue, setFormat("BLUE")));
			} catch (Exception e) {
				System.out.println("Excel表添加单元格失败！");
			}
		} else {
			try {
				writableSheet.addCell(new Label(5, row, statue,  setFormat("RED")));
			} catch (Exception e) {
				System.out.println("Excel表添加单元格失败！");
			}
		}

		try {
			writableSheet.addCell(new Label(4, row, AppData.sdf.format(new Date())));
		} catch (Exception e) {
			System.out.println("Excel表添加单元格失败！");
		}
	}

}
