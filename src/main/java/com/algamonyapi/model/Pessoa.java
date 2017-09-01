package com.algamonyapi.model;

import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categoria")
public class Pessoa {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long codigo;

    @NotBlank
    private String nome;

    @NotNull
    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;

        Pessoa pessoa = (Pessoa) o;

        return getCodigo().equals(pessoa.getCodigo());
    }

    @Override
    public int hashCode() {
        return getCodigo().hashCode();
    }
}
