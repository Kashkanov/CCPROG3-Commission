package records;

import java.util.*;

import java.io.FileOutputStream;

import com.itextpdf.text.*;
import manager.main_Menu;
import request.LabRequest;
import service.Service;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfChunk;

import records.ManagePatientRecords;

import java.io.*;

//TODO: search again should return to search function

public class SearchPatientRecord {
	main_Menu menu = new main_Menu();

	// Methods
	private void DisplayPatientRecord(String search, ArrayList<String> matches, ArrayList<Patient> patients, ArrayList<Service> services) {
		// String filepath = "Patients.txt";
		// Scanner sc = new Scanner(new File(filepath));
		String choice = "";
		Patient ptn = patients.get(0);
		Service svc = services.get(0);
		LabRequest req = new LabRequest();


		ArrayList<LabRequest> requests = new ArrayList<LabRequest>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Patient's UID" + "\t\t" + "Last Name" + "\t\t" + "First Name" + "\t\t" + "Middle Name"
				+ "\t\t" + "Birthday" + "\t\t" + "Gender" + "\t\t" + "Address" + "\t\t" + "Phone Number" + "\t\t"
				+ "National ID no.");

		if (!matches.isEmpty()) {
			for (int i = 0; i < matches.size(); i++) {
				String record = matches.get(i);
				String[] res = record.split(";");
				System.out.println(res[0] + "\t\t" + res[1] + "\t\t" + res[2] + "\t\t\t" + res[3] + "\t\t\t" + res[4]
						+ "\t\t" + res[5] + "\t\t" + res[6] + "\t\t" + res[7] + "\t\t" + res[8]);
			}

		} else/* if (matches.isEmpty()) */ {
			System.out.println("No Record found.");
			System.out.println("");
			System.out.println("Would you like to..");
			System.out.println("[1] Search Again");
			System.out.println("[2] Return to Main Menu");
			System.out.print("Select a transaction: ");
			int transaction = scan.nextInt();

			switch (transaction) {
				case 1:
					ManagePatientRecords mng = new ManagePatientRecords();
					System.out.println("Back to Search Patient Record");
					mng.ProcessPatientRecord(patients, services, 4);
					break;
				case 2:
					main_Menu.main(null);
					break;
			}
		}
		System.out.println("");

		/* only asks for UID if results are more than 1 */
		if (matches.size() > 1) {
			System.out.println("");
			System.out.println("Enter the patient's UID that you want to display: ");
			String patientUID = scan.nextLine();
			
		
			System.out.println("");
			for (int i = 0; i < patients.size(); i++) {
				// System.out.println(patientUID + " == " + patients.get(i).getUID() + "//" +
				// patientUID.equals(patients.get(i).getUID()));
				if (patientUID.equals(patients.get(i).getUID())) {
					ptn = patients.get(i);
					for(int j=0; j < services.size();j++){
						requests = readRequests(requests, services.get(j).getServCode());
					}

					System.out.println(patients.get(i).getUID());
					System.out.println(patients.get(i).getlastName() + ", " + patients.get(i).getFirstName() + " "
							+ patients.get(i).getMiddleName());
					System.out.println(patients.get(i).getBirthday());
					System.out.println(patients.get(i).getAddress());
					System.out.println(patients.get(i).getNumber());
					System.out.println(patients.get(i).getNationalID());
					for(int j=0; j < requests.size(); j++){
						if(patients.get(i).getUID().equals(requests.get(j).getPUID())){
							System.out.print(requests.get(j).getRUID() + "\t\t");
							req = requests.get(j);
							for(int k=0;k<services.size();k++){
								if (requests.get(j).getRUID().substring(0, 3).equals(services.get(k).getServCode())) {
									svc = services.get(k);
									System.out.print(services.get(k).getDescription() + "\t\t");
								}
							}
							System.out.println(requests.get(j).getReqDate() + "\t\t" + requests.get(j).getResult());
						}
					}

					
				}
			}
		} else {
			String word = matches.get(0);
			String UID = word.substring(0, word.indexOf(';'));
			for (int i = 0; i < patients.size(); i++) {
				// System.out.println(patientUID + " == " + patients.get(i).getUID() + "//" +
				// patientUID.equals(patients.get(i).getUID()));
				if (UID.equals(patients.get(i).getUID())) {
					ptn = patients.get(i);
					System.out.println(patients.get(i).getUID());
					System.out.println(patients.get(i).getlastName() + ", " + patients.get(i).getFirstName() + " "
							+ patients.get(i).getMiddleName());
					System.out.println(patients.get(i).getBirthday());
					System.out.println(patients.get(i).getAddress());
					System.out.println(patients.get(i).getNumber());
					System.out.println(patients.get(i).getNationalID());
					System.out.println("\nRequest's UID" + "\t\t" + "Lab Test Type" + "\t\t" + "Request Date" + "\t\t" + "Result");
					
					for(int j=0; j < services.size();j++){
						requests = readRequests(requests, services.get(j).getServCode());
					}
					//System.out.println("andito tayow " + patients.get(i).getUID());	//<===

					for(int j=0; j < requests.size(); j++){
						//System.out.println(patients.get(i).getUID() + " == " + requests.get(j).getPUID());	//<===
						if(patients.get(i).getUID().equals(requests.get(j).getPUID())){
							req = requests.get(j);
							System.out.print(requests.get(j).getRUID() + "\t\t");
							for(int k=0;k<services.size();k++){
								if (requests.get(j).getRUID().substring(0, 3).equals(services.get(k).getServCode())) {
									svc = services.get(k);
									System.out.print(services.get(k).getDescription() + "\t\t");
								}
							}
							System.out.println(requests.get(j).getReqDate() + "\t\t" + requests.get(j).getResult());
						}
					}
				}
			}



		}

