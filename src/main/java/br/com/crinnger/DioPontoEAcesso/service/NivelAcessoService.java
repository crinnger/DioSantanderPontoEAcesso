package br.com.crinnger.DioPontoEAcesso.service;

import br.com.crinnger.DioPontoEAcesso.model.NivelAcesso;
import br.com.crinnger.DioPontoEAcesso.repository.NivelAcessoRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
public class NivelAcessoService {
    @Autowired
    private NivelAcessoRepository repository;

    public NivelAcesso save(NivelAcesso newElement){
        return repository.save(newElement);
    }

    public NivelAcesso getById(Long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(()-> new NoSuchElementException());
    }

    public List<NivelAcesso> getAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
