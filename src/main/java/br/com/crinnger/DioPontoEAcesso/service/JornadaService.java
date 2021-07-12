package br.com.crinnger.DioPontoEAcesso.service;

import br.com.crinnger.DioPontoEAcesso.model.JornadaTrabalho;
import br.com.crinnger.DioPontoEAcesso.repository.JornadaRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
public class JornadaService {

    @Autowired
    private JornadaRepository jornadaRepository;

    public JornadaTrabalho save(JornadaTrabalho jornadaTrabalho){
        return jornadaRepository.save(jornadaTrabalho);
    }

    public JornadaTrabalho getById(Long id) throws NoSuchElementException {
        return jornadaRepository.findById(id).orElseThrow(()-> new NoSuchElementException());
    }

    public List<JornadaTrabalho> getAll(){
        return jornadaRepository.findAll();
    }

    public void delete(Long id){
        getById(id);
        jornadaRepository.deleteById(id);
    }
}
