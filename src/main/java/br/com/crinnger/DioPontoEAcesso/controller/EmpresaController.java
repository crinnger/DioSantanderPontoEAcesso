package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.Empresa;
import br.com.crinnger.DioPontoEAcesso.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/empresa")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService service;

    @PostMapping
    public ResponseEntity<Empresa> create(@RequestBody Empresa novo){
        return ResponseEntity.ok(service.save(novo));
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping
    public ResponseEntity<Empresa> update(@RequestBody Empresa novo){
        return new ResponseEntity<Empresa>(service.save(novo), HttpStatus.OK);
    }

    @GetMapping("/{empresaId}")
    public ResponseEntity<Empresa> getById(@PathVariable("empresaId") Long id) throws NoSuchElementException {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{empresaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("empresaId") Long id){
        service.delete(id);
    }
}
