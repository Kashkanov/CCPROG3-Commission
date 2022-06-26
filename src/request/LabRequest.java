package request;

import java.util.*;
import java.time.*;

public class LabRequest {
    public String fullString;
    public String reqUID;
    public String patientUID;
    public String reqDate;
    public String reqTime;
    public String result;
    public String UIDD;
    public int UIDE;

    public LabRequest(String fullString, String reqUID, String patientUID, String reqDate, String reqTime,
            String result) {
        this.fullString = fullString;
        this.reqUID = reqUID;
        this.patientUID = patientUID;
        this.reqDate = reqDate;
        this.reqTime = reqTime;
        this.result = result;
        isolateD();
        isolateE();
    }

    public String getFullString() {
        return this.fullString;
    }

    public String getReqDate(){
        return this.reqDate;
    }

    public int getE() {
        return this.UIDE;
    }

    public String getD() {
        return this.UIDD;
    }

    public void isolateE() {
        this.UIDE = Integer.parseInt(this.reqUID.substring(13));
    }

    public void isolateD() {
        this.UIDD = this.reqUID.substring(11, 13);
    }
}
