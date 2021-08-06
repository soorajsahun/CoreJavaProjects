package hit.PdfECertificateProject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;

public class PdfECertificateCreation3 {
	public static void main(String[] args)throws Exception {
		
		// Enter a valid Path with FileName
		String pdfName="E:\\PdfECertificateCreation\\pdfECertificate3.pdf";
		
		// Create a empty PDF
		createEmptyCertificatePdf();
		
		// Add Image & Content to Certificate 
		addContentToCertificate(pdfName);
	}
	public static void createEmptyCertificatePdf() throws Exception{
		String temp="E:\\PdfECertificateCreation\\temp.pdf";
		try {
		//steps to create pdf
		// 1. Create document
		Document document=new Document(PageSize.A4);
		
		// 2. Create PdfWriter
		PdfWriter.getInstance(document, new FileOutputStream(temp));
		
		// 3. Open document
		document.open();
		
		// 4. Add content
		Paragraph para=new Paragraph(" ");
		document.add(para);
		
		// 5. Close document
		document.close();
		System.out.println("Empty Pdf Generated");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void addContentToCertificate(String pdfName)throws Exception {
		Scanner input=new Scanner(System.in);
		try {
		//to add content in existing empty pdf-temp.pdf
		// Create Reader Instance
		PdfReader pdfReader=new PdfReader("temp.pdf");//Reads a PDF document.
		
		// Create Stamper Instance
		PdfStamper pdfStamper=new PdfStamper(pdfReader, new FileOutputStream(pdfName));
		//pdfStamper=Applies extra content to the pages of a PDF document
		//FileOutputStream=A file output stream is an output stream for writing data to a File
		
		// Set the Default Zoom-75%
		PdfDestination pdfDest = new PdfDestination(PdfDestination.XYZ, 0, pdfReader.getPageSize(1).getHeight(),
				0.25f);//A PdfDestination is a reference to a location in a PDF file and set the default size
		
		PdfAction action = PdfAction.gotoLocalPage(1, pdfDest, pdfStamper.getWriter());
//		PdfAction=Launches an application or a document->the application to be launched or the document to be opened or printed.
		
		pdfStamper.getWriter().setOpenAction(action);//Inserting attachment in PDF file using Java and iText
		
		// Create the Image Instance
		Image image = Image.getInstance("E:\\PdfECertificateCreation\\3.png");
		
		for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
			if(i==1) {
				// -------------------- Background Image ----------------
				// Add Background Image (put content under)
				PdfContentByte content = pdfStamper.getUnderContent(i);
				image.setAbsolutePosition(0, 0);
				image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
				content.addImage(image);

				// --------------- Header Data ---------------------
				String headerData = "PROJECT COMPLETION CERTIFICATE\n\n";
				// Enable During Final Test (To get input from User)
//					System.out.print("Enter Header Data : ");
//					headerData = input.nextLine();
				setData(pdfStamper, headerData, BaseFont.TIMES_BOLDITALIC, 26, BaseColor.WHITE, 530, 580, 70,
						430);

				// ---------------Main Content ----------------------
				// Add Student Name
				String studentName = "Mr. Name Surname ";
//					System.out.print("Enter Student Name on Certificate (FirstName SurnNme): ");
//					studentName = input.nextLine();
				setData(pdfStamper, studentName, BaseFont.TIMES_BOLDITALIC, 38, BaseColor.ORANGE, 530, 466, 80, 420);

				// ---------Middle Data (Reason of Certificate) ------------------
				// Prize Details & Standard Format of Data
				String midData = "Project on: PDF E-CERTIFICATE CREATION USING JAVA\n\n"
						+ "SuccessfUlly Completed the project and submitted on-time"
						+ " and his participation was very good. The programming language used for this"
						+ " project is \"JAVA\" and \"iText Library\" for creating and Generating the PDF E-Certificate.\n"
						+ "\nWe wish him goo luck for his future.....";
				// Enable During Final Test (To get input from User)
//					System.out.print("Enter the Main Content: : ");
//					midData = input.nextLine();

				setData(pdfStamper, midData, BaseFont.TIMES_BOLDITALIC, 14, BaseColor.WHITE, 530, 260, 70, 390);

				// ----------Footer Data -------------------

				// Add Current Date
				Date date = new Date();
				setData(pdfStamper, date.toString(), BaseFont.TIMES_BOLDITALIC, 16, BaseColor.RED, 240, 220, 90,
						150);
				setData(pdfStamper, "Acquired on", BaseFont.TIMES_BOLDITALIC, 14, BaseColor.WHITE, 240, 140, 90,
						115);

				// Add Name of the Head Person
				String nameOfHeadPerson = "Mr. Name Surname";
				String direc = "Director of Haaris Infotech Pvt. Ltd.";
				// Enable During Final Test (To get input from User)
//					System.out.print("Enter Head/Director Name : ");
//					nameOfHeadPerson = input.nextLine();
//					nameOfHeadPerson = "Mr. "+nameOfHeadPerson;
				setData(pdfStamper, nameOfHeadPerson, BaseFont.TIMES_BOLDITALIC, 18, BaseColor.BLUE, 330, 210, 545,
						140);
				setData(pdfStamper, direc, BaseFont.TIMES_BOLDITALIC, 12, BaseColor.WHITE, 330, 140, 545, 115);
			}
			input.close();
		}

		pdfStamper.close();
		pdfReader.close();

		System.out.println("Successfully added content & created the PDF E-Certificate : " + pdfName);

	} catch (IOException e) {
		System.out.println("Exception while adding Content To Certificate");
		e.printStackTrace();
	}
		
	}
	private static void setData(PdfStamper pdfStamper, String data,String fontType, float fontSize,
			BaseColor fontColor, float lowerX, float lowerY, float upperX, float upperY) throws Exception {
		try {
//
			TextField textField = new TextField(pdfStamper.getWriter(), new Rectangle(lowerX, lowerY, upperX, upperY),
					"newTextField");
			textField.setOptions(TextField.MULTILINE | TextField.READ_ONLY);
			textField.setAlignment(Element.ALIGN_CENTER);
			textField.setTextColor(fontColor);
			BaseFont baseFont = BaseFont.createFont(fontType, BaseFont.WINANSI, BaseFont.EMBEDDED);
			textField.setFont(baseFont);
			textField.setFontSize(fontSize);
			textField.setText(data);
//			// is no longer multiple-line
			pdfStamper.addAnnotation(textField.getTextField(), 1);

		} catch (Exception ex) {
			System.out.println("Exception while Setting Data: " + data);
			ex.printStackTrace();
		}
		
	}
	private static String getDataFromUser(String dataType) {
		String data = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Enter " + dataType);
		data = input.nextLine();
		input.close();
		return data;
	}
	

}
