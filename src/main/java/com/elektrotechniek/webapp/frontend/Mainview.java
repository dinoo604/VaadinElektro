package com.elektrotechniek.webapp.frontend;

import com.elektrotechniek.webapp.backend.Student;
import com.elektrotechniek.webapp.backend.StudentService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route
public class Mainview extends VerticalLayout{
    Student student;
    StudentService studentService;
    StudentForm studentForm;
    Grid<Student> grid = new Grid<>(Student.class);
    Binder<Student> binder = new Binder<>(Student.class);

    public Mainview(StudentService studentService) {
        this.studentService = studentService;
        addClassName("list-view");          //css
        initGrid();
        updateGrid();

        studentForm = new StudentForm();
        studentForm.setMaxWidth("800px");

        add(studentForm, grid);
    }

    private void initGrid() {
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setColumns("studentennummer", "naam",
                "achternaam", "email", "orientatie");
    }

    private void updateGrid() {
        grid.setItems(studentService.findAll());
    }

}
