package records;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DeletePatientRecord {
	
	//Properties
	private String DeleteReason;
	private int transaction; 
	
	//Methods
	private void DisplayPatientRecord() {
		
	}
	
	private void SearchPatientRecord() {
		
	}
	
	public void DeleteRecord() {
		try {

		      File inFile = new File("Patients.txt");

		      if (!inFile.isFile()) {
		        System.out.println("Parameter is not an existing file");
		        return;
		      }
		      
		      Scanner sc = new Scanner(System.in);
		      //Construct the new file that will later be renamed to the original filename.
		      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		      BufferedReader br = new BufferedReader(new FileReader("Patients.txt"));
		      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		      String line = null;

		      System.out.println("Which record to be removed?");
		      System.out.println("Input transaction: ");
		      String lineToRemove = sc.nextLine();
		      
		      //Read from the original file and write to the new
		      //unless content matches data to be removed.
		      while ((line = br.readLine()) != null) {

		        if (!line.trim().contains(lineToRemove)) {
		          pw.println(line);
		          pw.flush();
		        }
		      }
		      pw.close();
		      br.close();

		      //Delete the original file
		      if (!inFile.delete()) {
		        System.out.println("Could not delete file");
		        return;
		      }

		      //Rename the new file to the filename the original file had.
		      if (!tempFile.renameTo(inFile))
		        System.out.println("Could not rename file");

		    }
		    catch (FileNotFoundException ex) {
		      ex.printStackTrace();
		    }
		    catch (IOException ex) {
		      ex.printStackTrace();
		    }
	}
	
	private void ReturnMainMenu() {
		
	}
}
