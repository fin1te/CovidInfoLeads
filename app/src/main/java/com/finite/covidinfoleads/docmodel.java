package com.finite.covidinfoleads;

public class docmodel {
    String docName,docNo,docrate;

    docmodel() {

    }



    public docmodel(String docName, String docNo, String docrate) {
        this.docName = docName;
        this.docNo = docNo;
        this.docrate = docrate;
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
