package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.JornadaTrabalho;
import br.com.crinnger.DioPontoEAcesso.service.JornadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/jornada")
@RequiredArgsConstructor
public class JornadaTrabalhoController {

    private final JornadaService jornadaService;

    @PostMapping
    public ResponseEntity<JornadaTrabalho> create(@RequestBody JornadaTrabalho jornadaTrabalho){
        return ResponseEntity.ok(jornadaService.save(jornadaTrabalho));
    }

    @GetMapping(value = "/{jornadaId}")
    public ResponseEntity<JornadaTrabalho> getJornada(@PathVariable("jornadaId") Long id) throws NoSuchElementException {
        return ResponseEntity.ok(jornadaService.getById(id));
    }

//    @GetMapping
//    public ResponseEntity<JornadaTrabalho> getJornadaParam(@RequestParam("idJornada") Long id) throws NoSuchElementException {
//       return ResponseEntity.ok(jornadaService.getById(id));
//    }

    @GetMapping
    public ResponseEntity<List<JornadaTrabalho>> getJornada(){
        return ResponseEntity.ok(jornadaService.getAll());
    }

    @PutMapping
    public ResponseEntity<JornadaTrabalho> update(@RequestBody JornadaTrabalho jornadaTrabalho){
        return new ResponseEntity<JornadaTrabalho>(jornadaService.save(jornadaTrabalho),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{jornadaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("jornadaId") Long id){
        jornadaService.delete(id);
    }
}
