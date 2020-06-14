package com.elektrotechniek.webapp.frontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.combobox.ComboBox;

import java.util.ArrayList;
import java.util.List;

public class StudentForm extends FormLayout{
    private IntegerField studentennummer = new IntegerField("studentennummer");
    private TextField naam = new TextField("naam");
    private TextField achternaam = new TextField("achternaam");
    private TextField email = new TextField("email");
    private ComboBox<Integer> cohort = new ComboBox<>();
    private ComboBox<Integer> afstudeerjaar = new ComboBox<>();
    private ComboBox<Character> orientatie = new ComboBox<>();
    private ComboBox<Character> geslacht = new ComboBox<>();
    private ComboBox<Character> studieStatus = new ComboBox<>();
    private Button save = new Button("save");
    private Button delete = new Button("delete");
    private Button cancel = new Button("cancel");

    public StudentForm() {
        addClassName("student-form");
        initComponents();
        add(studentennummer, naam,
                achternaam, email,
                cohort, afstudeerjaar,
                orientatie, geslacht,
                studieStatus, getButtonLayout());

    }

    public void initComponents() {
        cohort.setLabel("cohort");
        afstudeerjaar.setLabel("afstudeerjaar");
        orientatie.setItems('e', 'i', 't');
        orientatie.setLabel("orientatie");
        geslacht.setItems('m', 'v');
        geslacht.setLabel("geslacht");
        studieStatus.setItems('a', 'd', 'v');
        studieStatus.setLabel("status");
        cohort.setItems(2015, 2016, 2017, 2018, 2019, 2020, 2021);
        afstudeerjaar.setItems(2015, 2016, 2017, 2018, 2019, 2020, 2021);
    }

    public HorizontalLayout getButtonLayout() {
        return new HorizontalLayout(save, delete, cancel);
    }
}
