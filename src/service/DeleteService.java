package service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import manager.main_Menu;
import service.ManageServices;

public class DeleteService {
	// Properties
	private String ServiceCode;
	private String ServiceDescriptionKey;
	private int transaction;

	// Method
	private void DisplayList(String key, ArrayList<Integer> matchesInd, ArrayList<Service> services) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Service Code" + "\t\t" + "Description" + "\t\t" + "Price");

		if (!matchesInd.isEmpty()) {
			for (int i = 0; i < matchesInd.size(); i++) {
				System.out.println(services.get(matchesInd.get(i)).getServCode() + "\t\t"
						+ services.get(matchesInd.get(i)).getDescription() + "\t\t"
						+ services.get(matchesInd.get(i)).getPrice());
				
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
					ManageServices mng = new ManageServices();
					System.out.println("Back to Search Patient Record");
					mng.ProcessService(services, 4);
					break;
				case 2:
					ReturnMainMenu();
					break;
			}
		}
		System.out.println("");

		/* only asks for UID if results are more than 1 */
		if (matchesInd.size() > 1) {
			System.out.println("");
			System.out.println("Enter the Service Code of the service that you want to delete: ");
			String servCode = scan.nextLine();

			System.out.println("");
			System.out.println("Service Code" + "\t\t" + "Description" + "\t\t" + "Price");
			for (int i = 0; i < services.size(); i++) {
				// System.out.println(patientUID + " == " + patients.get(i).getUID() + "//" +
				// patientUID.equals(patients.get(i).getUID()));
				// System.out.println(servCode + " == " + services.get(i).getServCode() + "//" +
				// servCode.equals(services.get(i).getServCode())); //<===
				if (servCode.equals(services.get(i).getServCode())) {
					DeleteService(services.get(i).getServCode(), services);
				}
			}
		}
		else{
			DeleteService(services.get(matchesInd.get(0)).getServCode(), services);
		}

		System.out.println("");
		System.out.println("Back to Main Menu...");
		ReturnMainMenu();
	}

	private void DeleteService(String servCode, ArrayList<Service> services) {
		Scanner sc = new Scanner(System.in);

		System.out.print("State reason for deletion: ");
		String delReason = sc.nextLine();

		System.out.print("Are you sure you would like to delete this service?[Y/N] ");
		String choice = sc.nextLine();

		if (choice.equals("Y") || (choice.equals("y"))) {
			for (int i = 0; i < services.size(); i++) {
				if (servCode.equals(services.get(i).getServCode())) {
					services.get(i).deleteService(delReason);
					saveChanges(services.get(i).getServCode(), services.get(i).getDescription(), services);
				}
			}

		} else
			ReturnMainMenu();
	}

	private void saveChanges(String servCode, String desc, ArrayList<Service> services) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		/* Overwrites Services.txt to update with new info */
		try {
			fw = new FileWriter("Services.txt", false);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			for (int i = 0; i < services.size(); i++) {
				pw.print(services.get(i).getFullString());
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
		System.out.println("\n" + servCode + " " + desc + " has been deleted.");
		ReturnMainMenu();
	}

	public void SearchService(int transaction, String key, ArrayList<Service> services) {
		ArrayList<Integer> matchesInd = new ArrayList<Integer>();

		/* if input is service code, compare to all service codes */
		if (transaction == 1) {
			for (int i = 0; i < services.size(); i++) {
				if (key.equals(services.get(i).getServCode())) {
					matchesInd.add(i);
				}
			}
		} else {
			for (int i = 0; i < services.size(); i++) {
				// System.out.println(key + "==" + services.get(i).getDescription() + "//" +
				// key.contains(services.get(i).getDescription()));//<===
				if (services.get(i).getDescription().contains(key)) {
					matchesInd.add(i);
				}
			}
		}
		DisplayList(key, matchesInd, services);

	}

	private void ReturnMainMenu() {
		main_Menu.main(null);
	}

}
