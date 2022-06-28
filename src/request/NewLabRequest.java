package request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.time.*;

public class NewLabRequest {


    public void SaveRecord(LabRequest lr, String servCode) {
        // this.list.addAll(list2);
        try {
            // Creates a new Text file called Patients.txt
            File myObj = new File(servCode + "_Requests.txt");
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
            fw = new FileWriter(servCode + "_Requests.txt", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.print(lr.getFullString() + ";");
            pw.print("\n");
            pw.flush();

            System.out.println("Lab request Successfully Added.");
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

    public String GetUniqueIdentifier(int NewPatientCount, String servCode, LocalDate lastDate, String lastD, int lastE) {
        // int newPatientCount = 101; //For TEST
        String AAA;
        String BBBB; // year
        String CCCC; // mmdd
        String DD = null;
        String EE = "";
        String UniqueIdentifier;

        DateTimeFormatter yeardtf = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter monthdtf = DateTimeFormatter.ofPattern("MMdd");
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = LocalDate.now();
        //LocalDate today = LocalDate.parse("2022-06-27");    //<=== for testing only

        // Check if UniqueIdentifier is in the text file or used
        /*
         * String filepath = "Patients.txt";
         * String checkUI = "P202206AAA00";
         * 
         * boolean found = false;
         * String OldUI, LastName, FirstName, MiddleName,
         * Birthday, Gender, Address, Phone, NI;
         * 
         * /*try {
         * Scanner x = new Scanner(new File(filepath));
         * x.useDelimiter(";");
         * System.out.println(x);
         * 
         * while (x.hasNext() && !found) {
         * OldUI = x.next();
         * LastName = x.next();
         * FirstName = x.next();
         * MiddleName = x.next();
         * Birthday = x.next();
         * Gender = x.next();
         * Address = x.next();
         * Phone = x.next();
         * NI = x.next();
         * if (OldUI.equals(checkUI)) {
         * found = true;
         * }
         * }
         * 
         * } catch (Exception e) {
         * e.printStackTrace();
         * }
         */

        AAA = servCode;
        BBBB = yeardtf.format(now);
        CCCC = monthdtf.format(now);

        DD = lastD;

        char d1 = lastD.charAt(0);
        char d2 = lastD.charAt(1);
        
        if(today.isEqual(lastDate)){
            if (lastE == 99) {
                d2++;
                if (d2 >= 'Z') {
                    d1++;
                }

                EE = "00";
            } else {
                EE = String.format("%02d", lastE + 1);
            }
        }
        else{
            d1 = 'A';
            d2 = 'A';
            EE = "00";
        }

        DD = "" + d1 + d2;

        UniqueIdentifier = AAA + BBBB + CCCC + DD + EE;
        System.out.println("unique= " + UniqueIdentifier); // <===
        return UniqueIdentifier;
    }
}
