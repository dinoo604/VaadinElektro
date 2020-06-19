package com.elektrotechniek.webapp.frontend.views;

import com.elektrotechniek.webapp.backend.Rapport;
import com.elektrotechniek.webapp.backend.Student;
import com.elektrotechniek.webapp.backend.Vak;
import com.elektrotechniek.webapp.backend.service.RapportService;
import com.elektrotechniek.webapp.backend.service.StudentService;
import com.elektrotechniek.webapp.backend.service.VakService;
import com.elektrotechniek.webapp.frontend.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/*
 * constructor runs first
 * setParameter runs at last.
 * Param originates from Listview.class
 */
@Route(value = "student-information", layout = MainLayout.class)
@PageTitle("student detail | elektro")
public class StudentDetail extends VerticalLayout implements HasUrlParameter<Integer>{
    Student student;
    StudentService studentService;
    RapportService rapportService;
    VakService vakService;
    Grid<Rapport> grid = new Grid<>();

    public StudentDetail(StudentService studentService,
                         VakService vakService,
                         RapportService rapportService) {
        this.studentService = studentService;
        this.rapportService = rapportService;
        this.vakService = vakService;
    }

    @Override
    public void setParameter(BeforeEvent event, Integer parameter){
        //gets instance of student from listview
        student = studentService.getStudentById(parameter);
        initGrid();
    }

    public void initGrid(){

        //grid.setColumns("periode", "jaar", "cijfer");
        grid.addColumn(rapport -> {
            Vak vak = rapport.getVak();
            return  vak == null ? "-": vak.getVak_naam();
        }).setHeader("vak");
        grid.addColumn(rapport -> rapport.getDatum()).setHeader("datum");
        grid.addColumn(rapport -> rapport.getCijfer()).setHeader("cijfer");

        grid.setItems(rapportService.selectByStudNummer(student.getStudentennummer()));
        add(grid);
    }
}
