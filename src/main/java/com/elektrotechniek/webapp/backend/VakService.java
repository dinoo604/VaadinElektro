package com.elektrotechniek.webapp.backend;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VakService {
    VakRepository vakRepository;

    public VakService(VakRepository vakRepository) {
        this.vakRepository = vakRepository;
    }

    public List<Vak> findAll(){
        return vakRepository.findAll();
    }

    public void saveVak(Vak vak){
        if(vak == null){
            //throw error log
        } else {
            vakRepository.save(vak);
        }
    }

    public void deleteVak(Vak vak){
        if (vak == null){
            //throw error log
        } else {
            vakRepository.delete(vak);
        }
    }
}
