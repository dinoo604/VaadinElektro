package com.elektrotechniek.webapp.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT * FROM elektrotechniek.student WHERE (naam LIKE CONCAT('%', :search, '%')" +
            "OR achternaam LIKE CONCAT('%', :search, '%')" +
            "OR cohort LIKE CONCAT(:search, '%'))" +
            "AND orientatie LIKE CONCAT('%', :orient, '%')", nativeQuery = true)
    List<Student> search(@Param("search") String searchText, @Param("orient") String orientatieFilter);

    Student findStudentByStudentennummer(Integer id);
}
