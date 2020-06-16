package com.elektrotechniek.webapp.frontend;

import com.elektrotechniek.webapp.backend.StudentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("dashboard")
public class DashboardView extends VerticalLayout {
    private StudentService studentService;

    public DashboardView(StudentService studentService) {
        this.studentService = studentService;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private Component getStudentStats(){
        Span stats = new Span(studentService.countStudent() + "students");
        stats.addClassName("student-stats");
        return stats;
    }
}