		System.out.println("Do you want to print a laboratory test result? [Y/N] ");
		choice = scan.nextLine();

		if(choice.equals("Y")||choice.equals("y")) {
			System.out.print("Enter request UID of lab result you wish to print: ");
			String choice2 = scan.nextLine();
			for(int j=0; j<requests.size();j++){
				if(choice2.equals(requests.get(j).getRUID()))
					req = requests.get(j);
			}

			try {
				printToPdf(choice2, ptn, svc, req);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		else{
			main_Menu.main(null);
		}



		System.out.println("Back to Main Menu...");
		main_Menu.main(null);
	}

	public void printToPdf(String PUID, Patient ptn, Service svc, LabRequest req) throws FileNotFoundException, DocumentException{
		String fileName = ""+ptn.getlastName() + "_" + req.getRUID() + "_" + req.getReqDate() + ".pdf";
		Document document = new Document();
		@SuppressWarnings("unused")
		PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fileName));
		document.open();

		Paragraph p1 = new Paragraph();
		p1.add("Company Logo");
		p1.setAlignment(Element.ALIGN_CENTER);

		Paragraph p2 = new Paragraph();
		p2.add("Address");
		p2.setAlignment(Element.ALIGN_CENTER);

		Paragraph p3 = new Paragraph();
		p3.add("Telephone Number");
		p3.setAlignment(Element.ALIGN_CENTER);


		float[] columnWidths = {10f, 10f};
		PdfPTable table = new PdfPTable(columnWidths);
		PdfPCell pcell = new PdfPCell();
		pcell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell.addElement(new Phrase("Name: " + ptn.getlastName() + ", " + ptn.getFirstName() + " " + ptn.getMiddleName()));
		pcell.setBorder(Rectangle.NO_BORDER);
		table.addCell(pcell);

		PdfPCell pcell1 = new PdfPCell();
		pcell1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell1.addElement(new Phrase("Specimen's UID: " + req.getRUID()));
		pcell1.setBorder(Rectangle.NO_BORDER);
		table.addCell(pcell1);

		PdfPCell pcell2 = new PdfPCell();
		pcell2.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell2.addElement(new Phrase("Patient's UID: " + ptn.getUID()));
		pcell2.setBorder(Rectangle.NO_BORDER);
		table.addCell(pcell2);

		PdfPCell pcell3 = new PdfPCell();
		pcell3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell3.addElement(new Phrase("Collection Date: " + req.getReqDate()));
		pcell3.setBorder(Rectangle.NO_BORDER);
		table.addCell(pcell3);

		PdfPCell pcell4 = new PdfPCell();
		pcell4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell4.addElement(new Phrase("Age: "));
		pcell4.setBorder(Rectangle.NO_BORDER);
		table.addCell(pcell4);

		PdfPCell pcell5 = new PdfPCell();
		pcell5.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell5.addElement(new Phrase("Birthday: "));
		pcell5.setBorder(Rectangle.NO_BORDER);
		table.addCell(pcell5);

		PdfPCell pcell6 = new PdfPCell();
		pcell6.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell6.addElement(new Phrase("Gender: "));
		pcell6.setBorder(Rectangle.NO_BORDER);
		table.addCell(pcell6);

		PdfPCell pcell7 = new PdfPCell();
		pcell7.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell7.addElement(new Phrase("Phone Number: "));
		pcell7.setBorder(Rectangle.NO_BORDER);
		table.addCell(pcell7);

		PdfPCell pcellf1 = new PdfPCell();
		pcellf1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcellf1.addElement(new Phrase(" "));
		pcellf1.setBorder(Rectangle.NO_BORDER);
		table.addCell(pcellf1);

		PdfPCell pcellf2 = new PdfPCell();
		pcellf2.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcellf2.addElement(new Phrase(" "));
		pcellf2.setBorder(Rectangle.NO_BORDER);
		table.addCell(pcellf2);

		PdfPCell pcellf3 = new PdfPCell();
		pcellf3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcellf3.addElement(new Phrase(" "));
		pcellf3.setBorder(Rectangle.NO_BORDER);
		table.addCell(pcellf3);

		PdfPCell pcellf4 = new PdfPCell();
		pcellf4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcellf4.addElement(new Phrase(" "));
		pcellf4.setBorder(Rectangle.NO_BORDER);
		table.addCell(pcellf4);

