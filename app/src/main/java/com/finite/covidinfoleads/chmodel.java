package com.finite.covidinfoleads;

public class chmodel {
    String chtitle,chbody;
    chmodel() {

    }

    public chmodel(String chtitle, String chbody) {
        this.chtitle = chtitle;
        this.chbody = chbody;
    }

    public String getChtitle() {
        return chtitle;
    }

    public void setChtitle(String chtitle) {
        this.chtitle = chtitle;
    }

    public String getChbody() {
        return chbody;
    }

    public void setChbody(String chbody) {
        this.chbody = chbody;
    }
}
