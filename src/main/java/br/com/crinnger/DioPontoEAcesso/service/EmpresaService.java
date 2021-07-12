package br.com.crinnger.DioPontoEAcesso.service;

import br.com.crinnger.DioPontoEAcesso.model.Empresa;
import br.com.crinnger.DioPontoEAcesso.repository.EmpresaRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
public class EmpresaService {
    @Autowired
    private EmpresaRepository repository;

    public Empresa save(Empresa newElement){
        return repository.save(newElement);
    }

    public Empresa getById(Long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(()-> new NoSuchElementException());
    }

    public List<Empresa> getAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
