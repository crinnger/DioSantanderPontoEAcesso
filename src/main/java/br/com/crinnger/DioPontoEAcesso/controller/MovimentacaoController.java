package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.Movimentacao;
import br.com.crinnger.DioPontoEAcesso.service.MovimentacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/movimentacao")
@RequiredArgsConstructor
public class MovimentacaoController {

    private final MovimentacaoService service;

    @PostMapping
    public ResponseEntity<Movimentacao> create(@RequestBody Movimentacao novo){
        return ResponseEntity.ok(service.save(novo));
    }

    @GetMapping
    public ResponseEntity<List<Movimentacao>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping
    public ResponseEntity<Movimentacao> update(@RequestBody Movimentacao novo){
        return new ResponseEntity<Movimentacao>(service.save(novo), HttpStatus.OK);
    }

    @GetMapping("/{usuarioId}/{movimentoId}")
    public ResponseEntity<Movimentacao> getById(@PathVariable("usuarioId") Long idUsuario,@PathVariable("movimentoId") Long movimentoId) throws NoSuchElementException {
        return ResponseEntity.ok(service.getById(idUsuario,movimentoId));
    }

    @DeleteMapping("/{usuarioId}/{movimentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("usuarioId") Long idUsuario,@PathVariable("movimentoId") Long movimentoId){
        service.delete(idUsuario,movimentoId);
    }
}
