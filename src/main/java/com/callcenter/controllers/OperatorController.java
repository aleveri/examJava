package com.callcenter.controllers;

import com.callcenter.entities.Operator;
import com.callcenter.interfaces.IOperatorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperatorController {

    @Autowired
    IOperatorRepository operatorRepository;

    @PostMapping("/operator/save")
    public void createOperator() {
        operatorRepository.save(createOperator(1));
        operatorRepository.save(createOperator(2));
        operatorRepository.save(createOperator(3));
        operatorRepository.save(createOperator(4));
        operatorRepository.save(createOperator(5));
    }

    @GetMapping("/operator/list")
    public List<Operator> listOperators() {
        return operatorRepository.findAll();
    }

    private Operator createOperator(int index) {
        Operator op = new Operator();
        op.setArea("Area Prueba");
        op.setAvailable(true);
        op.setIdNumber("00000" + index);
        op.setIdType(1);
        op.setLastName("Operador" + index);
        op.setName("Prueba");
        return op;
    }
}
