package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.Ocorrencia;
import br.com.crinnger.DioPontoEAcesso.service.OcorrenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/ocorrencia")
@RequiredArgsConstructor
public class OcorrenciaController {

    private final OcorrenciaService service;

    @PostMapping
    public ResponseEntity<Ocorrencia> create(@RequestBody Ocorrencia novo){
        return ResponseEntity.ok(service.save(novo));
    }

    @GetMapping
    public ResponseEntity<List<Ocorrencia>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping
    public ResponseEntity<Ocorrencia> update(@RequestBody Ocorrencia novo){
        return new ResponseEntity<Ocorrencia>(service.save(novo), HttpStatus.OK);
    }

    @GetMapping("/{ocorrrenciaId}")
    public ResponseEntity<Ocorrencia> getById(@PathVariable("ocorrrenciaId") Long id) throws NoSuchElementException {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{ocorrrenciaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("ocorrrenciaId") Long id){
        service.delete(id);
    }
}
