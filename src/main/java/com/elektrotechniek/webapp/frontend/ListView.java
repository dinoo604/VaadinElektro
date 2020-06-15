package com.elektrotechniek.webapp.frontend;

import com.elektrotechniek.webapp.backend.Student;
import com.elektrotechniek.webapp.backend.StudentService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("")
public class ListView extends VerticalLayout{
    Student student;
    StudentService studentService;
    StudentForm studentForm;
    Grid<Student> grid = new Grid<>(Student.class);
    TextField search = new TextField();
    Button add;

    public ListView(StudentService studentService) {    //studentservice autowired to constructor
        this.studentService = studentService;
        addClassName("list-view");                      //css
        initGrid();
        updateGrid();

        studentForm = new StudentForm();
        studentForm.setMaxWidth("800px");
        //verwijst naar addlistener functie in StudentForm
        studentForm.addListener(StudentForm.SaveEvent.class, this::saveStudent);
        studentForm.addListener(StudentForm.DeleteEvent.class, this::deleteStudent);
        studentForm.addListener(StudentForm.CloseEvent.class, e -> closeEditor());

        closeEditor();
        add(buttonLayout(), studentForm, grid);
    }

    private void initGrid() {
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setColumns("studentennummer", "naam",
                "achternaam", "email", "orientatie");
        grid.asSingleSelect().addValueChangeListener(
                gridStudentComponentValueChangeEvent -> editStudent(
                        gridStudentComponentValueChangeEvent.getValue()));
    }

    private void updateGrid() {
        grid.setItems(studentService.findAll(search.getValue()));
    }

    public void editStudent(Student student){
        if(student == null){
            closeEditor();
        } else {
            studentForm.setForm(student);
            studentForm.setVisible(true);
            addClassName("editing");            //css
        }
    }

    public void closeEditor(){
        studentForm.setForm(null);
        studentForm.setVisible(false);
        removeClassName("editing");
    }

    public void openEditor(){
        Student student = new Student();
        studentForm.setForm(student);
        studentForm.setVisible(true);
    }

    private void saveStudent(StudentForm.SaveEvent event){
        System.out.println("haaai");
        studentService.save(event.getStudent());
        updateGrid();
        closeEditor();
    }

    public void deleteStudent(StudentForm.DeleteEvent event){
        studentService.delete(event.getStudent());
        updateGrid();
        closeEditor();
    }

    public HorizontalLayout buttonLayout(){
        search.setPlaceholder("Search");
        search.setClearButtonVisible(true);
        search.setValueChangeMode(ValueChangeMode.LAZY);
        search.addValueChangeListener(e -> updateGrid());
        add = new Button("add");
        add.addClickListener(e -> openEditor());
        return new HorizontalLayout(search, add);
    }
}
