package com.callcenter.controllers;

import com.callcenter.entities.Director;
import com.callcenter.interfaces.IDirectorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectorController {

    @Autowired
    IDirectorRepository directorRepository;

    @PostMapping("/director/save")
    public void createDirector() {
        directorRepository.save(createDirector(1));
    }

    @GetMapping("/director/list")
    public List<Director> listDirectors() {
        return directorRepository.findAll();
    }

    private Director createDirector(int index) {
        Director dt = new Director();
        dt.setCommissionedGroup("Grupo Prueba");
        dt.setAvailable(true);
        dt.setIdNumber("00000" + index);
        dt.setIdType(1);
        dt.setLastName("Director" + index);
        dt.setName("Prueba");
        return dt;
    }
}
