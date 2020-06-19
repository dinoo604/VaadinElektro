package com.elektrotechniek.webapp.backend;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(Rapport.class)
@Entity
public class Rapport implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "student_studentennummer")
    Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "vak_idvak")
    Vak vak;

    @Id
    private String periode;

    @Id
    private Integer jaar;

    private Float cijfer;

    public Rapport() {
        super();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Vak getVak() {
        return vak;
    }

    public void setVak(Vak vak) {
        this.vak = vak;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public Integer getJaar() {
        return jaar;
    }

    public void setJaar(Integer jaar) {
        this.jaar = jaar;
    }

    public Float getCijfer() {
        return cijfer;
    }

    public void setCijfer(Float cijfer) {
        this.cijfer = cijfer;
    }
}
