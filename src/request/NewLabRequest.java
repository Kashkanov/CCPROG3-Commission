package request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class NewLabRequest {
    /*
     * String ID;
     * String serCode;
     * String ZZZ;
     * int i;
     * int n;
     * 
     * Scanner sc = new Scanner(System.in);
     * void Add(String[] UID)
     * {
     * 
     * do
     * {
     * display();
     * for (i = 0; i < UID.length; i++)
     * if (UID[i] != ID)
     * {
     * System.out.println("Patient not found!");
     * System.out.println("[1] Search \n[2] Main Menu");
     * n = sc.nextInt();
     * if (n == 1)
     * display();
     * }
     * }while (n != 2);
     * 
     * // menu_Lab obj = new menu_Lab();
     * // menu_Lab.displayLabMenu();
     * }
     * 
     * 
     * public String ReqGetUniqueIdentifier()
     * {
     * int reqCount = 101; //For TEST
     * String AA = null;
     * String BB = "";
     * String YYYYMMDD;
     * 
     * 
     * String UniqueIdentifier;
     * 
     * DateTimeFormatter yeardtf = DateTimeFormatter.ofPattern("yyyy");
     * DateTimeFormatter monthdtf = DateTimeFormatter.ofPattern("MM");
     * DateTimeFormatter daydtf = DateTimeFormatter.ofPattern("dd");
     * LocalDateTime now = LocalDateTime.now();
     * 
     * YYYYMMDD = yeardtf.format(now) + monthdtf.format(now) + daydtf.format(now);
     * 
     * int i = 0;
     * outerloop:
     * for (char c1 = 'A'; c1 <= 'Z'; c1++)
     * for (char c2 = 'A'; c2 <= 'Z'; c2++)
     * for (int j = 0; j <= 99; j++) {
     * BB = String.format("%02d", j);
     * AA = "" + c1 + c2 ;
     * i++;
     * 
     * if (i == reqCount)
     * break outerloop;
     * }
     * 
     * UniqueIdentifier = ZZZ + YYYYMMDD + AA + BB;
     * return UniqueIdentifier;
     * }
     * 
     * public void display()
     * {
     * System.out.println("Enter Patient's UID : ");
     * ID= sc.nextLine(); //Patient's UID
     * System.out.println("Enter Service Code: ");
     * serCode = sc.nextLine(); //Patient's service code
     * }
     */

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

    public String GetUniqueIdentifier(int NewPatientCount, String servCode, String lastD, int lastE) {
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

        if (lastE == 99) {
            d2++;
            if (d2 >= 'Z') {
                d1++;
            }

            EE = "01";
        } else {
            EE = String.format("%02d", lastE + 1);
        }

        DD = "" + d1 + d2;

        UniqueIdentifier = AAA + BBBB + CCCC + DD + EE;
        System.out.println("unique= " + UniqueIdentifier); // <===
        return UniqueIdentifier;
    }
}
