package br.com.uniasselvi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.uniasselvi.model.Participante;
import br.com.uniasselvi.service.ParticipanteService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/participants")

public class ParticipanteController {

    private final ParticipanteService ParticipanteService;

    public ParticipanteController(ParticipanteService ParticipanteService) {
        this.ParticipanteService = ParticipanteService;
    }

    @GetMapping
    public List<Participante> getAllParticipantes() {
        return ParticipanteService.findAllParticipantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> getParticipanteById(@PathVariable Long id) {
        return ParticipanteService.findParticipanteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Participante createParticipante(@RequestBody @Valid Participante Participante) {
        return ParticipanteService.createParticipante(Participante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participante> updateParticipante(@PathVariable Long id, @RequestBody @Valid Participante participante) {
        return ParticipanteService.findParticipanteById(id)
                .map(participanteObj -> {
                	System.out.println(participanteObj);
                    participante.setId(id);
                    return ResponseEntity.ok(ParticipanteService.updateParticipante(participante));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Participante> deleteParticipante(@PathVariable Long id) {
        return ParticipanteService.findParticipanteById(id)
                .map(Participante -> {
                    ParticipanteService.deleteParticipanteById(id);
                    return ResponseEntity.ok(Participante);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
