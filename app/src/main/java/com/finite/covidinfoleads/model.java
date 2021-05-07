package com.finite.covidinfoleads;

public class model {
    String cityName,resourcetype,body,verifiedtime,verifieddate;

    model()
    {

    }
    public model(String cityName, String resourcetype, String body, String verifiedtime, String verifieddate) {
        this.cityName = cityName;
        this.resourcetype = resourcetype;
        this.body = body;
        this.verifiedtime = verifiedtime;
        this.verifieddate = verifieddate;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(String resourcetype) {
        this.resourcetype = resourcetype;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getVerifiedtime() {
        return verifiedtime;
    }

    public void setVerifiedtime(String verifiedtime) {
        this.verifiedtime = verifiedtime;
    }

    public String getVerifieddate() {
        return verifieddate;
    }

    public void setVerifieddate(String verifieddate) {
        this.verifieddate = verifieddate;
    }
}
