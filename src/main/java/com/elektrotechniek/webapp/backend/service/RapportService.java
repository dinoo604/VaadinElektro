package com.elektrotechniek.webapp.backend.service;

import com.elektrotechniek.webapp.backend.Rapport;
import com.elektrotechniek.webapp.backend.repositories.RapportRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RapportService {
    RapportRepository rapportRepository;
    Rapport rapport;

    public RapportService(RapportRepository rapportRepository) {
        this.rapportRepository = rapportRepository;
    }

    public List<Rapport> selectByStudNummer(Integer studNum){
        return rapportRepository.selectByStudNummer(studNum);
    }
}
