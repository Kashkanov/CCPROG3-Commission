package records;

public class Patient {
    public String fullString;
    public String UID;
    public String lastName;
    public String firstName;
    public String middleName;
    public long birthday;
    public char gender;
    public String address;
    public String number;
    public String nationalID;
    public int UIDE;
    public String UIDD;

    public Patient(String fullString, String UID, String lastName, String firstName, String middleName, long birthday, char gender, String address, 
        String number, String nationalID){
            this.fullString = fullString;
            this.UID = UID;
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleName = middleName;
            this.birthday = birthday;
            this.gender = gender;
            this.address = address;
            this.number = number;
            this.nationalID = nationalID;
            isolateE();
            isolateD();
    }

    public String getFullString(){
        return this.fullString;
    }

    public String getUID(){
        return this.UID;
    }

    public String getlastName(){
        return this.lastName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getMiddleName(){
        return this.middleName;
    }

    public long getBirthday(){
        return this.birthday;
    }

    public char getGender(){
        return this.gender;
    }

    public String getAddress(){
        return this.address;
    }

    public String getNumber(){
        return this.number;
    }

    public String getNationalID(){
        return this.nationalID;
    }

    public int getE(){
        return this.UIDE;
    }

    public String getD(){
        return this.UIDD;
    }

    public void isolateE(){
        this.UIDE = Integer.parseInt(this.UID.substring(10));
        System.out.println("UIDE = " + this.UIDE); //<===
    }

    public void isolateD(){
        this.UIDD = this.UID.substring(7, 10);
    }

    
}
