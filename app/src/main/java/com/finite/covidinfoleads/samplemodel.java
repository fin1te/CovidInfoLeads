package com.finite.covidinfoleads;

public class samplemodel {

    String samplecity,samplename,sampleno;

    samplemodel() {

    }

    public samplemodel(String samplecity, String samplename, String sampleno) {
        this.samplecity = samplecity;
        this.samplename = samplename;
        this.sampleno = sampleno;
    }

    public String getSamplecity() {
        return samplecity;
    }

    public void setSamplecity(String samplecity) {
        this.samplecity = samplecity;
    }

    public String getSamplename() {
        return samplename;
    }

    public void setSamplename(String samplename) {
        this.samplename = samplename;
    }

    public String getSampleno() {
        return sampleno;
    }

    public void setSampleno(String sampleno) {
        this.sampleno = sampleno;
    }
}
