package com.elektrotechniek.webapp.frontend.views;

import com.elektrotechniek.webapp.backend.Rapport;
import com.elektrotechniek.webapp.backend.Student;
import com.elektrotechniek.webapp.backend.Vak;
import com.elektrotechniek.webapp.backend.service.RapportService;
import com.elektrotechniek.webapp.backend.service.StudentService;
import com.elektrotechniek.webapp.backend.service.VakService;
import com.elektrotechniek.webapp.frontend.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
    Label primary;
    Label secondary;
    HorizontalLayout infoBar;

    public StudentDetail(StudentService studentService,
                         VakService vakService,
                         RapportService rapportService) {
        this.studentService = studentService;
        this.rapportService = rapportService;
        this.vakService = vakService;
        addClassName("student-detail");                     //CSS
    }

    @Override
    public void setParameter(BeforeEvent event, Integer parameter){
        //gets instance of student from listview
        student = studentService.getStudentById(parameter);
        add(generalInfo());
        initGrid();

    }

    //for creating header with name enz.
    private HorizontalLayout generalInfo(){
         infoBar = new HorizontalLayout(setLabels(
                "Naam:", student.getNaam() + " " + student.getAchternaam()),
                setLabels("Studentennummer", student.getStudentennummer().toString()),
                setLabels("Cohort", student.getCohort().toString()),
                setLabels("Orientatie", student.getOrientatie()));
         infoBar.setWidthFull();
         return  infoBar;
    }

    private VerticalLayout setLabels(String header, String content){
        primary = new Label(header);
        primary.addClassName("student-detail-primary-label");           //CSS
        secondary = new Label(content);
        secondary.addClassName("student-detail-secondary-label");       //CSS

        return new VerticalLayout(primary, secondary);
    }

    public void initGrid(){

        grid.addColumn(rapport -> {
            Vak vak = rapport.getVak();
            return  vak == null ? "-": vak.getVak_naam();
        }).setHeader("vak").setSortable(true);
        grid.addColumn(rapport -> rapport.getDatum()).setHeader("datum").setSortable(true);
        grid.addColumn(rapport -> rapport.getCijfer()).setHeader("cijfer").setSortable(true);
        grid.addClassName("rapport-grid");

        grid.setItems(rapportService.selectByStudNummer(student.getStudentennummer()));
        add(grid);
    }
}
