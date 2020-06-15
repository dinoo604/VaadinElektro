package com.elektrotechniek.webapp.backend;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Vak {
    @Id
    Integer idvak;

    @NotEmpty(message = "field cannot be empty")
    String vak_naam;

    @NotEmpty(message = "field cannot be empty")
    @Min(value = 0, message = "studiepunten kunnen niet negatief")
    @Max(value = 99, message = "studiepunten is buiten zijn waarde")
    Float studiepunten;

    @Min(value = 1, message = "semester kan niet minder dan 1")
    @Max(value = 6, message = "semester kan niet meer dan 6")
    @NotEmpty(message = "field cannot be empty")
    Integer semester;

    @NotEmpty(message = "field cannot be empty")
    Character vak_orientatie;

    public Vak() {
    }

    public Integer getIdvak() {
        return idvak;
    }

    public String getVak_naam() {
        return vak_naam;
    }

    public void setVak_naam(String vak_naam) {
        this.vak_naam = vak_naam;
    }

    public Float getStudiepunten() {
        return studiepunten;
    }

    public void setStudiepunten(Float studiepunten) {
        this.studiepunten = studiepunten;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Character getVak_orientatie() {
        return vak_orientatie;
    }

    public void setVak_orientatie(Character vak_orientatie) {
        this.vak_orientatie = vak_orientatie;
    }

    public void setIdvak(Integer idvak) {
        this.idvak = idvak;
    }
}
