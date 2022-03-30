package com.springBajo8.springBajo8.web;

import com.springBajo8.springBajo8.domain.Padecimiento;
import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.repository.IcitasReactivaRepository;
import jdk.vm.ci.meta.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class citasReactivaResourceTest {

    @Autowired
    private IcitasReactivaRepository service;

    @Test
    void buscarPorIdDelPacienteTest(){
        LocalDate date = LocalDate.parse("2022-03-30");
        citasDTOReactiva cita = new citasDTOReactiva("1",
                "Pepe",
                "ApellidoDePepe",
                "DoctorJuan",
                "DoctorRodriguez",
                new LocalDate().parse("2022-30-24"),
                "05:00 am",
                "Cita Aceptada",
                new Padecimiento("Dolor de cabeza","1"));

        Flux<citasDTOReactiva> paciente = service.findByIdPaciente("1");
        StepVerifier.create(paciente).expectNext(cita).verifyComplete();
    }

}