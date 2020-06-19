package com.elektrotechniek.webapp.backend.repositories;

import com.elektrotechniek.webapp.backend.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RapportRepository extends JpaRepository<Rapport, Long> {
    //@Query(value = "select * from rapport where student_studentennummer = :studNum", nativeQuery = true)

    @Query(value = "select * from rapport where (datum = any (" +
            "select max(datum) as datum from elektrotechniek.rapport group by vak_idvak))" +
            "and student_studentennummer = :studNum", nativeQuery = true)
    List<Rapport> selectByStudNummer(@Param("studNum") Integer studNum);
}


/*
 * select * from elektrotechniek.rapport where datum = any (
 *	select max(datum) as datum from elektrotechniek.rapport group by vak_idvak
 * )
 */