package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.Localidade;
import br.com.crinnger.DioPontoEAcesso.service.LocalidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/localidade")
@RequiredArgsConstructor
public class LocalidadeController {

    private final LocalidadeService service;

    @PostMapping
    public ResponseEntity<Localidade> create(@RequestBody Localidade novo){
        return ResponseEntity.ok(service.save(novo));
    }

    @GetMapping
    public ResponseEntity<List<Localidade>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping
    public ResponseEntity<Localidade> update(@RequestBody Localidade novo){
        return new ResponseEntity<Localidade>(service.save(novo), HttpStatus.OK);
    }

    @GetMapping("/{localidadeId}")
    public ResponseEntity<Localidade> getById(@PathVariable("localidadeId") Long id) throws NoSuchElementException {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{localidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("localidadeId") Long id){
        service.delete(id);
    }
}
