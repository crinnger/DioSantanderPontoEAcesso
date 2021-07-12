package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.NivelAcesso;
import br.com.crinnger.DioPontoEAcesso.service.NivelAcessoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/nivelAcesso")
@RequiredArgsConstructor
public class NivelAcessoController {
    private final NivelAcessoService service;

    @PostMapping
    public ResponseEntity<NivelAcesso> create(@RequestBody NivelAcesso novo){
        return ResponseEntity.ok(service.save(novo));
    }

    @GetMapping
    public ResponseEntity<List<NivelAcesso>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping
    public ResponseEntity<NivelAcesso> update(@RequestBody NivelAcesso novo){
        return new ResponseEntity<NivelAcesso>(service.save(novo), HttpStatus.OK);
    }

    @GetMapping("/{nivelId}")
    public ResponseEntity<NivelAcesso> getById(@PathVariable("nivelId") Long id) throws NoSuchElementException {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{nivelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("nivelId") Long id){
        service.delete(id);
    }
}
