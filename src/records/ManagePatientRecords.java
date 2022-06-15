package records;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import manager.main_Menu;

public class ManagePatientRecords {

	// Properties
	private String FirstName;
	private String LastName;
	private String MiddleName;
	private String Birthday;
	private String Gender;
	private String Address;
	private String Phone;
	private String NationalID;
	private String transaction;
	private int NewPatientCount = 0;
	private AddNewPatient addPatient = new AddNewPatient();
	private SearchPatientRecord search = new SearchPatientRecord();
	private EditPatient edit = new EditPatient();
	private DeletePatientRecord delete = new DeletePatientRecord();

	private manager.main_Menu menu = new manager.main_Menu();
	private Scanner sc = new Scanner(System.in);
	private ArrayList<String> list = new ArrayList<String>();
	
	// Method
	public void EditPatient(ArrayList<Patient> patients) {
		String UI;
		String LN;
		String FN;
		String BD;
		String NID;

		System.out.println("Search Patient Record");
		System.out.println("");

		System.out.println("Select Preffered Input:");
		System.out.println("[1] Unique Identifier");
		System.out.println("[2] Last Name, First Name & Birthday");
		System.out.println("[3] National ID Number");
		System.out.println("[X] Return");
		String transaction = sc.nextLine();

		switch (transaction) {
			case "1":
				// If Unique Identifier
				System.out.println("Input Unique Identifier: ");
				UI = sc.nextLine();
				edit.SearchRecord(Integer.parseInt(transaction), UI, this.list, patients);

				break;
			case "2":
				// If Last Name, First Name & Birthday
				System.out.println("Last Name: ");
				LN = sc.nextLine();

				System.out.println("First Name: ");
				FN = sc.nextLine();

				System.out.println("Birthday(YYYYMMDD): ");
				BD = sc.nextLine();

				String combine = FN + ";" + LN + ";" + BD + ";";
				
				edit.SearchRecord(Integer.parseInt(transaction), combine, this.list, patients);
				break;
			case "3":
				// If National ID
				System.out.println("National ID no.: ");
				NID = sc.nextLine();
				edit.SearchRecord(Integer.parseInt(transaction), NID, this.list, patients);
				break;
			case "X":
				menu.main(null);
				break;
		}


	}

	public void AddNewPatient(ArrayList<Patient> patients) {

		String lastD = patients.get(patients.size()-1).getD(); //<===
		int lastE = patients.get(patients.size()-1).getE(); //<===

		System.out.println("Add New Patient");
		System.out.println("");
		System.out.println("First Name: ");
		FirstName = sc.nextLine();

		System.out.println("Last Name: ");
		LastName = sc.nextLine();

		System.out.println("Middle Name: ");
		MiddleName = sc.nextLine();

		System.out.println("Birthday(YYYYMMDD): ");
		Birthday = sc.nextLine();

		System.out.println("Gender: ");
		Gender = sc.nextLine();

		System.out.println("Address: ");
		Address = sc.nextLine();

		System.out.println("Phone No.: ");
		Phone = sc.nextLine();

		System.out.println("National ID no.: ");
		NationalID = sc.nextLine();
		

		System.out.println("Save Patient Record[Y/N]? ");
		transaction = sc.nextLine();

		NewPatientCount++;
		do {
			if (transaction.equals("Y") || transaction.equals("y")) {
				// Save Patient Record
				String newUID = addPatient.GetUniqueIdentifier(NewPatientCount, lastD, lastE);
				System.out.println("new UID is " + newUID);
				String fullString = "" + newUID + ";" + LastName + ";" + FirstName + ";" + MiddleName + ";" + Birthday + ";" + Gender + ";" + Address  + ";" + Phone + ";" + NationalID;
				patients.add(new Patient(fullString, newUID, LastName, FirstName, MiddleName, Long.parseLong(Birthday), Gender.charAt(0), Address, Phone, NationalID));
				/*list.add(addPatient.GetUniqueIdentifier(NewPatientCount));
				list.add(FirstName);
				list.add(LastName);
				list.add(MiddleName);
				list.add(Birthday);
				list.add(Gender);
				list.add(Address);
				list.add(Phone);
				list.add(NationalID);
				System.out.println(list); //<=== */
				addPatient.SaveRecord(patients.get(patients.size()-1));
				
				main_Menu.main(null);
			} else if (transaction.equals("N") || transaction.equals("n")) {
				System.out.println("Patient Record was not saved.");
				main_Menu.main(null);
			}
		} while (!(transaction.equals("Y") || transaction.equals("y") || transaction.equals("N")
				|| transaction.equals("n")));
	}

	public void SearchPatientRecord(ArrayList<Patient> patients) throws FileNotFoundException {
		String UI;
		String LN;
		String FN;
		String BD;
		String NID;

		System.out.println("Search Patient Record");
		System.out.println("");

		System.out.println("Select Preffered Input:");
		System.out.println("[1] Unique Identifier");
		System.out.println("[2] Last Name, First Name & Birthday");
		System.out.println("[3] National ID Number");
		System.out.println("[X] Return");
		String transaction = sc.nextLine();

		switch (transaction) {
			case "1":
				// If Unique Identifier
				System.out.println("Input Unique Identifier: ");
				UI = sc.nextLine();
				search.SearchRecord(Integer.parseInt(transaction), UI, this.list, patients);

				break;
			case "2":
				// If Last Name, First Name & Birthday
				System.out.println("Last Name: ");
				LN = sc.nextLine();

				System.out.println("First Name: ");
				FN = sc.nextLine();

				System.out.println("Birthday(YYYYMMDD): ");
				BD = sc.nextLine();

				String combine = FN + ";" + LN + ";" + BD + ";";
				
				search.SearchRecord(Integer.parseInt(transaction), combine, this.list, patients);
				break;
			case "3":
				// If National ID
				System.out.println("National ID no.: ");
				NID = sc.nextLine();
				search.SearchRecord(Integer.parseInt(transaction), NID, this.list, patients);
				break;
			case "X":
				menu.main(null);
				break;
		}

	}

	public void DeletePatientRecord(ArrayList<Patient> patients) {
		String input;

		/*System.out.println("Input Unique Identifier: ");
		input = sc.nextLine();
		delete.DeleteRecord();*/
		String UI;
		String LN;
		String FN;
		String BD;
		String NID;

		System.out.println("Search Patient Record");
		System.out.println("");

		System.out.println("Select Preffered Input:");
		System.out.println("[1] Unique Identifier");
		System.out.println("[2] Last Name, First Name & Birthday");
		System.out.println("[3] National ID Number");
		System.out.println("[X] Return");
		String transaction = sc.nextLine();

		switch (transaction) {
			case "1":
				// If Unique Identifier
				System.out.println("Input Unique Identifier: ");
				UI = sc.nextLine();
				delete.SearchPatientRecord(Integer.parseInt(transaction), UI, patients);

				break;
			case "2":
				// If Last Name, First Name & Birthday
				System.out.println("Last Name: ");
				LN = sc.nextLine();

				System.out.println("First Name: ");
				FN = sc.nextLine();

				System.out.println("Birthday(YYYYMMDD): ");
				BD = sc.nextLine();

				String combine = FN + ";" + LN + ";" + BD + ";";
				delete.SearchPatientRecord(Integer.parseInt(transaction), combine, patients);
				
				break;
			case "3":
				// If National ID
				System.out.println("National ID no.: ");
				NID = sc.nextLine();
				delete.SearchPatientRecord(Integer.parseInt(transaction), NID, patients);
				break;
			case "X":
				menu.main(null);
				break;
		}
	}

}
