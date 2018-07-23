package com.callcenter.interfaces;

import com.callcenter.entities.Operator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperatorRepository extends JpaRepository<Operator, UUID> {
    
    @Async
    Future<List<Operator>> findByAvailable(boolean available);
}
