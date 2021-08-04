package hit.SmsProject;

import java.util.ArrayList;
import java.util.Scanner;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SendSms {
	//Enter your Twilio ACCOUNT_SID & AUTH_TOKEN
	public static final String ACCOUNT_SID ="ACebb31a3f3f3106b84bf0f5380edbae08";
	public static final String AUTH_TOKEN ="07d1a98f7a0a07eb15f197357a74fbb5";

	public static void main(String[] args) throws Exception {
		int phoneNumberCount=0;
		String messageData = "";
//		Set<String> phoneNumbersSet = new HashSet<String>();
		ArrayList<String> phoneNumbersList=new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		
		System.out.print("How many phone numbers you want to add: ");
		phoneNumberCount = input.nextInt();
		input.nextLine();
		
		System.out.println("Enter " + phoneNumberCount + " Phone Numbers:");
		for (int i = 0; i < phoneNumberCount; i++) {
			phoneNumbersList.add(input.nextLine());
		}
		
		System.out.println("Enter the Message To Send: ");
		messageData = input.nextLine();
		input.close();
		
		try {
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			
			for (String phoneNumber : phoneNumbersList) {
				//In Message include the to Phone Number, Message & From Phone Number(Twilio account)
				Message message = Message.creator(new com.twilio.type.PhoneNumber("+91" + phoneNumber),
						new com.twilio.type.PhoneNumber("+1 772 320 5742"), "\n"+messageData).create();
				//Get the Transaction Id
				System.out.println("Session ID (SID): "+message.getSid());
				System.out.println("SMS Sent Successfully to :"+phoneNumber);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

