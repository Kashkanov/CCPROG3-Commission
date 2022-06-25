package request;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import records.Patient;
import service.Service;
import manager.main_Menu;

public class ManageLaboratoryRequest {
  /*
   * public void manage_Patient_Lab()
   * {
   * try (Scanner myobj = new Scanner(System.in))
   * {
   * int n;
   * 
   * do
   * {
   * displayLabMenu();
   * n = myobj.nextInt();
   * 
   * if (n == 1)
   * {
   * // NewLab obj = new NewLab();
   * 
   * // obj.Add(null);
   * 
   * }
   * 
   * else if (n == 2)
   * {
   * // SearchLab();
   * }
   * 
   * else if (n == 3)
   * {
   * //DeleteLab();
   * }
   * 
   * else if(n == 4)
   * {
   * // EditReq();
   * }
   * } while(n != 5);
   * System.out.println("Invalid!");
   * }
   * }
   * 
   * 
   * 
   * public static void displayLabMenu()
   * {
   * System.out.
   * println("Manage Laboratory Request \n[1] New Laboratory Request \n[2] Search Laboratory Request \n[3] Delete Laboratory Request \n[4] Edit Laboratory Request \nSelect Transaction : "
   * );
   * }
   * 
   */

  private String servCode;
  private String patientUID;
  private String transaction;
  private Scanner sc = new Scanner(System.in);
  private NewLabRequest add = new NewLabRequest();
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

  public void AddNewLabRequest(ArrayList<Patient> patients, ArrayList<Service> services) {
    String addAnother = "N";

    do {
      String lastD = patients.get(patients.size() - 1).getD();
      int lastE = patients.get(patients.size() - 1).getE();

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

      System.out.println("Save Lab Request Record [Y/N]? ");
      transaction = sc.nextLine();

      // NewPatientCount++;
      do {
        if (transaction.equals("Y") || transaction.equals("y")) {
          // Save Patient Record
          String newUID = add.GetUniqueIdentifier(NewRequestCount, servCode, lastD, lastE);
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

}
