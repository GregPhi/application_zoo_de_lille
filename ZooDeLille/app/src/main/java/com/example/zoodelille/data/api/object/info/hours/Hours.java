package com.example.zoodelille.data.api.object.info.hours;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Hours {
    private int id;
    private Summer summer;
    private Winter winter;
    private List<Date> exceptional_opening;
    private Date annual_closure_oldYear;
    private Date annual_closure_newYear;
    private String attention;

    public Hours() {
    }

    public Hours(Summer summer, Winter winter, List<Date> exceptional_opening, Date annual_closure_oldYear, Date annual_closure_newYear) {
        this.summer = summer;
        this.winter = winter;
        this.exceptional_opening = exceptional_opening;
        this.annual_closure_oldYear = annual_closure_oldYear;
        this.annual_closure_newYear = annual_closure_newYear;
        this.attention = "Fermeture des caisses 1h avant la fermeture du parc, dernier accès au zoo 30 minutes avant la fermeture des portes pour les visiteurs déjà munis d'un billet, abonnement ou Pass.";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Summer getSummer() {
        return summer;
    }

    public void setSummer(Summer summer) {
        this.summer = summer;
    }

    public Winter getWinter() {
        return winter;
    }

    public void setWinter(Winter winter) {
        this.winter = winter;
    }

    public List<Date> getExceptional_opening() {
        return exceptional_opening;
    }

    public void setExceptional_opening(List<Date> exceptional_opening) {
        this.exceptional_opening = exceptional_opening;
    }

    public Date getAnnual_closure_oldYear() {
        return annual_closure_oldYear;
    }

    public void setAnnual_closure_oldYear(Date annual_closure_oldYear) {
        this.annual_closure_oldYear = annual_closure_oldYear;
    }

    public Date getAnnual_closure_newYear() {
        return annual_closure_newYear;
    }

    public void setAnnual_closure_newYear(Date annual_closure_newYear) {
        this.annual_closure_newYear = annual_closure_newYear;
    }

    public String getAttention() {
        return attention;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hours hours = (Hours) o;
        return Objects.equals(summer, hours.summer) &&
                Objects.equals(winter, hours.winter) &&
                Objects.equals(exceptional_opening, hours.exceptional_opening) &&
                Objects.equals(annual_closure_oldYear, hours.annual_closure_oldYear) &&
                Objects.equals(annual_closure_newYear, hours.annual_closure_newYear);
    }
}
