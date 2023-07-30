package br.com.vinicius.loja.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
// Esta anotação serve para mostrar que essa classe é uma entidade da JPA, ou seja, se econtra no banco de dados;
@Table(name = "categorias")// QUANDO O NOME DA TABELA NÃO É IGUAL O NOME DA CLASSE
@Getter
@Setter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria() {
    }
}
