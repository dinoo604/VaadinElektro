package com.elektrotechniek.webapp.backend;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

@Service
public class StudentService {
    private static final Logger LOGGER = Logger.getLogger(StudentService.class.getName());
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findAll(String searchText){
        if (searchText == null || searchText.isEmpty()){
            return studentRepository.findAll();
        } else{
            return studentRepository.search(searchText);
        }
    }

    public void save(Student student){
        if (student==null){
            LOGGER.log(Level.SEVERE, "student=empty");
        }
        studentRepository.save(student);
    }

    public void delete(Student student){
        studentRepository.delete(student);
    }
}
