package com.callcenter.controllers;

import com.callcenter.entities.Call;
import com.callcenter.interfaces.ICallRepository;
import com.callcenter.logic.Dispatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallController {

    @Autowired
    ICallRepository callRepository;

    List<Call> calls;

    @GetMapping("/call/dispatch")
    public String Dispatch() {
        try {
            prepareCalls();
            calls = callRepository.findAll();
            Dispatcher dispatcher = new Dispatcher();
            for (int i = 0; i < calls.size(); i++) {
                dispatcher.dispatchCall(calls.get(i));
            }
            return "OK";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/call/list")
    public List<Call> listCalls() {
        return callRepository.findAll();
    }

    private void prepareCalls() throws InterruptedException, ExecutionException {
        try {
            calls = new ArrayList<Call>();
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
            callRepository.saveAll(calls);
        } catch (InterruptedException | ExecutionException e) {
            throw e;
        }
    }
}
