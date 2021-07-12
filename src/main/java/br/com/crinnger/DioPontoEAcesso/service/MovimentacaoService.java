package br.com.crinnger.DioPontoEAcesso.service;

import br.com.crinnger.DioPontoEAcesso.model.Movimentacao;
import br.com.crinnger.DioPontoEAcesso.model.Usuario;
import br.com.crinnger.DioPontoEAcesso.repository.MovimentacaoRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository repository;
    @Autowired
    private UsuarioService usuarioService;

    public Movimentacao save(Movimentacao newElement){
        return repository.save(newElement);
    }

    public Movimentacao getById(Long idUsuario,Long idMovimento) throws NoSuchElementException {
        Usuario usuario = usuarioService.getById(idUsuario);
        Movimentacao movimentacao= new Movimentacao(usuario,idMovimento);
        return repository.findById(movimentacao.getMovimentacaoId()).orElseThrow(()-> new NoSuchElementException());
    }

    public List<Movimentacao> getAll(){
        return repository.findAll();
    }

    public void delete(Long idUsuario,Long idMovimento){
        repository.delete(getById(idMovimento,idUsuario));
    }
}
