package com.algamonyapi.repository.helpe;

import com.algamonyapi.model.Lancamento;
import com.algamonyapi.repository.filter.LancamentoFilter;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        List<Predicate> predicates = criarRestrincoes(lancamentoFilter, builder, root);

        criteria.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Lancamento> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private List<Predicate> criarRestrincoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder, Root<Lancamento> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (lancamentoFilter.getDescricao() != null) {
            predicates.add(builder.like(
                    builder.lower(root.get("descricao")), "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
        }

        if (lancamentoFilter.getDataDeVencimentoDe() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataDeVencimentoDe()));
        }

        if (lancamentoFilter.getDataDeVencimentoAte() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataDeVencimentoAte()));
        }

        return predicates;
    }
}
