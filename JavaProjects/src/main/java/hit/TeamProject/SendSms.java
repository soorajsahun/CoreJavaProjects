package hit.TeamProject;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SendSms {
	static int otp;
	//Enter your Twilio ACCOUNT_SID & AUTH_TOKEN
	public static final String ACCOUNT_SID ="ACebb31a3f3f3106b84bf0f5380edbae08";
	public static final String AUTH_TOKEN ="27bc94a76e66cd95ab63fb8a98a3f9bc";
	public static void sendSms() throws Exception {
		int phoneNumberCount=1;
		ArrayList<String> phoneNumbersList=new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter your Phone Number:");
		for (int i = 0; i < phoneNumberCount; i++) {
			phoneNumbersList.add(input.nextLine());
		}
		
		Random random=new Random();
		otp=1000+random.nextInt(999);
		StringBuilder sb=new StringBuilder("Your OTP is:");
		sb.append(otp);
		try {
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			
			for (String phoneNumber : phoneNumbersList) {
				//In Message include the to Phone Number, Message & From Phone Number(Twilio account)
				Message message = Message.creator(new com.twilio.type.PhoneNumber("+91" + phoneNumber),
						new com.twilio.type.PhoneNumber("+1 772 320 5742"), "\n"+sb).create();
				//Get the Transaction Id
				System.out.println("Session ID/String Identifier (SID): "+message.getSid());
				System.out.println("SMS Sent Successfully to :"+phoneNumber);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}


