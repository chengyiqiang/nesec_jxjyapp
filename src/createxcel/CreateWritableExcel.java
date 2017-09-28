package createxcel;

import java.io.File;

import appdata.AppData;
import jxl.Workbook;
import jxl.write.WritableWorkbook;

public class CreateWritableExcel {
	//��ȡ���еĲ�������Excel
	public static Workbook wb = null;
	//�����ɱ༭��Excel����������������
	public static WritableWorkbook wwb = null;
	
	public static void createExcel(){
		try {
			wb = Workbook.getWorkbook(new File(AppData.path+".xls"));
			wwb = Workbook.createWorkbook(new File(AppData.path+"_Result.xls"), wb);
		} catch (Exception e) {
			System.out.println("��ȡ��������Excel����");
			e.printStackTrace();
		}
	}
}
