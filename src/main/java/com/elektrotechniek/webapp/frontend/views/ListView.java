package com.elektrotechniek.webapp.frontend.views;

import com.elektrotechniek.webapp.backend.Student;
import com.elektrotechniek.webapp.backend.service.StudentService;
import com.elektrotechniek.webapp.frontend.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Students | Elektro CRM")
public class ListView extends VerticalLayout{
    Student student;                        //todo check why student unused
    StudentService studentService;
    StudentForm studentForm;
    Grid<Student> grid = new Grid<>(Student.class);
    TextField search = new TextField();
    Button add;
    ComboBox<String> orientatieSelect = new ComboBox<>();
    ComboBox<String> statusSelect = new ComboBox<>();

    public ListView(StudentService studentService) {    //studentservice autowired to constructor
        this.studentService = studentService;
        addClassName("list-view");                      //css
        initGrid();
        updateGrid();

        studentForm = new StudentForm();
        studentForm.setMaxWidth("75%");
        //verwijst naar addlistener functie in StudentForm
        studentForm.addListener(StudentForm.SaveEvent.class, this::saveStudent);
        studentForm.addListener(StudentForm.DeleteEvent.class, this::deleteStudent);
        studentForm.addListener(StudentForm.CloseEvent.class, e -> closeEditor());

        closeEditor();
        add(buttonLayout(), studentForm, grid);
    }

    private void initGrid() {
        grid.addClassName("student-grid");
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        //grid.setHeight("100%");
        grid.setColumns("studentennummer", "naam",
                "achternaam", "email", "orientatie");
        grid.addSelectionListener(e -> e.getFirstSelectedItem().ifPresent(this::showDetails));
    }

    private void updateGrid() {
        grid.setItems(studentService.findAll(search.getValue(),
                orientatieSelect.getValue()));
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
        orientatieSelect.setPlaceholder("orientatie");
        orientatieSelect.setClearButtonVisible(true);
        orientatieSelect.setItems("Energietechniek",
                "informatica", "telecommunicatie");
        orientatieSelect.addValueChangeListener(e -> updateGrid());
        search.setPlaceholder("Search");
        search.setClearButtonVisible(true);
        search.setValueChangeMode(ValueChangeMode.LAZY);
        search.addValueChangeListener(e -> updateGrid());
        add = new Button("add");
        add.addClickListener(e -> openEditor());
        return new HorizontalLayout(search, orientatieSelect, add);
    }

    private void showDetails(Student student){
        UI.getCurrent().navigate(StudentDetail.class, student.getStudentennummer());
    }
}
