package com.callcenter.controllers;

import com.callcenter.entities.Supervisor;
import com.callcenter.interfaces.ISupervisorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupervisorController {

    @Autowired
    ISupervisorRepository supervisorRepository;

    @PostMapping("/supervisor/save")
    public void createSupervisor() {
        supervisorRepository.save(createSupervisor(1));
        supervisorRepository.save(createSupervisor(2));
        supervisorRepository.save(createSupervisor(3));
    }

    @GetMapping("/supervisor/list")
    public List<Supervisor> listSupervisors() {
        return supervisorRepository.findAll();
    }

    private Supervisor createSupervisor(int index) {
        Supervisor sp = new Supervisor();
        sp.setGroup("Grupo Prueba");
        sp.setCommissionedArea("Area Prueba");
        sp.setAvailable(true);
        sp.setIdNumber("10000" + index);
        sp.setIdType(1);
        sp.setLastName("Supervisor" + index);
        sp.setName("Prueba");
        return sp;
    }
}