		PdfPTable table2 = new PdfPTable(columnWidths);
		PdfPCell pcell8 = new PdfPCell();
		pcell8.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
		pcell8.addElement(new Phrase("Test", bold));
		table.addCell(pcell8);

		PdfPCell pcell9 = new PdfPCell();
		pcell9.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell9.addElement(new Phrase("Result", bold));
		table.addCell(pcell9);

		PdfPCell pcell10 = new PdfPCell();
		pcell10.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell10.addElement(new Phrase(svc.getDescription()));
		table.addCell(pcell10);

		PdfPCell pcell11 = new PdfPCell();
		pcell11.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell11.addElement(new Phrase(req.getResult()));
		table.addCell(pcell11);

		PdfPTable table3 = new PdfPTable(columnWidths);
		PdfPCell pcell12 = new PdfPCell();
		pcell12.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell12.addElement(new Phrase("Ferelyn G. Ching"));
		pcell12.setBorder(Rectangle.NO_BORDER);
		table3.addCell(pcell12);

		PdfPCell pcell13 = new PdfPCell();
		pcell13.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell13.addElement(new Phrase("Sandeon Mikhail N. Gavan"));
		pcell13.setBorder(Rectangle.NO_BORDER);
		table3.addCell(pcell13);

		PdfPCell pcell14 = new PdfPCell();
		pcell14.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell14.addElement(new Phrase("Medical Technologist"));
		pcell14.setBorder(Rectangle.NO_BORDER);
		table3.addCell(pcell14);

		PdfPCell pcell15 = new PdfPCell();
		pcell15.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell15.addElement(new Phrase("Pathologist"));
		pcell15.setBorder(Rectangle.NO_BORDER);
		table3.addCell(pcell15);

		PdfPCell pcell16 = new PdfPCell();
		pcell16.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell16.addElement(new Phrase("Lic. # 9876549"));
		pcell16.setBorder(Rectangle.NO_BORDER);
		table3.addCell(pcell16);

		PdfPCell pcell17 = new PdfPCell();
		pcell17.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		pcell17.addElement(new Phrase("Lic. # 97434789"));
		pcell17.setBorder(Rectangle.NO_BORDER);
		table3.addCell(pcell17);



		document.add(p1);
		document.add(p2);
		document.add(p3);
		document.add( Chunk.NEWLINE );
		document.add(table);
		document.add( Chunk.NEWLINE );
		document.add( Chunk.NEWLINE );
		document.add(table2);
		document.add( Chunk.NEWLINE );
		document.add( Chunk.NEWLINE );
		document.add(table3);

		document.close();
	}

	public void SearchRecord(int transaction, String search, ArrayList<Service> services, ArrayList<String> list, ArrayList<Patient> patients) {
		// String filepath = "Patients.txt";
		// Scanner sc = new Scanner(new File(filepath));
		// String firstLastName;
		String bday = ""; // need to separate bday from lastname since fullString places middlename
							// between them
		ArrayList<String> matches = new ArrayList<String>();

		/*
		 * while(sc.hasNext()) {
		 * String record = sc.nextLine();
		 * if(record.contains(search)) {
		 * matches.add(record);
		 * }
		 * }
		 */

		/* if transaction is 2, separate lastname & firstname from bday */
		if (transaction == 2) {
			String[] res = search.split(";");
			search = "" + res[0] + ";" + res[1];
			bday = res[2];
		}

		for (int i = 0; i < patients.size(); i++) {
			String fullString = patients.get(i).getFullString();
			if (transaction == 2) {
				if (fullString.contains(search) && fullString.contains(bday)){
					String[] splitFS = fullString.split(";");
					if(splitFS.length <= 9)
						matches.add(fullString);
				}
			} else {
				if (fullString.contains(search)){
					String[] splitFS = fullString.split(";");
					if(splitFS.length <= 9)
						matches.add(fullString);
				
				}
			}
		}

		DisplayPatientRecord(search, matches, patients, services);
	}

	public ArrayList<LabRequest> readRequests(ArrayList<LabRequest> requests, String servCode) {
		String filepath = servCode + "_Requests.txt";
	
		try {
		  Scanner scan = new Scanner(new File(filepath));
		  while (scan.hasNext()) {
			String fullString = scan.nextLine();
			String[] splitString = fullString.split(";");
			if (splitString.length <= 4) {
			  requests.add(new LabRequest(fullString, splitString[0], splitString[1], splitString[2], splitString[3], ""));
			} else if (splitString.length == 5) {
			  requests.add(new LabRequest(fullString, splitString[0], splitString[1], splitString[2], splitString[3],
				  splitString[4]));
			} // TODO: consider deleted options in read
			else if (splitString.length == 6) {
			  requests.add(
				  new LabRequest(fullString, splitString[0], splitString[1], splitString[2], splitString[3], splitString[4],
					  splitString[5]));
			} else {
			  requests.add(
				  new LabRequest(fullString, splitString[0], splitString[1], splitString[2], splitString[3], splitString[4],
					  splitString[6]));
			}
			
		  }
	
		  return requests;
		} catch (FileNotFoundException e) {
		}
	
		return requests;
	  }
}
