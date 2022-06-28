package records;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.*;

public class AddNewPatient {
	// Properties
	private static String month = "05"; // For TEST
	private ArrayList<String> list = new ArrayList<>();

	// Methods
	public void SaveRecord(Patient patient) {
		// this.list.addAll(list2);
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

	public boolean isNIDUnique(ArrayList<Patient> patients, String NID) {

		for (int i = 0; i < patients.size(); i++) {
			// if the National ID already exists in the patient array, return false
			if (NID.contains(patients.get(i).getNationalID())) {
				return false;
			}
		}
		return true;
	}

	public String GetUniqueIdentifier( Month lastMonth, String lastD, int lastE) {
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
		//LocalDateTime now = LocalDateTime.parse("2022-07-30T19:34:50.63");	//<== for testing
		Month curMonth = now.getMonth();


		A = "P";
		BBBB = yeardtf.format(now);
		CC = monthdtf.format(now);

		DDD = lastD;

		char d1 = lastD.charAt(0);
		char d2 = lastD.charAt(1);
		char d3 = lastD.charAt(2);

		// 4lastE = 99; // <=== testing lan

		if(curMonth.equals(lastMonth)){
			if (lastE == 99) {
				d3++;
				if (d3 >= 'Z') {
					d2++;
					if (d2 >= 'Z') {
						d1++;
					}
				}
				EE = "00";
			} else {
				EE = String.format("%02d", lastE + 1);
			}
		}
		else{
			d1='A';
			d2='A';
			d3='A';
			EE="00";
		}

		DDD = "" + d1 + d2 + d3;



		UniqueIdentifier = A + BBBB + CC + DDD + EE;
		System.out.println("unique= " + UniqueIdentifier); // <===
		return UniqueIdentifier;
	}

}
