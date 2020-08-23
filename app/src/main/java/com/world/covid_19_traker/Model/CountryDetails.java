package com.world.covid_19_traker.Model;

public class CountryDetails {
    private String Flag, countryname, cases, todaycases, recover, active, test;

    public CountryDetails() {
    }

    public CountryDetails(String flag, String countryname, String cases, String todaycases, String recover, String active, String test) {
        Flag = flag;
        this.countryname = countryname;
        this.cases = cases;
        this.todaycases = todaycases;
        this.recover = recover;
        this.active = active;
        this.test = test;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public String getRecover() {
        return recover;
    }

    public void setRecover(String recover) {
        this.recover = recover;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
