package br.com.crinnger.DioPontoEAcesso.service;

import br.com.crinnger.DioPontoEAcesso.model.CategoriaUsuario;
import br.com.crinnger.DioPontoEAcesso.repository.CategoriaUsuarioRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
public class CategoriaUsuarioService {
    @Autowired
    private CategoriaUsuarioRepository repository;

    public CategoriaUsuario save(CategoriaUsuario newElement){
        return repository.save(newElement);
    }

    public CategoriaUsuario getById(Long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(()-> new NoSuchElementException());
    }

    public List<CategoriaUsuario> getAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
