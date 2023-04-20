package br.com.uniasselvi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.uniasselvi.exception.ParticipanteRegistrationException;
import br.com.uniasselvi.model.Participante;
import br.com.uniasselvi.repository.ParticipanteRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParticipanteService {

    private final ParticipanteRepository ParticipanteRepository;

    @Autowired
    public ParticipanteService(ParticipanteRepository ParticipanteRepository) {
        this.ParticipanteRepository = ParticipanteRepository;
    }


    public Participante createParticipante(Participante participante) {
        Optional<Participante> ParticipanteOptional = ParticipanteRepository.findByEmail(participante.getEmail());
        if(ParticipanteOptional.isPresent()) {
            throw new ParticipanteRegistrationException("Participante com email "+ participante.getEmail()+" já existe");
        }

        return ParticipanteRepository.save(participante);
    }

    public Participante updateParticipante(Participante participante) {
        return ParticipanteRepository.save(participante);
    }

    public List<Participante> findAllParticipantes() {
        return ParticipanteRepository.findAll();
    }

    public Optional<Participante> findParticipanteById(Long id) {
        return ParticipanteRepository.findById(id);
    }

    public void deleteParticipanteById(Long id) {
        ParticipanteRepository.deleteById(id);
    }
}
