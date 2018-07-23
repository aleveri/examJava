package com.callecenter.calls;

import com.callcenter.entities.Call;
import com.callcenter.entities.Operator;
import com.callcenter.entities.Supervisor;
import com.callcenter.interfaces.ICallRepository;
import com.callcenter.interfaces.IDirectorRepository;
import com.callcenter.interfaces.IOperatorRepository;
import javax.persistence.PersistenceContext;
import com.callcenter.interfaces.ISupervisorRepository;
import com.callcenter.logic.Dispatcher;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class})
@SpringBootTest
public class CallTest {

    @Autowired
    IOperatorRepository operatorRepository;

    @Autowired
    ISupervisorRepository supervisorRepository;

    @Autowired
    IDirectorRepository directorRepository;

    @Autowired
    ICallRepository callRepository;

    @Test
    public void createOperators() {
        operatorRepository.save(createOperator(1));
        operatorRepository.save(createOperator(2));
        operatorRepository.save(createOperator(3));
        operatorRepository.save(createOperator(4));
        operatorRepository.save(createOperator(5));
        assertEquals(true, true);
    }

    @Test
    public void createSupervisors() {
        supervisorRepository.save(createSupervisor(1));
        supervisorRepository.save(createSupervisor(2));
        supervisorRepository.save(createSupervisor(3));
        assertEquals(true, true);
    }

    @Test
    public void dispatchCall() {

        try {
            Dispatcher dispatcher = new Dispatcher();
            List<Call> calls = new ArrayList<Call>();
            int position = callRepository.findMaxQueuePosition().get();
            Random r = new Random();
            for (int i = 0; i < 15; i++) {
                Call call = new Call();
                call.setCode("Llamada" + (i + 1));
                call.setFinished(false);
                call.setQueuePosition(position + 1);
                call.setCallDuration(r.nextInt((10 - 5) + 1));
                calls.add(call);
                position++;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

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
