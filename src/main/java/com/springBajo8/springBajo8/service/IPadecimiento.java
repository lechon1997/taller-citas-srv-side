package com.springBajo8.springBajo8.service;

import com.springBajo8.springBajo8.domain.Padecimiento;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPadecimiento {

    Mono<Padecimiento> save(Padecimiento citasDTOReactiva);
    Flux<Padecimiento> listarPadecimientosPaciente(String idPaciente);
}
