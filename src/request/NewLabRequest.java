package request;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewLabRequest
{
    String ID;
    String serCode; 
    String ZZZ;
    int i;
    int n;

    Scanner sc = new Scanner(System.in);
    void Add(String[] UID) 
    { 

     do
    {
        display();
        for (i = 0; i < UID.length; i++)
        if (UID[i] != ID)
        {
            System.out.println("Patient not found!");
            System.out.println("[1] Search \n[2] Main Menu");
            n = sc.nextInt();
            if (n == 1)
            display();
        }
    }while (n != 2);

//        menu_Lab obj = new menu_Lab();
//        menu_Lab.displayLabMenu();
    }
    

    public String ReqGetUniqueIdentifier()
    {
        int reqCount = 101;			//For TEST
        String AA = null;
        String BB = "";
        String YYYYMMDD; 
  
        
        String UniqueIdentifier;
        
        DateTimeFormatter yeardtf = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter monthdtf = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter daydtf = DateTimeFormatter.ofPattern("dd");
        LocalDateTime now = LocalDateTime.now();
        
         YYYYMMDD = yeardtf.format(now) + monthdtf.format(now) + daydtf.format(now);
                
          int i = 0;
            outerloop:
            for (char c1 = 'A'; c1 <= 'Z'; c1++)
              for (char c2 = 'A'; c2 <= 'Z'; c2++)
                  for (int j = 0; j <= 99; j++) {
                    BB = String.format("%02d", j);
                    AA = "" + c1 + c2 ;
                    i++;
                    
                    if (i == reqCount)
                      break outerloop;
                  }
        
                UniqueIdentifier = ZZZ + YYYYMMDD + AA + BB;
        return UniqueIdentifier;
    }

	public void display()
	{
	    System.out.println("Enter Patient's UID : ");
	    ID= sc.nextLine();   //Patient's UID
	    System.out.println("Enter Service Code: ");
	    serCode = sc.nextLine(); //Patient's service code
	}
  
}
