package br.com.crinnger.DioPontoEAcesso.service;

import br.com.crinnger.DioPontoEAcesso.model.Calendario;
import br.com.crinnger.DioPontoEAcesso.repository.CalendarioRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
public class CalendarioService {
    @Autowired
    private CalendarioRepository repository;

    public Calendario save(Calendario newElement){
        return repository.save(newElement);
    }

    public Calendario getById(Long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(()-> new NoSuchElementException());
    }

    public List<Calendario> getAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
