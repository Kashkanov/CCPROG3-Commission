package records;

import java.util.*;

import manager.main_Menu;
import request.LabRequest;
import service.Service;

import records.ManagePatientRecords;

import java.io.*;

//TODO: search again should return to search function

public class SearchPatientRecord {
	main_Menu menu = new main_Menu();

	// Methods
	private void DisplayPatientRecord(String search, ArrayList<String> matches, ArrayList<Patient> patients, ArrayList<Service> services) {
		// String filepath = "Patients.txt";
		// Scanner sc = new Scanner(new File(filepath));
		ArrayList<LabRequest> requests = new ArrayList<LabRequest>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Patient's UID" + "\t\t" + "Last Name" + "\t\t" + "First Name" + "\t\t" + "Middle Name"
				+ "\t\t" + "Birthday" + "\t\t" + "Gender" + "\t\t" + "Address" + "\t\t" + "Phone Number" + "\t\t"
				+ "National ID no.");

		/*
		 * while(sc.hasNext()) {
		 * String record = sc.nextLine();
		 * if(record.contains(search)) {
		 * // System.out.println(record);
		 * String[] res = record.split(";");
		 * System.out.println(res[0] + "\t" + res[1] + "\t" + res[2] + "\t" + res[3] +
		 * "\t" + res[4] + "\t" + res[5] + "\t" + res[6] + "\t" + res[7] + "\t" +
		 * res[8]);
		 * }
		 */

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
							System.out.print(requests.get(j).getPUID() + "\t\t");
							for(int k=0;k<services.size();k++){
								if (requests.get(j).getRUID().substring(0, 3).equals(services.get(k).getServCode())) {
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
							System.out.print(requests.get(j).getPUID() + "\t\t");
							for(int k=0;k<services.size();k++){
								if (requests.get(j).getRUID().substring(0, 3).equals(services.get(k).getServCode())) {
									System.out.print(services.get(k).getDescription() + "\t\t");
								}
							}
							System.out.println(requests.get(j).getReqDate() + "\t\t" + requests.get(j).getResult());
						}
					}
				}
			}
		}

		System.out.println("");
		System.out.println("Back to Main Menu...");
		main_Menu.main(null);
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
