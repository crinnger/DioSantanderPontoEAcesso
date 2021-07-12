package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.BancoHoras;
import br.com.crinnger.DioPontoEAcesso.model.Movimentacao;
import br.com.crinnger.DioPontoEAcesso.service.BancoHorasService;
import br.com.crinnger.DioPontoEAcesso.service.MovimentacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/bancoHoras")
@RequiredArgsConstructor
public class BancoHorasController {

    private final BancoHorasService service;

    @PostMapping
    public ResponseEntity<BancoHoras> create(@RequestBody BancoHoras novo){
        return ResponseEntity.ok(service.save(novo));
    }

    @GetMapping
    public ResponseEntity<List<BancoHoras>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping
    public ResponseEntity<BancoHoras> update(@RequestBody BancoHoras novo){
        return new ResponseEntity<BancoHoras>(service.save(novo), HttpStatus.OK);
    }

    @GetMapping("/{usuarioId}/{movimentoId}/{bancoHorasId}")
    public ResponseEntity<BancoHoras> getById(@PathVariable("usuarioId") Long idUsuario, @PathVariable("movimentoId") Long movimentoId, @PathVariable("bancoHorasId") Long bancoHorasId) throws NoSuchElementException {
        return ResponseEntity.ok(service.getById(idUsuario,movimentoId,bancoHorasId));
    }

    @DeleteMapping("/{usuarioId}/{movimentoId}/{bancoHorasId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("usuarioId") Long idUsuario,@PathVariable("movimentoId") Long movimentoId, @PathVariable("bancoHorasId") Long bancoHorasId){
        service.delete(idUsuario,movimentoId,bancoHorasId);
    }
}
