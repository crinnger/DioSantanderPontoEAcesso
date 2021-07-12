package br.com.crinnger.DioPontoEAcesso.repository;

import br.com.crinnger.DioPontoEAcesso.model.JornadaTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JornadaRepository extends JpaRepository<JornadaTrabalho,Long> {
}
