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
    public char deleted;
    public String delReason;
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

    public LabRequest(String fullString, String reqUID, String patientUID, String reqDate, String reqTime,
            char deleted, String delReason) {
        this.fullString = fullString;
        this.reqUID = reqUID;
        this.patientUID = patientUID;
        this.reqDate = reqDate;
        this.reqTime = reqTime;
        this.deleted = deleted;
        this.delReason = delReason;
        isolateD();
        isolateE();
    }

    public LabRequest(String fullString, String reqUID, String patientUID, String reqDate, String reqTime,
            String result, String delReason) {
        this.fullString = fullString;
        this.reqUID = reqUID;
        this.patientUID = patientUID;
        this.reqDate = reqDate;
        this.reqTime = reqTime;
        this.result = result;
        this.deleted = 'D';
        this.delReason = delReason;
        isolateD();
        isolateE();
    }

    public String getFullString() {
        return this.fullString;
    }

    public String getReqDate() {
        return this.reqDate;
    }

    public int getE() {
        return this.UIDE;
    }

    public String getRUID() {
        return reqUID;
    }

    public String getPUID() {
        return patientUID;
    }

    public String getD() {
        return this.UIDD;
    }

    public String getResult(){
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
        this.fullString = fullString + this.result + ';';
    }

    public void isolateE() {
        this.UIDE = Integer.parseInt(this.reqUID.substring(13));
    }

    public void isolateD() {
        this.UIDD = this.reqUID.substring(11, 13);
    }

    public void deleteRequest(String delReason) {
        this.deleted = 'D';
        this.delReason = delReason;
        this.fullString = fullString + this.deleted + ';' + this.delReason + ";";
    }
}
