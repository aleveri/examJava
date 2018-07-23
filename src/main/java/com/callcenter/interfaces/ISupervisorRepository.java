package com.callcenter.interfaces;

import com.callcenter.entities.Supervisor;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupervisorRepository extends JpaRepository<Supervisor, UUID> {

    @Async
    Future<List<Supervisor>> findByAvailable(boolean available);
}
