package com.springBajo8.springBajo8.service.impl;

import com.springBajo8.springBajo8.domain.Padecimiento;
import com.springBajo8.springBajo8.repository.IPadecimientoRepository;
import com.springBajo8.springBajo8.service.IPadecimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServicePadecimiento implements IPadecimiento {

    @Autowired
    private IPadecimientoRepository padecimientoRepository;


    @Override
    public Mono<Padecimiento> save(Padecimiento padecimiento) {
        return padecimientoRepository.save(padecimiento);
    }

    @Override
    public Flux<Padecimiento> listarPadecimientosPaciente(String idPaciente) {
        return padecimientoRepository.findByIdPaciente(idPaciente);
    }
}