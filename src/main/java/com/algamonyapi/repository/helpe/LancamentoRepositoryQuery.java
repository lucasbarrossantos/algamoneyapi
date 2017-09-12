package com.algamonyapi.repository.helpe;

import com.algamonyapi.model.Lancamento;
import com.algamonyapi.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

}
