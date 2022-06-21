package service;

public class Service {
    public String fullString;
    public String servCode;
    public String description;
    public long price;
    public char D;
    public String reason;

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
        this.D = 'D';
        this.reason = reason;
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


}
