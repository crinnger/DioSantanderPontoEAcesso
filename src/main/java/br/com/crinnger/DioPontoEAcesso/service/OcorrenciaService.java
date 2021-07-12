package br.com.crinnger.DioPontoEAcesso.service;

import br.com.crinnger.DioPontoEAcesso.model.Ocorrencia;
import br.com.crinnger.DioPontoEAcesso.repository.OcorrenciaRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
public class OcorrenciaService {
    @Autowired
    private OcorrenciaRepository repository;

    public Ocorrencia save(Ocorrencia newElement){
        return repository.save(newElement);
    }

    public Ocorrencia getById(Long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(()-> new NoSuchElementException());
    }

    public List<Ocorrencia> getAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
