package br.com.crinnger.DioPontoEAcesso.repository;

import br.com.crinnger.DioPontoEAcesso.model.Calendario;
import br.com.crinnger.DioPontoEAcesso.model.JornadaTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario,Long> {
}
