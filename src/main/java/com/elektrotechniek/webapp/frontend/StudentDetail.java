package com.elektrotechniek.webapp.frontend;

import com.elektrotechniek.webapp.backend.Student;
import com.elektrotechniek.webapp.backend.StudentService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Route(value = "student-information", layout = MainLayout.class)
@PageTitle("student detail | elektro")
public class StudentDetail extends VerticalLayout implements HasUrlParameter<Integer>{
    Student student;
    StudentService studentService;
    Integer id;

    public StudentDetail(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void setParameter(BeforeEvent event, Integer parameter){
        student = studentService.getStudentById(parameter);
        H1 nummer = new H1(student.getStudentennummer().toString());
        H1 naam = new H1(student.getNaam());
        H1 achternaam = new H1(student.getAchternaam());
        add(naam, achternaam, nummer);
    }


}
