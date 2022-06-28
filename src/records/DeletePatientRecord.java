package records;

import manager.main_Menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.Scanner;
import service.Service;


public class DeletePatientRecord {

	// Properties
	private String DeleteReason;
	private int transaction;

	// Methods
	private void DisplayPatientRecord(String search, ArrayList<String> matches, ArrayList<Patient> patients, ArrayList<Service> services) {
		int resIndex = -1;
		String patientUID = "";

		Scanner scan = new Scanner(System.in);
		System.out.println("Patient's UID" + "\t\t" + "Last Name" + "\t\t" + "First Name" + "\t\t" + "Middle Name"
				+ "\t\t" + "Birthday" + "\t\t" + "Gender" + "\t\t" + "Address" + "\t\t" + "Phone Number" + "\t\t"
				+ "National ID no.");

		if (!matches.isEmpty()) {
			for (int i = 0; i < matches.size(); i++) {
				String record = matches.get(i);
				String[] res = record.split(";");
				System.out.println(res[0] + "\t\t" + res[1] + "\t\t" + res[2] + "\t\t" + res[3] + "\t\t" + res[4]
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
					System.out.println("Back to Delete Patient Record");
					mng.ProcessPatientRecord(patients, services, 3);
					break;
				case 2:
					scan.close();
					ReturnMainMenu();
					break;
			}
		}
		System.out.println("");

		/* only asks for UID if results are more than 1 */
		if (matches.size() > 1) {
			System.out.println("");
			System.out.println("Enter the patient's UID that you want to delete: ");
			patientUID = scan.nextLine();

			System.out.println("");
			for (int i = 0; i < patients.size(); i++) {
				// System.out.println(patientUID + " == " + patients.get(i).getUID() + "//" +
				// patientUID.equals(patients.get(i).getUID()));
				if (patientUID.equals(patients.get(i).getUID())) {
					System.out.println(patients.get(i).getUID() + "\t\t" + patients.get(i).getlastName() + "\t\t"
							+ patients.get(i).getFirstName() +
							"\t\t" + patients.get(i).getMiddleName() + "\t\t" + patients.get(i).getBirthday() + "\t\t"
							+ patients.get(i).getAddress() + "\t\t" +
							patients.get(i).getNumber() + "\t\t" + (patients.get(i).getNationalID()));

				}
			}
		} else {
			String word = matches.get(0);
			patientUID = word.substring(0, word.indexOf(';'));

		}

		System.out.print("Are you sure you want to delete this patient's record?[Y/N]  ");
		String decision = scan.nextLine();

		if (decision.equals("y") || decision.equals("Y")) {
			DeletePatientRecord(patientUID, patients);
		} else {
			System.out.println("");
			System.out.println("Back to Main Menu...");
			ReturnMainMenu();
		}
		
	}

	private void DeletePatientRecord(String patientUID, ArrayList<Patient> patients) {
		// System.out.println("nasa delete na ko pre"); //<===

		Scanner scan = new Scanner(System.in);

		System.out.print("State reason for deletion: ");
		String reason = scan.nextLine();

		for (int i = 0; i < patients.size(); i++) {
			// System.out.println(patientUID + " == " + patients.get(i).getUID() + "//" +
			// patientUID.equals(patients.get(i).getUID()));
			if (patientUID.equals(patients.get(i).getUID())) {
				patients.get(i).deletePatient(reason);
			}
		}

		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		/* Overwrites Patients.txt to update with new info */
		try {
			fw = new FileWriter("Patients.txt", false);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			for (int i = 0; i < patients.size(); i++) {
				pw.print(patients.get(i).getFullString());
				pw.print("\n");
				pw.flush();
			}

			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				pw.close();
				bw.close();
				fw.close();
			} catch (IOException io) {
			}
		}
		System.out.println("\nData of patient " + patientUID + " has been deleted.");
		

	}

	private void ReturnMainMenu() {
		main_Menu.main(null);
	}

	public void SearchRecord(int transaction, String search, ArrayList<Service> services, ArrayList<Patient> patients) {

		String bday = ""; // need to separate bday from lastname since fullString places middlename
							// between them
		ArrayList<String> matches = new ArrayList<String>();

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

}
