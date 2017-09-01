package com.algamonyapi.repository;

import com.algamonyapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
