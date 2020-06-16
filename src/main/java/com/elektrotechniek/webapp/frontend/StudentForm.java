package com.elektrotechniek.webapp.frontend;

import com.elektrotechniek.webapp.backend.Student;
import com.elektrotechniek.webapp.backend.StudentService;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.shared.Registration;

import java.util.ArrayList;
import java.util.List;

public class StudentForm extends FormLayout{
    IntegerField studentennummer = new IntegerField("studentennummer");
    TextField naam = new TextField("naam");
    TextField achternaam = new TextField("achternaam");
    EmailField email = new EmailField("email");
    IntegerField cohort = new IntegerField("cohort");
    IntegerField afstudeer_jaar = new IntegerField("jaar van afstudeer");
    ComboBox<String> orientatie = new ComboBox<>();
    ComboBox<String> geslacht = new ComboBox<>();
    ComboBox<String> studie_status = new ComboBox<>();
    Button save = new Button("save");
    Button delete = new Button("delete");
    Button cancel = new Button("close");
    Binder<Student> binder = new BeanValidationBinder<>(Student.class);
    StudentService studentService;
    Student student;

    /*
     * binder.withconverter = convert from string to integer
     * binder.withnullrepresentation = convert null table values
     *      acceptable for textfield
     */
    public StudentForm() {
        addClassName("student-form");
        setupComponents();

        binder.bindInstanceFields(this);

        //init a student obj to fill bean, otherwise exception gets thrown
        Student student = new Student();
        setForm(student);

        add(studentennummer, naam,
                achternaam, email,
                cohort, afstudeer_jaar,
                orientatie, geslacht,
                studie_status, getButtonLayout());
    }

    public void setupComponents() {
        orientatie.setItems("Energietechniek",
                "informatica", "telecommunicatie");
        orientatie.setLabel("orientatie");
        geslacht.setItems("man", "vrouw");
        geslacht.setLabel("geslacht");
        studie_status.setItems("Actief", "Voldaan", "Inactief");
        studie_status.setLabel("status");
    }

    public HorizontalLayout getButtonLayout() {
        save.addClickListener(event -> validateAndSave());
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickShortcut(Key.ENTER);
        delete.addClickListener(event ->
                fireEvent(new DeleteEvent(this, binder.getBean())));
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addClickListener(event -> new CloseEvent(this));
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.addClickShortcut(Key.ESCAPE);

        binder.addStatusChangeListener(evt ->
                save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, cancel);
    }

    public void validateAndSave(){
        if(binder.isValid()){
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    /*
     * bind changing sql data to form when clicked
     */
    public void setForm(Student student){
        this.student = student;
        binder.setBean(student);
    }

    public static abstract class StudentFormEvent extends ComponentEvent<StudentForm> {
        private Student student;

        protected StudentFormEvent(StudentForm source, Student student) {
            super(source, false);
            this.student = student;
        }

        public Student getStudent(){
            return student;
        }

    }

    public static class SaveEvent extends StudentFormEvent {
        public SaveEvent(StudentForm source, Student student) {
            super(source, student);
        }
    }

    public static class DeleteEvent extends StudentFormEvent {
        public DeleteEvent(StudentForm source, Student student) {
            super(source, student);
        }
    }

    public static class CloseEvent extends StudentFormEvent{
        public CloseEvent(StudentForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>>
            Registration addListener(Class<T> eventType, ComponentEventListener<T> listener){
        return getEventBus().addListener(eventType, listener);
    }
}
