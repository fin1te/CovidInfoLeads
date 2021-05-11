package com.finite.covidinfoleads;

public class docmodel {
    String docName,docNo,docrate,docTiming;

    docmodel() {

    }



    public docmodel(String docName, String docNo, String docrate, String docTiming) {
        this.docName = docName;
        this.docNo = docNo;
        this.docrate = docrate;
        this.docTiming = docTiming;
    }

    public String getDocTiming() {
        return docTiming;
    }

    public void setDocTiming(String docTiming) {
        this.docTiming = docTiming;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getDocrate() {
        return docrate;
    }

    public void setDocrate(String docrate) {
        this.docrate = docrate;
    }
}
