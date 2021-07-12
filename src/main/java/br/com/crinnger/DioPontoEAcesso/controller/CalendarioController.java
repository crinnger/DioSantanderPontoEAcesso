package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.Calendario;
import br.com.crinnger.DioPontoEAcesso.service.CalendarioService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/calendario")
@RequiredArgsConstructor
public class CalendarioController {

    private final CalendarioService service;

    @PostMapping
    public ResponseEntity<Calendario> create(@RequestBody Calendario novo){
        return ResponseEntity.ok(service.save(novo));
    }

    @GetMapping
    public ResponseEntity<List<Calendario>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping
    public ResponseEntity<Calendario> update(@RequestBody Calendario novo){
        return new ResponseEntity<Calendario>(service.save(novo), HttpStatus.OK);
    }

    @GetMapping("/{calendarioId}")
    public ResponseEntity<Calendario> getById(@PathVariable("calendarioId") Long id) throws NoSuchElementException {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{calendarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("calendarioId") Long id){
        service.delete(id);
    }
}
