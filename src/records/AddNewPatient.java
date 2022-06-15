package records;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class AddNewPatient {
	// Properties
	private static String month = "05"; // For TEST
	private int k = 0;
	private ArrayList<String> list = new ArrayList<>();

	// Methods
	public void SaveRecord(Patient patient) {
		//this.list.addAll(list2);
		try {
			// Creates a new Text file called Patients.txt
			File myObj = new File("Patients.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		try {
			fw = new FileWriter("Patients.txt", true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			pw.print(patient.getFullString() + ";");
			/*
			for (int i = 0; i < this.list.size(); i++) {
				removeDuplicate(this.list);
				pw.print(this.list.get(i) + ";");
				k++;
				if (k == 9) {
					pw.println();
					k = 0;
				}
			}
			
			pw.print(patient.getUID() + ";");
			pw.print(patient.getFirstName() + ";");
			pw.print(patient.getlastName() + ";");
			pw.print(patient.getMiddleName() + ";");
			pw.print(patient.getBirthday() + ";");
			pw.print(patient.getGender() + ";");
			pw.print(patient.getAddress() + ";");
			pw.print(patient.getNumber() + ";");
			pw.print(patient.getNationalID() + ";");*/
			pw.print("\n");
			pw.flush();

			System.out.println("Record Successfully Added.");
			System.out.println("");

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
	}

	private void removeDuplicate(ArrayList<String> list2) {
		ArrayList<String> temp = new ArrayList<>();
		for (int i = 0; i < this.list.size(); i++) {

			if (!temp.contains(this.list.get(i))) {
				temp.add(this.list.get(i));
			}
		}
		this.list.clear();
		this.list.addAll(temp);
	}

	public String GetUniqueIdentifier(int NewPatientCount, String lastD, int lastE) {
		// int newPatientCount = 101; //For TEST
		String A;
		String BBBB;
		String CC;
		String DDD = null;
		String EE = "";
		String UniqueIdentifier;

		DateTimeFormatter yeardtf = DateTimeFormatter.ofPattern("yyyy");
		DateTimeFormatter monthdtf = DateTimeFormatter.ofPattern("MM");
		LocalDateTime now = LocalDateTime.now();

		// Check if UniqueIdentifier is in the text file or used
		/*String filepath = "Patients.txt";
		String checkUI = "P202206AAA00";

		boolean found = false;
		String OldUI, LastName, FirstName, MiddleName,
				Birthday, Gender, Address, Phone, NI;

		/*try {
			Scanner x = new Scanner(new File(filepath));
			x.useDelimiter(";");
			System.out.println(x);

			while (x.hasNext() && !found) {
				OldUI = x.next();
				LastName = x.next();
				FirstName = x.next();
				MiddleName = x.next();
				Birthday = x.next();
				Gender = x.next();
				Address = x.next();
				Phone = x.next();
				NI = x.next();
				if (OldUI.equals(checkUI)) {
					found = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}*/

		A = "P";
		BBBB = yeardtf.format(now);
		CC = monthdtf.format(now);

		if (!CC.equals(month)) {
			NewPatientCount = 1;
			month = CC;
		}

		DDD = lastD;

		char d1 = lastD.charAt(0);
		char d2 = lastD.charAt(1);
		char d3 = lastD.charAt(2);

		//4lastE = 99; // <=== testing lang

		if(lastE == 99){
			d3++;
			if(d3 >= 'Z'){
				d2++;
				if(d2 >= 'Z'){
					d1++;
				}
			}
			EE = "01";
		}
		else {
			EE = String.format("%02d", lastE+1);
		}

		DDD = ""+ d1 + d2 + d3;
		
		// if Added New Patient
		/*int i = 0;
		outerloop: for (char c1 = 'A'; c1 <= 'Z'; c1++)
			for (char c2 = 'A'; c2 <= 'Z'; c2++)
				for (char c3 = 'A'; c3 <= 'Z'; c3++)
					for (int j = 0; j <= 99; j++) {
						EE = String.format("%02d", j);
						DDD = "" + c1 + c2 + c3;
						i++;

						if (i == NewPatientCount)
							break outerloop;
					}*/

		UniqueIdentifier = A + BBBB + CC + DDD + EE;
		System.out.println("unique= " + UniqueIdentifier);	//<===
		return UniqueIdentifier;
	}

}
