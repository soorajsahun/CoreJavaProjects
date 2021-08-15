package hit.TeamProject;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class EmailSmsPdfExcel {
	public static void main(String[] args)throws Exception {
		Scanner scan=new Scanner(System.in);
		
		String subject ="Welocome To Vijay Tutorials\n";
		String textBody = "<h1>Your Progress Report Card<h1/>";
		String myEmailAccount="suraj.sahu.9484@gmail.com";
		String password="9819675309";
		String fileName="E:\\PdfECertificateCreation\\ProgressReport.xls";
		
		System.out.println("Do you want your progress report card via mail?YES/NO");
		String response=scan.next();
		//Create Excel
		if(response.equalsIgnoreCase("yes")) {
			CreateExcel.createExcel(fileName);
		}
		else {
			System.out.println("Thank you!");
			System.exit(0);
		}
		
		
		//Send Sms
		SendSms.sendSms();
		System.out.println("Type OTP Received on number");

		//Send mail
		int otp=scan.nextInt();
		if(otp==SendSms.otp) {
			SendAttachmentByMail.sendMailFromGmail(subject, textBody, myEmailAccount, password);
		}
		else {
			System.err.println("enter valid otp");
		}
		
		scan.close();
	}
}
