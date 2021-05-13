package com.finite.covidinfoleads.Model;

public class hospmodel {

    String hospcity,hospname,hospno;

    hospmodel() {

    }

    public hospmodel(String hospcity, String hospname, String hospno) {
        this.hospcity = hospcity;
        this.hospname = hospname;
        this.hospno = hospno;
    }

    public String getHospcity() {
        return hospcity;
    }

    public void setHospcity(String hospcity) {
        this.hospcity = hospcity;
    }

    public String getHospname() {
        return hospname;
    }

    public void setHospname(String hospname) {
        this.hospname = hospname;
    }

    public String getHospno() {
        return hospno;
    }

    public void setHospno(String hospno) {
        this.hospno = hospno;
    }
}
