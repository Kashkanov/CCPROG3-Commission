package service;

public class Service {
    public String fullString;
    public String servCode;
    public String description;
    public long price;
    public char deleted;
    public String delReason;

    public Service(String fullString, String servCode, String description, long price){
        this.fullString = fullString;
        this.servCode = servCode;
        this.description = description;
        this.price = price;
    }

    public Service(String fullString, String servCode, String description, long price, String reason){
        this.fullString = fullString;
        this.servCode = servCode;
        this.description = description;
        this.price = price;
        this.deleted = 'D';
        this.delReason = reason;
    }

    public String getFullString(){
        return this.fullString;
    }

    public String getServCode(){
        return this.servCode;
    }

    public String getDescription(){
        return this.description;
    }

    public long getPrice(){
        return this.price;
    }

    public void deleteService(String delReason){
        this.deleted = 'D';
        this.delReason = delReason;
        this.fullString = fullString  + this.deleted + ';' + this.delReason + ";"; 
    }


}
