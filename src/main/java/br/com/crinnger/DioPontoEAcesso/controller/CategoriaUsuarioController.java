package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.CategoriaUsuario;
import br.com.crinnger.DioPontoEAcesso.service.CategoriaUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/categoriaUsuario")
@RequiredArgsConstructor
public class CategoriaUsuarioController {

    private final CategoriaUsuarioService service;

    @PostMapping
    public ResponseEntity<CategoriaUsuario> create(@RequestBody CategoriaUsuario novo){
        return ResponseEntity.ok(service.save(novo));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaUsuario>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping
    public ResponseEntity<CategoriaUsuario> update(@RequestBody CategoriaUsuario novo){
        return new ResponseEntity<CategoriaUsuario>(service.save(novo), HttpStatus.OK);
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<CategoriaUsuario> getById(@PathVariable("categoriaId") Long id) throws NoSuchElementException {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{categoriaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("categoriaId") Long id){
        service.delete(id);
    }
}
