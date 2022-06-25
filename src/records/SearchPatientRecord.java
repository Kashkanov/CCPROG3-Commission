package records;

import java.util.*;

import manager.main_Menu;

import records.ManagePatientRecords;

import java.io.*;

//TODO: search again should return to search function

public class SearchPatientRecord {
	main_Menu menu = new main_Menu();

	// Methods
	private void DisplayPatientRecord(String search, ArrayList<String> matches, ArrayList<Patient> patients) {
		// String filepath = "Patients.txt";
		// Scanner sc = new Scanner(new File(filepath));
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
					mng.ProcessPatientRecord(patients, 4);
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

			// System.out.println("Patient's UID" + "\t" + "Last Name" + "\t" + "First Name"
			// + "\t" + "Middle Name" + "\t" + "Birthday" + "\t" + "Gender" + "\t" +
			// "Address" + "\t" + "Phone Number" + "\t" + "National ID no.");

			/*
			 * for ( int i = 0; i < matches.size(); i++){
			 * if (matches.get(i).contains(patientUID)){
			 * //System.out.println(matches.get(i));
			 * String res = matches.get(i);
			 * System.out.println(res.split(";"));
			 * }
			 * }
			 */
			System.out.println("");
			for (int i = 0; i < patients.size(); i++) {
				// System.out.println(patientUID + " == " + patients.get(i).getUID() + "//" +
				// patientUID.equals(patients.get(i).getUID()));
				if (patientUID.equals(patients.get(i).getUID())) {
					System.out.println(patients.get(i).getUID());
					System.out.println(patients.get(i).getlastName() + ", " + patients.get(i).getFirstName() + " "
							+ patients.get(i).getMiddleName());
					System.out.println(patients.get(i).getBirthday());
					System.out.println(patients.get(i).getAddress());
					System.out.println(patients.get(i).getNumber());
					System.out.println(patients.get(i).getNationalID());
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
				}
			}
		}

		System.out.println("");
		System.out.println("Back to Main Menu...");
		main_Menu.main(null);
	}

	public void SearchRecord(int transaction, String search, ArrayList<String> list, ArrayList<Patient> patients) {
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

		DisplayPatientRecord(search, matches, patients);
	}
}
