package records;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.time.*;

import manager.main_Menu;
import service.Service;


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
	private AddNewPatient add = new AddNewPatient();
	private SearchPatientRecord search = new SearchPatientRecord();
	private EditPatient edit = new EditPatient();
	private DeletePatientRecord delete = new DeletePatientRecord();

	private manager.main_Menu menu = new manager.main_Menu();
	private Scanner sc = new Scanner(System.in);
	private ArrayList<String> list = new ArrayList<String>();


	public void AddNewPatient(ArrayList<Patient> patients) {
		File f1 = new File("patients.txt");
		String addAnother = "N";
		Month lastMonth =  LocalDate.parse("2000-01-01").getMonth();


		do{
			String lastD = "AAA";
			int lastE = 00;
			if(patients.size()>0) {
				String lastReqDate = patients.get(patients.size() - 1).getRegDate();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMM");
				lastMonth = YearMonth.parse(lastReqDate, dtf).getMonth();

				lastD = patients.get(patients.size() - 1).getD();
				lastE = patients.get(patients.size() - 1).getE();
			}

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

			while (add.isNIDUnique(patients, NationalID) == false && f1.exists()) {
				System.out.println("National ID Already Exists! Enter a new one");
				System.out.println("National ID no.: ");
				NationalID = sc.nextLine();
			}

			System.out.println("Save Patient Record[Y/N]? ");
			transaction = sc.nextLine();


			do {
				if (transaction.equals("Y") || transaction.equals("y")) {
					// Save Patient Record

					String newUID = add.GetUniqueIdentifier(lastMonth, lastD, lastE);
					System.out.println("new UID is " + newUID);
					String fullString = "" + newUID + ";" + LastName + ";" + FirstName + ";" + MiddleName + ";" + Birthday
								+ ";" + Gender + ";" + Address + ";" + Phone + ";" + NationalID;
					patients.add(new Patient(fullString, newUID, LastName, FirstName, MiddleName, Long.parseLong(Birthday),
								Gender.charAt(0), Address, Phone, NationalID));

					add.SaveRecord(patients.get(patients.size() - 1));


				} else if (transaction.equals("N") || transaction.equals("n")) {
					System.out.println("Patient Record was not saved.");

				}
			} while (!(transaction.equals("Y") || transaction.equals("y") || transaction.equals("N")
					|| transaction.equals("n")));
		
			System.out.println("Add another user?[Y/N] ");
			addAnother = sc.nextLine();
			
		}while(addAnother.equals("Y")||addAnother.equals("y"));
	
		main_Menu.main(null);
	}


	public void ProcessPatientRecord(ArrayList<Patient> patients, ArrayList<Service> services, int choice) {
		String delAnother="Y";


		String UI;
		String LN;
		String FN;
		String BD;
		String NID;

		System.out.println("Search Patient Record");
		System.out.println("");

		System.out.println("Select Prefered Input:");
		System.out.println("[1] Unique Identifier");
		System.out.println("[2] Last Name, First Name & Birthday");
		System.out.println("[3] National ID Number");
		System.out.println("[X] Return");
		String transaction = sc.nextLine();
		do{
			switch (transaction) {
				case "1":
					System.out.println("Input Unique Identifier: ");
					UI = sc.nextLine();
					switch (choice) {
						case 2: // edit
							edit.SearchRecord(Integer.parseInt(transaction), UI, services, this.list, patients);
							break;
						case 3: // delete
							delete.SearchRecord(Integer.parseInt(transaction), UI, services, patients);
							break;
						case 4: // search
							search.SearchRecord(Integer.parseInt(transaction), UI, services, this.list, patients);
							break;

					}
					break;
				case "2":
					// If Last Name, First Name & Birthday
					System.out.println("Last Name: ");
					LN = sc.nextLine();

					System.out.println("First Name: ");
					FN = sc.nextLine();

					System.out.println("Birthday(YYYYMMDD): ");
					BD = sc.nextLine();

					String combine = LN + ";" + FN + ";" + BD + ";";
					switch (choice) {
						case 2: // edit
							edit.SearchRecord(Integer.parseInt(transaction), combine, services, this.list, patients);
							break;
						case 3: // delete
							delete.SearchRecord(Integer.parseInt(transaction), combine, services, patients);
							break;
						case 4: // search
							search.SearchRecord(Integer.parseInt(transaction), combine, services, this.list, patients);
							break;

					}

					break;
				case "3":
					// If National ID
					System.out.println("National ID no.: ");
					NID = sc.nextLine();
					switch (choice) {
						case 2: // edit
							edit.SearchRecord(Integer.parseInt(transaction), NID, services, this.list, patients);
							break;
						case 3: // delete
							delete.SearchRecord(Integer.parseInt(transaction), NID, services,patients);
						case 4: // search
							search.SearchRecord(Integer.parseInt(transaction), NID, services, this.list, patients);
							break;

					}

					break;
				case "X":
					main_Menu.main(null);
					break;
			}

			System.out.print("Would you like to delete another user?[Y/N] ");
			delAnother = sc.nextLine();
	
		}while(delAnother.equals("Y")||delAnother.equals("y"));
		main_Menu.main(null);
	}
}