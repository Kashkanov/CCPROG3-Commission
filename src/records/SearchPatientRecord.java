package records;

import java.util.*;

import manager.main_Menu;

import java.io.*;


public class SearchPatientRecord {
	main_Menu menu = new main_Menu();
	
	//Methods
	private void DisplayPatientRecord(String search, ArrayList<String> matches) throws FileNotFoundException {
		String filepath = "Patients.txt";
		Scanner sc = new Scanner(new File(filepath));
		Scanner scan = new Scanner(System.in);
		System.out.println("Patient's UID" + "\t" + "Last Name" + "\t" + "First Name" + "\t" + "Middle Name" + "\t" + "Birthday" + "\t" + "Gender" + "\t" + "Address" + "\t" + "Phone Number" + "\t" + "National ID no.");
		
		while(sc.hasNext()) {
			String record = sc.nextLine();
			if(record.contains(search)) {
				//	System.out.println(record);
				String[] res = record.split(";");
				System.out.println(res[0] + "\t" + res[1] + "\t" + res[2] + "\t" + res[3] + "\t" + res[4] + "\t" + res[5] + "\t" + res[6] + "\t" + res[7] + "\t" + res[8]);
			}
			else if (matches.isEmpty()){
				System.out.println("No Record found.");
				System.out.println("");
				System.out.println("Would you like to..");
				System.out.println("[1] Search Again");
				System.out.println("[2] Return to Main Menu");
				System.out.println("Select a transaction: ");
				int transaction = scan.nextInt();
				
				switch(transaction) {
				case 1:
					System.out.println("Back to Search Patient Record");
					break;
				case 2:
					menu.main(null);
					break;
				}
			}
		}
		
		System.out.println("");
		System.out.println("Enter the patient's UID that you want to display: ");
		String patientUID = scan.nextLine();
		
		System.out.println("Patient's UID" + "\t" + "Last Name" + "\t" + "First Name" + "\t" + "Middle Name" + "\t" + "Birthday" + "\t" + "Gender" + "\t" + "Address" + "\t" + "Phone Number" + "\t" + "National ID no.");
		
		for ( int i = 0; i < matches.size(); i++){
			  if (matches.get(i).contains(patientUID)){
				  //System.out.println(matches.get(i));
					String res = matches.get(i);
					System.out.println(res.split(";"));
			  }   
			}
		
		System.out.println("");
		System.out.println("Back to Main Menu...");
		menu.main(null);
	}
	
	public void SearchRecord(String search, ArrayList<String> list ) throws FileNotFoundException {
		String filepath = "Patients.txt";
		Scanner sc = new Scanner(new File(filepath));
		ArrayList<String> matches = new ArrayList<String>();
		
		while(sc.hasNext()) {
			String record = sc.nextLine();
			if(record.contains(search)) {
				matches.add(record);
			}
		}
		DisplayPatientRecord(search,matches);
	}
}
