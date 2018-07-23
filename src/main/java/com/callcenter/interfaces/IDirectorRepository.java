package com.callcenter.interfaces;

import com.callcenter.entities.Director;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface IDirectorRepository extends JpaRepository<Director, UUID> {

    @Async
    Future<List<Director>> findByAvailable(boolean available);
}
