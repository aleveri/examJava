package com.callcenter.interfaces;

import com.callcenter.entities.Call;
import java.util.UUID;
import java.util.concurrent.Future;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface ICallRepository extends JpaRepository<Call, UUID> {
    
    @Async
    @Query("SELECT coalesce(max(c.queuePosition), 0) FROM Call as c") 
    Future<Integer> findMaxQueuePosition();
}