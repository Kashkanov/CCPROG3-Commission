package request;

import service.Service;
import manager.main_Menu;
import java.util.*;
import java.io.*;

public class SearchLabRequest {
	// Properties

	// Method
	private void DisplayLabRequests(String key, ArrayList<LabRequest> matches, ArrayList<Service> services) {
		// String filepath = "Patients.txt";
		// Scanner sc = new Scanner(new File(filepath));
		Scanner scan = new Scanner(System.in);
		System.out.println("\nRequest UID" + "\t\t" + "Lab Test Type" + "\t\t\t" + "Request Date" + "\t\t" + "Result");

		int servIndex = 0;
		if (!matches.isEmpty()) {
			for (int i = 0; i < matches.size(); i++) {
				LabRequest record = matches.get(i);
				String[] res = record.getFullString().split(";");
				for (int j = 0; j < services.size(); j++) {
					if (record.getRUID().substring(0, 3).equals(services.get(j).getServCode())) {
						servIndex = j;
					}
				}
				if (res.length <= 4) {
					System.out.println(
							res[0] + "\t\t" + services.get(servIndex).getDescription() + "\t\t" + res[2] + "\t\t\t"
									+ "N/A");
				} else {
					System.out.println(
							res[0] + "\t\t" + services.get(servIndex).getDescription() + "\t\t" + res[2] + "\t\t\t"
									+ res[4]);
				}

			}
		} else/* if (matches.isEmpty()) */ {
			System.out.println("No Record found.");
		}
		System.out.println("");
		System.out.println("Would you like to..");
		System.out.println("[1] Search Again");
		System.out.println("[2] Return to Main Menu");
		System.out.print("Select a transaction: ");
		int transaction = scan.nextInt();

		switch (transaction) {
			case 1:
				ManageLaboratoryRequest mng = new ManageLaboratoryRequest();
				System.out.println("Back to Search Lab Request...");
				mng.ProcessLabRequest(new ArrayList<LabRequest>(), services, 4);
				break;
			case 2:
				main_Menu.main(null);
				break;
		}
		System.out.println("");
		System.out.println("Back to Main Menu...");
		main_Menu.main(null);
	}

	public void search(int transaction, String key, ArrayList<Service> services) {
		ArrayList<LabRequest> matches = new ArrayList<LabRequest>();
		ArrayList<LabRequest> requests = new ArrayList<LabRequest>();
		ManageLaboratoryRequest mng = new ManageLaboratoryRequest();

		// Search patient UID
		if (transaction == 2) {
			for (int i = 0; i < services.size(); i++) {
				String filepath = services.get(i).getServCode() + "_Requests.txt";
				if (new File(filepath).exists()) {
					requests = mng.readRequests(requests, services.get(i).getServCode());
				}
			}

			for (int i = 0; i < requests.size(); i++) {
				if (key.equals(requests.get(i).getPUID())) {
					matches.add(requests.get(i));
				}
			}
		} else { // Search request UID
			String servCode = key.substring(0, 3);
			String filepath = servCode + "_Requests.txt";
			if (new File(filepath).exists()) {
				requests = mng.readRequests(requests, servCode);
			}
			for (int i = 0; i < requests.size(); i++) {
				if (key.equals(requests.get(i).getRUID())) {
					matches.add(requests.get(i));
				}
			}
		}

		DisplayLabRequests(key, matches, services);

	}

}
