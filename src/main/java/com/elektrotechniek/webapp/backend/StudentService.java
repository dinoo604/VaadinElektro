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

    public List<Student> findAll(String searchText, String orientatieFilter){

        if ((searchText == null || searchText.isEmpty())
                && orientatieFilter == null){
            return studentRepository.findAll();
        } else if(orientatieFilter == null && searchText != null){
            return studentRepository.search(searchText, "");
        }
        else{
            return studentRepository.search(searchText, orientatieFilter);
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

    public long countStudent(){
        return studentRepository.count();
    }
}
