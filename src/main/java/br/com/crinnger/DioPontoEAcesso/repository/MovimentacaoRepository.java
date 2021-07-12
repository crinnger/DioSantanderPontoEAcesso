package br.com.crinnger.DioPontoEAcesso.repository;

import br.com.crinnger.DioPontoEAcesso.model.BancoHoras;
import br.com.crinnger.DioPontoEAcesso.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Movimentacao.MovimentacaoId> {
}
