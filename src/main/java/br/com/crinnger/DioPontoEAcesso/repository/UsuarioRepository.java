package br.com.crinnger.DioPontoEAcesso.repository;

import br.com.crinnger.DioPontoEAcesso.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
