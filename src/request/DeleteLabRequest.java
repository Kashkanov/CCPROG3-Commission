package request;

import service.Service;
import manager.main_Menu;
import java.util.*;
import java.io.*;

public class DeleteLabRequest {
	// Properties
	private void DisplayLabRequests(String key, ArrayList<LabRequest> matches, ArrayList<LabRequest> requests,
			ArrayList<Service> services) {
		String rUID;
		// String filepath = "Patients.txt";
		// Scanner sc = new Scanner(new File(filepath));
		Scanner scan = new Scanner(System.in);
		System.out.println("\nRequest UID" + "\t\t" + "Lab Test Type" + "\t\t\t" + "Request Date" + "\t\t" + "Result");

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
				if (res.length <= 4) { // no result
					System.out.println(
							res[0] + "\t\t" + services.get(servIndex).getDescription() + "\t\t" + res[2] + "\t\t\t"
									+ "N/A");
				} else { // with result
					System.out.println(
							res[0] + "\t\t" + services.get(servIndex).getDescription() + "\t\t" + res[2] + "\t\t\t"
									+ res[4]);
				}
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
					ManageLaboratoryRequest mng = new ManageLaboratoryRequest();
					System.out.println("Back to Delete Lab Request...");
					mng.ProcessLabRequest(new ArrayList<LabRequest>(), services, 3);
					break;
				case 2:
					main_Menu.main(null);
					break;
			}
		}
		System.out.println("");

		if (matches.size() > 1) {
			System.out.println("");
			System.out.println("Enter the request UID that you want to delete: ");
			rUID = scan.nextLine();

			System.out.println("");
			for (int i = 0; i < matches.size(); i++) {
				LabRequest record = matches.get(i);
				String[] res = record.getFullString().split(";");
				for (int j = 0; j < services.size(); j++) {
					if (record.getRUID().substring(0, 3).equals(services.get(j).getServCode())) {
						servIndex = j;
					}
				}
				System.out.println(
						res[0] + "\t\t" + services.get(servIndex).getDescription() + "\t\t" + res[2] + "\t\t\t" + "");
			}
		} else {
			LabRequest req = matches.get(0);
			rUID = req.getRUID();
		}

		System.out.print("Are you sure you want to delete this patient's record?[Y/N]  ");
		String decision = scan.nextLine();

		if (decision.equals("y") || decision.equals("Y")) {
			DeleteLabReq(rUID);
		} else {
			System.out.println("");
			System.out.println("Back to Main Menu...");
			main_Menu.main(null);
		}
	}

	private void DeleteLabReq(String rUID) {
		// System.out.println("nasa delete na ko pre"); //<===
		ManageLaboratoryRequest mng = new ManageLaboratoryRequest();
		Scanner scan = new Scanner(System.in);
		ArrayList<LabRequest> reqs = mng.readRequests(new ArrayList<LabRequest>(), rUID.substring(0, 3));

		System.out.print("State reason for deletion: ");
		String reason = scan.nextLine();

		for (int i = 0; i < reqs.size(); i++) {
			// System.out.println(patientUID + " == " + patients.get(i).getUID() + "//" +
			// patientUID.equals(patients.get(i).getUID()));
			if (rUID.equals(reqs.get(i).getRUID())) {
				reqs.get(i).deleteRequest(reason);
			}
		}

		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		/* Overwrites SERVICECODE_Requests.txt to update with new info */
		try {
			fw = new FileWriter(rUID.substring(0, 3) + "_Requests.txt", false);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			for (int i = 0; i < reqs.size(); i++) {
				pw.print(reqs.get(i).getFullString());
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
		System.out.println("\nData of request " + rUID + " has been deleted.");

		// ReturnMainMenu();
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
					mng.readRequests(requests, services.get(i).getServCode());
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

		DisplayLabRequests(key, matches, requests, services);

	}
}
