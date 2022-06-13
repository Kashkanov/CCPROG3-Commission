package request;

import java.util.Scanner;

public class ManageLaboratoryRequest {

    public void manage_Patient_Lab() 
    {
      try (Scanner myobj = new Scanner(System.in)) 
      {
          int n;

          do
          {
            displayLabMenu();
             n = myobj.nextInt();
           
              if (n == 1)
              {
//                NewLab obj = new NewLab();

//                obj.Add(null);

              }
  
              else if (n == 2)
              {
               // SearchLab();
              }
  
              else if (n == 3)
              {
                //DeleteLab();
              }

              else if(n == 4)
              {
               // EditReq();
              }
      } while(n != 5);
      System.out.println("Invalid!");
  }
}



    public static  void displayLabMenu() 
    {
      System.out.println("Manage Laboratory Request \n[1] New Laboratory Request \n[2] Search Laboratory Request \n[3] Delete Laboratory Request \n[4] Edit Laboratory Request \nSelect Transaction : ");
    }
}
