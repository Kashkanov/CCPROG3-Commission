package records;

import java.util.ArrayList;

import java.util.*;

import manager.main_Menu;

import java.io.*;

//TODO: search again should return to search function

public class EditPatient {

	//Properties
	private String UpdatedAddress;
	private String UpdatedPhone;
	private int transaction;
	
	//Methods
	private void DisplayPatientRecord(String search, ArrayList<String> matches, ArrayList<Patient> patients) {
		int resIndex = -1;
		String patientUID = "";

		Scanner scan = new Scanner(System.in);
		System.out.println("Patient's UID" + "\t\t" + "Last Name" + "\t\t" + "First Name" + "\t\t" + "Middle Name" + "\t\t" + "Birthday" + "\t\t" + "Gender" + "\t\t" + "Address" + "\t\t" + "Phone Number" + "\t\t" + "National ID no.");
		

			if(!matches.isEmpty()){
				for(int i=0; i<matches.size();i++){
					String record = matches.get(i);
					String[] res = record.split(";");
					String temp = res[1];
					res[1] = res[2];
					res[2] = temp;
					System.out.println(res[0] + "\t\t" + res[1] + "\t\t" + res[2] + "\t\t" + res[3] + "\t\t" + res[4] + "\t\t" + res[5] + "\t\t" + res[6] + "\t\t" + res[7] + "\t\t" + res[8]);
				}
			}
			else/*  if (matches.isEmpty())*/{
				System.out.println("No Record found.");
				System.out.println("");
				System.out.println("Would you like to..");
				System.out.println("[1] Search Again");
				System.out.println("[2] Return to Main Menu");
				System.out.print("Select a transaction: ");
				int transaction = scan.nextInt();
				
				switch(transaction) {
				case 1:
					ManagePatientRecords mng = new ManagePatientRecords();
					System.out.println("Back to Edit Patient Record");
					mng.ProcessPatientRecord(patients, 4);
					break;
				case 2:
					ReturnMainMenu();
					break;
				}
			}
		System.out.println("");

		/*only asks for UID if results are more than 1 */
		if(matches.size()>1){
			System.out.println("");
			System.out.println("Enter the patient's UID that you want to edit: ");
			patientUID = scan.nextLine();

			System.out.println("");
			for(int i=0;i<patients.size();i++){
				//System.out.println(patientUID + " == " + patients.get(i).getUID() + "//" + patientUID.equals(patients.get(i).getUID()));
				if(patientUID.equals(patients.get(i).getUID())){
					System.out.println(patients.get(i).getUID() + "\t\t" + patients.get(i).getlastName() + "\t\t" + patients.get(i).getFirstName() + 
						"\t\t" + patients.get(i).getMiddleName() + "\t\t" + patients.get(i).getBirthday() + "\t\t" + patients.get(i).getAddress() + "\t\t" +
						patients.get(i).getNumber() + "\t\t" +  (patients.get(i).getNationalID()));

				}
			}
		}
		else{
			String word = matches.get(0);
			patientUID = word.substring(0, word.indexOf(';'));

		}
		
		EditPatientRecord(patientUID, patients);
	
	}
	
	private void EditPatientRecord(String UID, ArrayList<Patient> patients) {
		Scanner sc = new Scanner(System.in);
		int i=0, resIndex=-1;
		boolean found = false;

		/*search patients array for the index of UID*/
		while(found==false){
			if(UID.equals(patients.get(i).getUID())){
				resIndex = i;
				found = true;
			}
			i++;
		}

		System.out.println("\nWhich Information would you like to edit?");
		System.out.println("[1] Address");
		System.out.println("[2] Phone Number");
		System.out.println("[X] Exit to Main Menu");
		System.out.println("Select a transaction: ");
		String transaction = sc.nextLine();
		
		switch(transaction){
			case "1":	/*for changing address */
				System.out.print("\nEnter New Address: ");
				String address = sc.nextLine();
				System.out.println("\nfullstring = " + patients.get(resIndex).getFullString());
				patients.get(resIndex).setAdress(address);
				System.out.println("\nAddress of patient " + UID + " has been updated.");
				SaveRecord(patients);
				break;
			case "2":	/*for changing number */
				System.out.print("\nEnter New Phone Number: ");
				String number= sc.nextLine();
				patients.get(resIndex).setNumber(number);
				System.out.println("\nPhone Number of patient " + UID + " has been updated.");
				SaveRecord(patients);
				break;
			case "X":	/*return to main menu */
				ReturnMainMenu();
				break;
		}
		
	}

	private void SaveRecord(ArrayList<Patient> patients){
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		/*Overwrites Patients.txt to update with new info*/
		try {
			fw = new FileWriter("Patients.txt", false);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			for(int i=0;i<patients.size();i++){
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
		ReturnMainMenu();
	}
	

	
	private void ReturnMainMenu() {
		main_Menu menu = new main_Menu();
		menu.main(null);
	}

	public void SearchRecord(int transaction, String search, ArrayList<String> list, ArrayList<Patient> patients) {
		
		String bday="";	//need to separate bday from lastname since fullString places middlename between them
		ArrayList<String> matches = new ArrayList<String>();
		System.out.println("\nandito ako" + patients.size());	//<===

		/*if transaction is 2, separate lastname & firstname from bday */
		if(transaction==2){
			String[] res = search.split(";");	
			search = "" + res[0] + ";" + res[1];		
			bday = res[2];
		}

		for(int i=0; i<patients.size();i++){
			String fullString = patients.get(i).getFullString();
			//System.out.println( search + " == " + fullString + "//" + fullString.contains(search));	//<===
			if(transaction==2){
				if(fullString.contains(search)&&fullString.contains(bday)) 
					matches.add(fullString);
			}
			else{
				if(fullString.contains(search)) 
					matches.add(fullString);
			}
		}
		
		DisplayPatientRecord(search,matches,patients);
	}
	
}
