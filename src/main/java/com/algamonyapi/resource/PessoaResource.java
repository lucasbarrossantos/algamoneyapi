package com.algamonyapi.resource;

import com.algamonyapi.event.RecursoCriadoEvent;
import com.algamonyapi.model.Pessoa;
import com.algamonyapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Pessoa> listar(){
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoa> salvar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPeloCodigo(@PathVariable("codigo") Long codigo){
        Pessoa pessoa = pessoaRepository.findOne(codigo);
        return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
    }

}
