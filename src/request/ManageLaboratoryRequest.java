package request;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;

import records.Patient;
import service.Service;
import manager.main_Menu;

public class ManageLaboratoryRequest {

  private String servCode;
  private String patientUID;
  private String transaction;
  private Scanner sc = new Scanner(System.in);
  private NewLabRequest add = new NewLabRequest();
  private SearchLabRequest search = new SearchLabRequest();
  private DeleteLabRequest delete = new DeleteLabRequest();
  private EditLabRequest edit = new EditLabRequest();
  int NewRequestCount = 0;

  public Boolean isValidPatientUID(String uid, ArrayList<Patient> patients) {
    for (int i = 0; i < patients.size(); i++) {
      if (uid.equals(patients.get(i).getUID()))
        return true;
    }
    return false;
  }

  public Boolean isValidServCode(String servCode, ArrayList<Service> services) {
    for (int i = 0; i < services.size(); i++) {
      if (servCode.equals(services.get(i).getServCode()))
        return true;
    }
    return false;
  }

  public ArrayList<LabRequest> readRequests(ArrayList<LabRequest> requests, String servCode) {
    String filepath = servCode + "_Requests.txt";
    try {
      Scanner scan = new Scanner(new File(filepath));
      while (scan.hasNext()) {
        String fullString = scan.nextLine();
        String[] splitString = fullString.split(";");
        if (splitString.length <= 4) {
          requests.add(new LabRequest(fullString, splitString[0], splitString[1], splitString[2], splitString[3], ""));
        } else if (splitString.length == 5) {
          requests.add(new LabRequest(fullString, splitString[0], splitString[1], splitString[2], splitString[3],
              splitString[4]));
        } // TODO: consider deleted options in read
        else if (splitString.length == 6) {
          requests.add(
              new LabRequest(fullString, splitString[0], splitString[1], splitString[2], splitString[3], splitString[4],
                  splitString[5]));
        } else {
          requests.add(
              new LabRequest(fullString, splitString[0], splitString[1], splitString[2], splitString[3], splitString[4],
                  splitString[6]));
        }
      }

      return requests;
    } catch (FileNotFoundException e) {
    }

    return requests;
  }

  public void AddNewLabRequest(ArrayList<Patient> patients, ArrayList<Service> services) {
    String addAnother = "N", lastD = "";
    LocalDate lastDate = LocalDate.parse("2000-01-01");
    ArrayList<LabRequest> requests = new ArrayList<LabRequest>();
    int lastE = 0;

    do {
      System.out.println("Add New Lab Request\n");
      do {
        System.out.println("Enter patient's UID: ");
        patientUID = sc.nextLine();
        if (!isValidPatientUID(patientUID, patients)) {
          System.out.println("Patient UID is invalid. Try again.");
        }
      } while (!isValidPatientUID(patientUID, patients));

      do {
        System.out.println("Enter service code: ");
        servCode = sc.nextLine();
        if (!isValidServCode(servCode, services)) {
          System.out.println("Service code is invalid. Try again.");
        }
      } while (!isValidServCode(servCode, services));

      requests = readRequests(requests, servCode);
      if (requests.size() > 0) {
        String lastReqDate = requests.get(requests.size() - 1).getReqDate();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

        lastDate = LocalDate.parse(lastReqDate, dtf);
        lastD = requests.get(requests.size() - 1).getD();
        lastE = requests.get(requests.size() - 1).getE();
      } else {
        lastD = "AA";
        lastE = -1;
      }

      System.out.println("Save Lab Request Record [Y/N]? ");
      transaction = sc.nextLine();

      // NewPatientCount++;
      do {
        if (transaction.equals("Y") || transaction.equals("y")) {
          // Save Patient Record
          String newUID = add.GetUniqueIdentifier(NewRequestCount, servCode, lastDate, lastD, lastE);
          System.out.println("new UID is " + newUID);
          LocalDateTime ldt = LocalDateTime.now();
          LocalDate ld = ldt.toLocalDate();
          LocalTime lt = ldt.toLocalTime();
          DateTimeFormatter lddtf = DateTimeFormatter.ofPattern("yyyyMMdd");
          String reqDate = lddtf.format(ld);
          DateTimeFormatter ltdtf = DateTimeFormatter.ofPattern("hhmm");
          String reqTime = ltdtf.format(lt);
          String fullString = "" + newUID + ";" + patientUID + ";" + reqDate + ";" + reqTime;
          String result = "";

          add.SaveRecord(new LabRequest(fullString, newUID, patientUID, reqDate, reqTime, result), servCode);

          // main_Menu.main(null);
        } else if (transaction.equals("N") || transaction.equals("n")) {
          System.out.println("Lab Request was not saved.");
          // main_Menu.main(null);
        }
      } while (!(transaction.equals("Y") || transaction.equals("y") || transaction.equals("N")
          || transaction.equals("n")));

      System.out.println("Add another lab request?[Y/N] ");
      addAnother = sc.nextLine();

    } while (addAnother.equals("Y") || addAnother.equals("y"));

    main_Menu.main(null);
  }

  public void ProcessLabRequest(ArrayList<LabRequest> requests, ArrayList<Service> services, int choice) {
    String delAnother = "Y";
    String rUID, pUID;



    System.out.println("Search Lab Request");
    System.out.println("");

    System.out.println("Select Prefered Input:");
    System.out.println("[1] Request UID");
    System.out.println("[2] Patient UID");
    System.out.println("[X] Return");
    String transaction = sc.nextLine();
    do {
      switch (transaction) {
        case "1":
          System.out.println("Input Request UID: ");
          rUID = sc.nextLine();
          switch (choice) {
            case 2: // edit
              // edit.SearchRecord(Integer.parseInt(transaction), UI, this.list, patients);
              edit.search(Integer.parseInt(transaction), rUID, services);
              break;
            case 3: // delete
              // delete.SearchRecord(Integer.parseInt(transaction), UI, patients);
              delete.search(Integer.parseInt(transaction), rUID, services);
              break;
            case 4: // search
              search.search(Integer.parseInt(transaction), rUID, services);
              break;

          }
          break;
        case "2":
          System.out.println("Input Patient UID: ");
          pUID = sc.nextLine();
          switch (choice) {
            case 2: // edit
              // edit.SearchRecord(Integer.parseInt(transaction), combine, this.list,
              edit.search(Integer.parseInt(transaction), pUID, services);
              // patients);
              break;
            case 3: // delete
              // delete.SearchRecord(Integer.parseInt(transaction), combine, patients);
              delete.search(Integer.parseInt(transaction), pUID, services);
              break;
            case 4: // search
              // search.SearchRecord(Integer.parseInt(transaction), combine, this.list,
              // patients);
              search.search(Integer.parseInt(transaction), pUID, services);
              break;

          }
          break;
        case "X":
          main_Menu.main(null);
          break;
      }

      System.out.print("Would you like to delete another request? [Y/N] ");
      delAnother = sc.nextLine();

    } while (delAnother.equals("Y") || delAnother.equals("y"));
    main_Menu.main(null);
  }

}
