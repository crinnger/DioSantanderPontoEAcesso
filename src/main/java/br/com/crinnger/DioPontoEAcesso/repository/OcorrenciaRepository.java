package br.com.crinnger.DioPontoEAcesso.repository;

import br.com.crinnger.DioPontoEAcesso.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
}
