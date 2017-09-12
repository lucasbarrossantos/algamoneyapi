package com.algamonyapi.repository;

import com.algamonyapi.model.Lancamento;
import com.algamonyapi.repository.helpe.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
