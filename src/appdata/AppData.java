package appdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


//�ͻ������ݼ���
public class AppData {
	
	//������Ϣ
	//�豸����
	public static String devicesName = "faf6d064";
	//�豸����ƽ̨
	public static String platformName = "Android";
//	public static String platformVersion = "";
	//apk����
	public static String appPackage = "com.nesec.jxjy_phone";
	//apk��activity
	public static String appActivity = "com.nesec.jxjy.LoginActivity";
	//�豸��׿�汾
	public static String version = "4.4.4";
	public static String urlString = "http://127.0.0.1:4723/wd/hub";

	//�������ڸ�ʽ
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//��ʼ�к���
	public static int row = 1;
	public static int column = 0;
	
	
	
	//�ͷ��绰  //APP�汾��
	public static String phoneNumber = "0755-25887803";//
	public static String APPVersion = "v1.1.9 Debug";
	public static String noticeContent = "Ϊ���Ϲ���ʻԱ���Ѽ�������ѧϰ�����е�����ȫ�������ڿ�������·���а�ȫ��������½�������Զ����ѵ��һ�����֣�ѧʱ���ϡ�";
	//ʡ		//��
	public static String province = "����ʡ";
	public static String city = "����ʡ����";
	//�����������ͣ�����  ����  Σ��  
	public static String jxjyType = "����"; 
	//�Ƿ���Ҫ��Ԫ����
	public static boolean isdyexam = false;
	/* һ��һ�ε�һ��  0
	 * һ��һ�εڶ���  1
	 * ����һ��             2
	 */
	public static int num = 2;
	public static int totalModel = 10;
	//�γ�ѧϰ�½��б�
	public static List<String> groupNameList = new ArrayList<String>();
	public static List<String> childNameList = new ArrayList<String>();
	//ģ������
	public static String[] defaultModelList;
	public static String[] modelList0 = {"�γ�ѧϰ","�ص�ع�"};
	public static String[] modelList1 = {"�γ�ѧϰ","�ص�ع�"};
	public static String[] modelList2 = {"�γ�ѧϰ","�ص�ع�","��ͨ��־","���ɷ���","��ʻ����","�������","����֪ʶ","��ȫ����","ϰ����ϰ","ģ�����"};
	//�˺�	//����
	public static String Id = "429004199909090914";
	public static String passwd = "222222";
	//����·��
	public static String path = "D:\\Result\\��������";
	
}
