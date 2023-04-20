package br.com.uniasselvi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.uniasselvi.model.Participante;

import java.util.Optional;


@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

    @Query("select p from Participante p where p.email=?1")
    Optional<Participante> findByEmail(String email);

}
