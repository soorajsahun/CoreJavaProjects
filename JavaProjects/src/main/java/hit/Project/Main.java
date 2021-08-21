package hit.Project;
//This file Requires CreateExcel,SendSms.java and SendEmail.java to execute the program.
public class Main {
	public static void main(String[] args)throws Exception {	
		//Create Excel
		CreateExcel.callCreateExcel();
		System.out.println();		
//		
//		//Send Sms
		SendSms.sendSms();
		System.out.println();
		
		//Send mail
		SendEmail.callSendMailFromGmail();
		System.out.println();
		
		System.out.println("All done!!!!");
	}
}
