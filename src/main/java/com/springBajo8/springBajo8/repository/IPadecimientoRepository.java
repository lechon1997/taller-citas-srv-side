package com.springBajo8.springBajo8.repository;

import com.springBajo8.springBajo8.domain.Padecimiento;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface IPadecimientoRepository extends ReactiveMongoRepository<Padecimiento,String> {
    Flux<Padecimiento> findByIdPaciente(String idPaciente);
}
