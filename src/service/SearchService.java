package service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;
import manager.main_Menu;
import service.ManageServices;

public class SearchService {
	//Properties
	private String ServiceCode;
	private String ServiceDescriptionKey;
	private int transaction; 
	main_Menu menu = new main_Menu();
	
	//Method
	private void DisplayList(String key, ArrayList<Integer> matchesInd, ArrayList<Service> services) {
		//String filepath = "Patients.txt";
		//Scanner sc = new Scanner(new File(filepath));
		Scanner scan = new Scanner(System.in);
		System.out.println("Service Code" + "\t\t" + "Description" + "\t\t" + "Price");
		
		

		if(!matchesInd.isEmpty()){
			for(int i=0; i<matchesInd.size();i++){
				System.out.println(services.get(matchesInd.get(i)).getServCode() + "\t\t" + services.get(matchesInd.get(i)).getDescription() + "\t\t" + services.get(matchesInd.get(i)).getPrice());
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
				ManageServices mng = new ManageServices();
				System.out.println("Back to Search Patient Record");
				mng.ProcessService(services, 4);
				break;
			case 2:
				menu.main(null);
				break;
			}
		}
		System.out.println("");

		/*only asks for UID if results are more than 1 */
		if(matchesInd.size()>1){
			System.out.println("");
			System.out.println("Enter the Service Code of the service that you want to display: ");
			String servCode = scan.nextLine();
		
			System.out.println("");
			for(int i=0;i<services.size();i++){
				//System.out.println(patientUID + " == " + patients.get(i).getUID() + "//" + patientUID.equals(patients.get(i).getUID()));
				if(servCode.equals(services.get(i).getServCode())){
					System.out.println(services.get(matchesInd.get(i)).getServCode() + "\t\t" + 
						services.get(matchesInd.get(i)).getDescription() + "\t\t" + services.get(matchesInd.get(i)).getPrice());
				}
			}
		}
		else{
			System.out.println(services.get(0).getServCode() + "\t\t" + services.get(0).getDescription() + "\t\t" + services.get(0).getPrice());
		}
		
		
		
		System.out.println("");
		System.out.println("Back to Main Menu...");
		menu.main(null);
	}
	
	
	public void SearchService(int transaction, String key, ArrayList<Service> services) {
		ArrayList<Integer> matchesInd = new ArrayList<Integer>();

		/*if input is service code, compare to all service codes*/
		if(transaction==1){
			for(int i=0; i<services.size();i++){
				if(key.contains(services.get(i).getServCode())){
					matchesInd.add(i);
				}
			}
		}
		else{
			for(int i=0; i<services.size();i++){
				System.out.println(key + "==" + services.get(i).getDescription() + "//" + key.contains(services.get(i).getDescription()));//<===
				if(services.get(i).getDescription().contains(key)){
					matchesInd.add(i);
				}
		}
	}
		DisplayList(key,matchesInd,services);
	
		
	}
	
	private void ReturnMainMenu() {
		
	}
	
}
