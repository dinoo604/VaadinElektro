package com.elektrotechniek.webapp.backend;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Entity
public class Student {

    @Id
    @NotNull
    private Integer studentennummer;

    @NotNull
    private String naam;

    @NotNull
    private String achternaam;

    @NotNull
    @Min(value = 2010, message = "Cohort mag niet minder dan 2010 zijn")
    @Max(value = 2099, message = "Cohort mag niet meer dan 2099 zijn")
    private Integer cohort;

    @Min(value = 2010, message = "Cohort mag niet minder dan 2010 zijn")
    @Max(value = 2099, message = "Cohort mag niet meer dan 2099 zijn")
    private Integer afstudeer_jaar;

    private String email;

    @NotNull
    private Character geslacht;          //TODO: limit to 2 Characters (m, v)

    private Character orientatie;        //TODO: limit to 3 Characters (e, t, i)

    @NotNull
    private Character studie_status;

    public Student() {
        super();
    }

    public Student(Integer studentennummer, String naam, String achternaam,
                   Integer cohort, Integer afstudeer_jaar, String email,
                   Character geslacht, Character orientatie, Character studie_status) {
        this.studentennummer = studentennummer;
        this.naam = naam;
        this.achternaam = achternaam;
        this.email = email;
        this.cohort = cohort;
        this.afstudeer_jaar = afstudeer_jaar;
        this.orientatie = orientatie;
        this.geslacht = geslacht;
        this.studie_status = studie_status;
    }

    public void setStudentennummer(Integer studentennummer) {
        this.studentennummer = studentennummer;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrientatie(Character orientatie) {
        this.orientatie = orientatie;
    }

    public void setGeslacht(Character geslacht) {
        this.geslacht = geslacht;
    }

    public void setCohort(Integer cohort) {
        this.cohort = cohort;
    }

    public void setAfstudeer_jaar(Integer afstudeer_jaar) {
        this.afstudeer_jaar = afstudeer_jaar;
    }

    public void setStudie_status(Character studie_status) {
        this.studie_status = studie_status;
    }

    public Integer getStudentennummer() {
        return studentennummer;
    }

    public String getNaam() {
        return naam;
    }

    public String getachternaam() {
        return achternaam;
    }

    public String getEmail() {
        return email;
    }

    public Character getOrientatie() {
        return orientatie;
    }

    public Character getGeslacht() {
        return geslacht;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public Character getStudie_status() {
        return studie_status;
    }

    public Integer getCohort() {
        return cohort;
    }

    public Integer getAfstudeer_jaar() {
        return afstudeer_jaar;
    }
}