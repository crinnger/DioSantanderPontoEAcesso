package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.TipoData;
import br.com.crinnger.DioPontoEAcesso.service.TipoDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tipoData")
@RequiredArgsConstructor
public class TipoDataController {
    private final TipoDataService service;

    @PostMapping
    public ResponseEntity<TipoData> create(@RequestBody TipoData novo){
        return ResponseEntity.ok(service.save(novo));
    }

    @GetMapping
    public ResponseEntity<List<TipoData>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping
    public ResponseEntity<TipoData> update(@RequestBody TipoData novo){
        return new ResponseEntity<TipoData>(service.save(novo), HttpStatus.OK);
    }

    @GetMapping("/{tipoDataId}")
    public ResponseEntity<TipoData> getById(@PathVariable("tipoDataId") Long id) throws NoSuchElementException {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{tipoDataId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("tipoDataId") Long id){
        service.delete(id);
    }
}
