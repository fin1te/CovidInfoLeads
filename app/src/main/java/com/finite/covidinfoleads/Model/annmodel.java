package com.finite.covidinfoleads.Model;

public class annmodel {
    String anntitle,annbody,annauthor;

    annmodel() {

    }

    public annmodel(String anntitle, String annbody, String annauthor) {
        this.anntitle = anntitle;
        this.annbody = annbody;
        this.annauthor = annauthor;
    }

    public String getAnntitle() {
        return anntitle;
    }

    public void setAnntitle(String anntitle) {
        this.anntitle = anntitle;
    }

    public String getAnnbody() {
        return annbody;
    }

    public void setAnnbody(String annbody) {
        this.annbody = annbody;
    }

    public String getAnnauthor() {
        return annauthor;
    }

    public void setAnnauthor(String annauthor) {
        this.annauthor = annauthor;
    }
}
