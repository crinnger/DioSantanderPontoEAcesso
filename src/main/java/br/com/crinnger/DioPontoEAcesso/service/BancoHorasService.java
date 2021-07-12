package br.com.crinnger.DioPontoEAcesso.service;

import br.com.crinnger.DioPontoEAcesso.model.BancoHoras;
import br.com.crinnger.DioPontoEAcesso.model.Movimentacao;
import br.com.crinnger.DioPontoEAcesso.repository.BancoHorasRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
public class BancoHorasService {

    @Autowired
    private  BancoHorasRepository repository;

    @Autowired
    private MovimentacaoService movimentacaoService;

    public BancoHoras save(BancoHoras newElement){
        return repository.save(newElement);
    }

    public BancoHoras getById(Long idUsuario,Long idMovimentacao,Long idBancoHora) throws NoSuchElementException {
        Movimentacao movimentacao = movimentacaoService.getById(idMovimentacao,idUsuario);
        BancoHoras bancoHoras = new BancoHoras(movimentacao,idBancoHora);
        return repository.findById(bancoHoras.getBancoHorasId()).orElseThrow(()-> new NoSuchElementException());
    }

    public List<BancoHoras> getAll(){
        return repository.findAll();
    }

    public void delete(Long idUsuario,Long idMovimentacao,Long idBancoHora){
        repository.delete(getById(idUsuario,idMovimentacao,idBancoHora));
    }
}
