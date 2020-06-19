package com.elektrotechniek.webapp.backend;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private java.util.Date datum;



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

    public Float getCijfer() {
        return cijfer;
    }

    public void setCijfer(Float cijfer) {
        this.cijfer = cijfer;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
