package records;

public class Patient {
    public String fullString;
    public String UID;
    public String regDate;
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
    public char deleted;
    public String delReason;

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
            this.regDate = UID.substring(1, 7);
            isolateE();
            isolateD();
    }

    public Patient(String fullString, String UID, String lastName, String firstName, String middleName, long birthday, char gender, String address, 
        String number, String nationalID, String reason){
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
            this.regDate = UID.substring(1, 7);
            this.deleted = 'D';
            this.delReason = reason;
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

    public String getRegDate(){
        return this.regDate;
    }

    public int getE(){
        return this.UIDE;
    }

    public String getD(){
        return this.UIDD;
    }

    public void isolateE(){
        this.UIDE = Integer.parseInt(this.UID.substring(10));
    }

    public void isolateD(){
        this.UIDD = this.UID.substring(7, 10);
    }

    public void setAdress(String newAddress){
        this.address = newAddress;
        this.fullString = "" + this.UID + ";" + this.lastName + ";" + this.firstName  + ";" + this.middleName+ ";" + 
            this.birthday + ";" + this.gender + ";" + newAddress + ";" + this.number + ";" + nationalID + ";";
    }

    public void setNumber(String newNumber){
        this.number = newNumber;
        this.fullString = "" + this.UID + ";" + this.lastName + ";" + this.firstName  + ";" + this.middleName+ ";" + 
            this.birthday + ";" + this.gender + ";" + this.address + ";" + newNumber + ";" + nationalID + ";";
    }

    /*this is called to modify patient info once they are deleted */
    public void deletePatient(String delReason){
        this.deleted = 'D';
        this.delReason = delReason;
        this.fullString = fullString  + this.deleted + ';' + this.delReason + ";"; 
    }

    
}
