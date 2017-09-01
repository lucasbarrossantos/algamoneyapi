package com.algamonyapi.resource;

import com.algamonyapi.model.Pessoa;
import com.algamonyapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> listar(){
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoa> salvar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{codigo}").buildAndExpand(pessoaSalva.getCodigo()).toUri();

        response.setHeader("Location", uri.toASCIIString());
        return ResponseEntity.created(uri).body(pessoaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPeloCodigo(@PathVariable("codigo") Long codigo){
        Pessoa pessoa = pessoaRepository.findOne(codigo);
        return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
    }

}
