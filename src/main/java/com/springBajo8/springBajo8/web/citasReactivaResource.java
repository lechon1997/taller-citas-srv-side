package com.springBajo8.springBajo8.web;


import com.springBajo8.springBajo8.domain.Padecimiento;
import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.service.IPadecimiento;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class citasReactivaResource {

    @Autowired
    private IcitasReactivaService icitasReactivaService;

    @Autowired
    private IPadecimiento ipadecimiento;

    @PostMapping("/citasReactivas")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<citasDTOReactiva> save(@RequestBody citasDTOReactiva citasDTOReactiva) {
        int x = 3;
        Padecimiento p = citasDTOReactiva.getPadecimiento();
        return ipadecimiento.save(p).then((icitasReactivaService.save(citasDTOReactiva)));
    }

    @DeleteMapping("/citasReactivas/{id}")
    private Mono<ResponseEntity<citasDTOReactiva>> delete(@PathVariable("id") String id) {
        return this.icitasReactivaService.delete(id)
                .flatMap(citasDTOReactiva -> Mono.just(ResponseEntity.ok(citasDTOReactiva)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @PutMapping("/citasReactivas/{id}")
    private Mono<ResponseEntity<citasDTOReactiva>> update(@PathVariable("id") String id, @RequestBody citasDTOReactiva citasDTOReactiva) {
        return this.icitasReactivaService.update(id, citasDTOReactiva)
                .flatMap(citasDTOReactiva1 -> Mono.just(ResponseEntity.ok(citasDTOReactiva1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @PutMapping("/citasReactivas/cancelar/{id}")
    private Mono<citasDTOReactiva> updateEstado(@PathVariable("id") String id) {
        citasDTOReactiva cita = this.icitasReactivaService.findById(id).block();
        cita.setEstadoReservaCita("Cita cancelada");
        return this.icitasReactivaService.save(cita);
    }

    @GetMapping("/citasReactivas/fecha-hora/{fecha}/{hora}")
    private Mono<citasDTOReactiva> consultarFechaHora(@PathVariable String fecha, @PathVariable String hora){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        LocalDate date = LocalDate.parse(fecha);
        return this.icitasReactivaService.findByFechaReservaCitaAndHoraReservaCita(date, hora);
    }

    @GetMapping("/citasReactivas/medico/{id}")
    private Mono<String> consultarFechaHora(@PathVariable String id){
        citasDTOReactiva cita = this.icitasReactivaService.findById(id).block();
        return Mono.just(cita.getNombreMedico() + " " + cita.getApellidosPaciente());
    }

    @GetMapping("/citasReactivas/{idPaciente}/byidPaciente")
    private Flux<citasDTOReactiva> findAllByidPaciente(@PathVariable("idPaciente") String idPaciente) {
        return this.icitasReactivaService.findByIdPaciente(idPaciente);
    }

    @GetMapping(value = "/citasReactivas")
    private Flux<citasDTOReactiva> findAll() {
        return this.icitasReactivaService.findAll();
    }

    @GetMapping("/citasReactivas/{id}/padecimiento")
    private Flux<Padecimiento> listarPadecimientosCliente(@PathVariable String id){
        return this.ipadecimiento.listarPadecimientosPaciente(id);
    }

}
