package service;
import manager.main_Menu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.BufferedWriter;

public class AddNewService {
	//Properties
	private String ServiceCode;
	private String Description;
	private String Price; //might be double or float
	private int transaction;
	
	//Method
	public void SaveRecord(Service service) {
		try {
			// Creates a new Text file called Patients.txt
			File myObj = new File("Services.txt");
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
			fw = new FileWriter("Services.txt", true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			pw.print(service.getFullString());
			
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
				ReturnMainMenu();
			} catch (IOException io) {
			}
		}
		
	}

	public boolean isServCodeUnique(ArrayList<Service> services, String servCode){
		for(int i=0;i<services.size();i++){
			//if the National ID already exists in the patient array, return false
			if(servCode.contains(services.get(i).getServCode())){
				return false;
			}
		}
		return true;
	}
	

	private void ReturnMainMenu() {
		main_Menu menu = new main_Menu();
		menu.main(null);
	}
	
}
