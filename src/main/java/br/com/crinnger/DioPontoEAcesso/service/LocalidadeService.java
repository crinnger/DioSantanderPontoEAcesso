package br.com.crinnger.DioPontoEAcesso.service;

import br.com.crinnger.DioPontoEAcesso.model.Localidade;
import br.com.crinnger.DioPontoEAcesso.repository.LocalidadeRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
public class LocalidadeService {
    @Autowired
    private LocalidadeRepository repository;

    public Localidade save(Localidade newElement){
        return repository.save(newElement);
    }

    public Localidade getById(Long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(()-> new NoSuchElementException());
    }

    public List<Localidade> getAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
