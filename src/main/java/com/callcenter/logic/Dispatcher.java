package com.callcenter.logic;

import com.callcenter.entities.Call;
import com.callcenter.entities.Director;
import com.callcenter.entities.Operator;
import com.callcenter.entities.Supervisor;
import com.callcenter.interfaces.ICallRepository;
import com.callcenter.interfaces.IDirectorRepository;
import com.callcenter.interfaces.IOperatorRepository;
import com.callcenter.interfaces.ISupervisorRepository;
import java.util.List;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author andrexxx
 */
public class Dispatcher {

    @Autowired
    IOperatorRepository operatorRepository;

    @Autowired
    ISupervisorRepository supervisorRepository;

    @Autowired
    IDirectorRepository directorRepository;

    @Autowired
    ICallRepository callRepository;

    public void dispatchCall(Call call) throws Exception {
        try {
            if (call.getQueuePosition() < 10 || !call.isFinished()) {

                Future<List<Operator>> futureOperators = operatorRepository.findByAvailable(true);

                List<Operator> operators = futureOperators.get();

                if (!operators.isEmpty()) {
                    Operator operator = operators.get(0);
                    operator.setAvailable(false);
                    operator.setCall(call);
                    operatorRepository.save(operator);
                    return;
                }

                Future<List<Supervisor>> futureSupervisors = supervisorRepository.findByAvailable(true);

                List<Supervisor> supervisors = futureSupervisors.get();

                if (!supervisors.isEmpty()) {
                    Supervisor supervisor = supervisors.get(0);
                    supervisor.setAvailable(false);
                    supervisor.setCall(call);
                    supervisorRepository.save(supervisor);
                    return;
                }

                Future<List<Director>> futureDirectors = directorRepository.findByAvailable(true);

                List<Director> directors = futureDirectors.get();

                if (!directors.isEmpty()) {
                    Director director = directors.get(0);
                    director.setAvailable(false);
                    director.setCall(call);
                    directorRepository.save(director);
                    return;
                }

                throw new Exception("No hay agentes disponibles para atender la llamada");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void releaseOperatorCall(Operator operator) {
        try {
            Call call = operator.getCall();
            operator.setCall(null);
            operator.setAvailable(true);
            call.setFinished(true);
            //Aditional logic for operator type
            operatorRepository.save(operator);
            callRepository.save(call);
        } catch (Exception e) {
            throw e;
        }
    }

    public void releaseSupervisorCall(Supervisor supervisor) {
        try {
            Call call = supervisor.getCall();
            supervisor.setCall(null);
            supervisor.setAvailable(true);
            call.setFinished(true);
            //Aditional logic for supervisor type
            supervisorRepository.save(supervisor);
            callRepository.save(call);
        } catch (Exception e) {
            throw e;
        }
    }

    public void releaseDirectorCall(Director director) {
        try {
            Call call = director.getCall();
            director.setCall(null);
            director.setAvailable(true);
            call.setFinished(true);
            //Aditional logic for director type
            directorRepository.save(director);
            callRepository.save(call);
        } catch (Exception e) {
            throw e;
        }
    }
}
